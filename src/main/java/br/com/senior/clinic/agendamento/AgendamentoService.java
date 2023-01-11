package br.com.senior.clinic.agendamento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.senior.clinic.doctor.DoctorRepository;
import br.com.senior.clinic.paciente.PacienteRepository;

@Service
public class AgendamentoService {

	@Autowired
	private AgendamentoRepository agendamentoRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private PacienteRepository patientRepository;

	public AgendamentoDados findById(Integer id) {
		atualizarAgendamentosVencidos();
		Optional<Agendamento> agendamento = agendamentoRepository.findById(id);
		AgendamentoDados agendamentoDados = new AgendamentoDados(agendamento);
		return agendamentoDados;
	}

	public Page<AgendamentoList> listAllAtivo(Pageable paginacao) {
		atualizarAgendamentosVencidos();
		return agendamentoRepository.findAllByAtivoTrue(paginacao);
	}

	private void atualizarAgendamentosVencidos() {

		agendamentoRepository.findAllByAtivoTrue().stream()
				.filter(obj -> obj.getDataConsulta().isBefore(LocalDateTime.now().plusHours(1)))
				.forEach(obj -> obj.setDescricao(obj.getDescricao() + "/ AGENDAMENTO CONCLUIDO"));
		;
		agendamentoRepository.findAllByAtivoTrue().stream()
				.filter(obj -> obj.getDataConsulta().isBefore(LocalDateTime.now().plusHours(1))).forEach(obj -> obj.delete());
		;
	}

	public Page<AgendamentoList> listAllDesativados(Pageable paginacao) {
		return agendamentoRepository.findAllByAtivoFalse(paginacao);
	}

	public void add(AgendamentoAdd agendamento) {
		if (agendamento.dataConsulta().isBefore(LocalDateTime.now().plusHours(1))) {
			throw new IllegalArgumentException(
					"SUA CONSULTA DEVE SER MARCADA COM PELO MENOS UMA HORA DE ANTECEDENCIA, FAVOR CORRIGIR A DATA INSERIDA, ");
		}
		if (doctorRepository.getReferenceById(agendamento.doctor()) == null) {
			throw new IllegalArgumentException("FAVOR INFORMAR UM MEDICO VALIDO PARA CONSULTA, ");
		}
		List<Agendamento> listTemp = doctorRepository.getReferenceById(agendamento.doctor()).getListAgendamentos();

		for (int count = 0; count < listTemp.size(); count++) {

			if (listTemp.get(count).getDataConsulta().equals(agendamento.dataConsulta())) {
				throw new IllegalArgumentException(
						"MEDICO OCUPADO NESTA DATA, FAVOR INFORMAR UMA DATA VALIDO PARA CONSULTA, ");
			}

		}

		if (patientRepository.getReferenceById(agendamento.patient()) == null) {
			throw new IllegalArgumentException("FAVOR INFORMAR UM PACIENTE VALIDO PARA CONSULTA, ");
		}
		if (agendamento.description().isBlank()) {
			throw new IllegalArgumentException("FAVOR INFORMAR A DESCRIÇÃO DO AGENDAMENTO, ");
		}
		Agendamento agendamentoNew = new Agendamento(agendamento.description(), agendamento.dataConsulta());

		agendamentoNew.setDoctor(doctorRepository.getReferenceById(agendamento.doctor()));
		doctorRepository.getReferenceById(agendamentoNew.getDoctor().getId()).addAgendamento(agendamentoNew);
		;

		agendamentoNew.setPaciente(patientRepository.getReferenceById(agendamento.patient()));
		patientRepository.getReferenceById(agendamentoNew.getPaciente().getId()).addAgendamento(agendamentoNew);
		agendamentoRepository.save(agendamentoNew);
	}

	public void edit(Integer id, AgendamentoEdit agendamento) {
		if (agendamentoRepository.findById(id) != null) {
			agendamentoRepository.getReferenceById(id).edit(agendamento);

			if (doctorRepository.getReferenceById(agendamento.doctor_id()) != null) {
				agendamentoRepository.getReferenceById(id)
						.setDoctor(doctorRepository.getReferenceById(agendamento.doctor_id()));
			} else {
				throw new IllegalArgumentException("DOCTOR DONT ExIST");
			}

		} else {
			throw new IllegalArgumentException("AGENDAMENTO DONT ExIST");
		}

	}

	public void delete(Integer id) {

		agendamentoRepository.getReferenceById(id).delete();

	}

}
