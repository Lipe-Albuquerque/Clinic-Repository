package br.com.senior.clinic.scheduling;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.senior.clinic.doctor.DoctorRepository;
import br.com.senior.clinic.patient.PatientRepository;

@Service
public class SchedulingService {

	@Autowired
	private SchedulingRepository agendamentoRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private PatientRepository patientRepository;

	public SchedulingDados findById(Integer id) {
		atualizarAgendamentosVencidos();
		Optional<Scheduling> agendamento = agendamentoRepository.findById(id);
		SchedulingDados agendamentoDados = new SchedulingDados(agendamento);
		return agendamentoDados;
	}

	public Page<SchedulingList> listAllAtivo(Pageable paginacao) {
		atualizarAgendamentosVencidos();
		return agendamentoRepository.findAllByAtivoTrue(paginacao);
	}

	private void atualizarAgendamentosVencidos() {

		agendamentoRepository.findAllByAtivoTrue().stream()
				.filter(obj -> obj.getDataConsulta().isBefore(LocalDateTime.now().plusHours(1)))
				.forEach(obj -> obj.setDescricao(obj.getDescricao() + "/ SCHEDULE COMPLETED"));
		;
		agendamentoRepository.findAllByAtivoTrue().stream()
				.filter(obj -> obj.getDataConsulta().isBefore(LocalDateTime.now().plusHours(1))).forEach(obj -> obj.delete());
		;
	}

	public Page<SchedulingList> listAllDesativados(Pageable paginacao) {
		return agendamentoRepository.findAllByAtivoFalse(paginacao);
	}

	public SchedulingDados add(SchedulingAdd agendamento) {
		if (agendamento.dataConsulta().isBefore(LocalDateTime.now().plusHours(1))) {
			throw new IllegalArgumentException(
					"YOUR APPOINTMENT MUST BE BOOKED AT LEAST ONE HOUR IN ADVANCE, PLEASE CORRECT THE INSERTED DATE, ");
		}
		if (doctorRepository.getReferenceById(agendamento.doctor()) == null) {
			throw new IllegalArgumentException("PLEASE INFORM A VALID DOCTOR FOR CONSULTATION, ");
		}
		List<Scheduling> listTemp = doctorRepository.getReferenceById(agendamento.doctor()).getListAgendamentos();

		for (int count = 0; count < listTemp.size(); count++) {

			if (listTemp.get(count).getDataConsulta().equals(agendamento.dataConsulta())) {
				throw new IllegalArgumentException(
						"DOCTOR OCCUPIED ON THIS DATE, PLEASE INFORM A VALID DATE FOR CONSULTATION, ");
			}

		}

		if (patientRepository.getReferenceById(agendamento.patient()) == null) {
			throw new IllegalArgumentException("PLEASE INFORM A VALID PATIENT FOR CONSULTATION, ");
		}
		if (agendamento.description().isBlank()) {
			throw new IllegalArgumentException("PLEASE INFORM THE DESCRIPTION OF THE SCHEDULE, ");
		}
		Scheduling agendamentoNew = new Scheduling(agendamento.description(), agendamento.dataConsulta());

		agendamentoNew.setDoctor(doctorRepository.getReferenceById(agendamento.doctor()));
		doctorRepository.getReferenceById(agendamentoNew.getDoctor().getId()).addAgendamento(agendamentoNew);
		;

		agendamentoNew.setPaciente(patientRepository.getReferenceById(agendamento.patient()));
		patientRepository.getReferenceById(agendamentoNew.getPaciente().getId()).addAgendamento(agendamentoNew);
		agendamentoRepository.save(agendamentoNew);
		SchedulingDados response = new SchedulingDados(agendamentoNew);
		return response;
	}

	public SchedulingDados edit(Integer id, SchedulingEdit agendamento) {
		if (agendamentoRepository.findByIdAndAtivoTrue(id) != null) {
			agendamentoRepository.getReferenceById(id).edit(agendamento);

			if (doctorRepository.getReferenceById(agendamento.doctor_id()) != null) {
				agendamentoRepository.getReferenceById(id)
						.setDoctor(doctorRepository.getReferenceById(agendamento.doctor_id()));
			} else {
				throw new IllegalArgumentException("doctor does not exist");
			}

		} else {
			throw new IllegalArgumentException("schedule does not exist");
		}
		SchedulingDados response = new SchedulingDados(agendamentoRepository.getReferenceById(id));
		return response;
	}

	public Boolean delete(Integer id) {

		if (agendamentoRepository.findById(id) == null) {
			throw new IllegalArgumentException("schedule does not exist!");
		}
		if(agendamentoRepository.findById(id).get().getAtivo() == false) {
			throw new IllegalArgumentException("Appointment already cancelled/finalized!");
		}
		agendamentoRepository.getReferenceById(id).delete();
		return true;
	}

}
