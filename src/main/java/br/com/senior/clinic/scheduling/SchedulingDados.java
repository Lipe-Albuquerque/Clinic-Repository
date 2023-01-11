package br.com.senior.clinic.scheduling;

import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.senior.clinic.doctor.DoctorDados;
import br.com.senior.clinic.patient.PatientDados;

public record SchedulingDados(Integer id,@JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime dataConsulta, DoctorDados doctor, PatientDados patient,
		String description, Boolean ativo) {

	public SchedulingDados(Optional<Scheduling> agendamento) {
		this(agendamento.get().getId(), agendamento.get().getDataConsulta(),
				new DoctorDados(agendamento.get().getDoctor()), new PatientDados(agendamento.get().getPaciente()),
				agendamento.get().getDescricao(), agendamento.get().getAtivo());
	}

}
