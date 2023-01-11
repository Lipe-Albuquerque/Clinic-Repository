package br.com.senior.clinic.agendamento;

import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.senior.clinic.doctor.DoctorDados;
import br.com.senior.clinic.paciente.PacienteDados;

public record AgendamentoDados(Integer id,@JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime dataConsulta, DoctorDados doctor, PacienteDados patient,
		String description, Boolean ativo) {

	public AgendamentoDados(Optional<Agendamento> agendamento) {
		this(agendamento.get().getId(), agendamento.get().getDataConsulta(),
				new DoctorDados(agendamento.get().getDoctor()), new PacienteDados(agendamento.get().getPaciente()),
				agendamento.get().getDescricao(), agendamento.get().getAtivo());
	}

}
