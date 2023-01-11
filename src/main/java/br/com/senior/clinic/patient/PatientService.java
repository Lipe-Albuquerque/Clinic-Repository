package br.com.senior.clinic.patient;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.senior.clinic.scheduling.SchedulingList;
import br.com.senior.clinic.scheduling.SchedulingRepository;

@Service
public class PatientService {

	@Autowired
	private PatientRepository pacienteRepository;

	@Autowired
	private SchedulingRepository agendamentoRepository;

	public PatientDados findById(Integer id) {
		Optional<Patient> paciente = pacienteRepository.findById(id);
		PatientDados pacienteDados = new PatientDados(paciente);
		return pacienteDados;
	}

	public Patient add(PatientAdd paciente) {
		if (pacienteRepository.findByCpf(paciente.cpf()) != null) {
			throw new IllegalArgumentException("CPF ALREADY REGISTERED IN THE BANK");
		}
		Patient pacienteBanco = new Patient(paciente);
		return pacienteRepository.saveAndFlush(pacienteBanco);
	}

	public Page<PatientList> listar(Pageable paginacao) {
		return pacienteRepository.findAllByAtivoTrue(paginacao).map(PatientList::new);
	}

	public void edit(Integer id,PatientEdit paciente) {
		if (pacienteRepository.getReferenceById(id) == null) {
			throw new IllegalArgumentException("patient does not exist!");
		}
		Patient pacienteEdit = pacienteRepository.getReferenceById(id);
		pacienteEdit.atualizarDados(paciente);

	}

	public void delete(Integer id) {
		if (pacienteRepository.findById(id) == null) {
			throw new IllegalArgumentException("patient does not exist!");
		}
		if (!agendamentoRepository.findByPatientIdAndAtivoTrue(id).isEmpty()) {
			throw new IllegalArgumentException("patient cannot be deleted, as he has active appointments");
		}
		pacienteRepository.getReferenceById(id).deletar();

	}

	public Page<PatientList> listarDesativados(Pageable paginacao) {

		return pacienteRepository.findAllByAtivoFalse(paginacao).map(PatientList::new);
	}

	public Page<SchedulingList> listarAgendamentoAberto(Pageable paginacao, Integer id) {

		return agendamentoRepository.findAllByPatientIdAndAtivoTrue(paginacao, id);

	}

	public Page<SchedulingList> listarAgendamentoFechado(Pageable paginacao, Integer id) {

		return agendamentoRepository.findAllByPatientIdAndAtivoFalse(paginacao, id);

	}

	public void ativarPatient(Integer id) {
		
		pacienteRepository.findByIdAndAtivoFalse(id).ativar();
		
	}

}
