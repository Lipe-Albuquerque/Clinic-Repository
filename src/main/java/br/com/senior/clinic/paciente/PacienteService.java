package br.com.senior.clinic.paciente;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.senior.clinic.agendamento.AgendamentoList;
import br.com.senior.clinic.agendamento.AgendamentoRepository;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private AgendamentoRepository agendamentoRepository;

	public PacienteDados findById(Integer id) {
		Optional<Paciente> paciente = pacienteRepository.findById(id);
		PacienteDados pacienteDados = new PacienteDados(paciente);
		return pacienteDados;
	}

	public Paciente add(PacienteAdd paciente) {
		if (pacienteRepository.findByCpf(paciente.cpf()) != null) {
			throw new IllegalArgumentException("CPF JÁ CADASTRADO NO BANCO");
		}
		Paciente pacienteBanco = new Paciente(paciente);
		return pacienteRepository.saveAndFlush(pacienteBanco);
	}

	public Page<PacienteList> listar(Pageable paginacao) {
		return pacienteRepository.findAllByAtivoTrue(paginacao).map(PacienteList::new);
	}

	public void edit(Integer id,PacienteEdit paciente) {
		if (pacienteRepository.getReferenceById(id) == null) {
			throw new IllegalArgumentException("Esse paciente não existe! favor verificar id");
		}
		Paciente pacienteEdit = pacienteRepository.getReferenceById(id);
		pacienteEdit.atualizarDados(paciente);

	}

	public void delete(Integer id) {
		if (pacienteRepository.findById(id) == null) {
			throw new IllegalArgumentException("Patient dont exist!");
		}
		if (!agendamentoRepository.findByPatientIdAndAtivoTrue(id).isEmpty()) {
			throw new IllegalArgumentException("patient cannot be deleted, as he has active appointments");
		}
		pacienteRepository.getReferenceById(id).deletar();

	}

	public Page<PacienteList> listarDesativados(Pageable paginacao) {

		return pacienteRepository.findAllByAtivoFalse(paginacao).map(PacienteList::new);
	}

	public Page<AgendamentoList> listarAgendamentoAberto(Pageable paginacao, Integer id) {

		return agendamentoRepository.findAllByPatientIdAndAtivoTrue(paginacao, id);

	}

	public Page<AgendamentoList> listarAgendamentoFechado(Pageable paginacao, Integer id) {

		return agendamentoRepository.findAllByPatientIdAndAtivoFalse(paginacao, id);

	}

	public void ativarPatient(Integer id) {
		
		pacienteRepository.findByIdAndAtivoFalse(id).ativar();
		
	}

}
