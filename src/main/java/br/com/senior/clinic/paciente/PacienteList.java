package br.com.senior.clinic.paciente;

import java.util.Optional;

public record PacienteList(Integer id, String name, Boolean ativo) {
	public PacienteList(Paciente paciente) {
		this(paciente.getId(), paciente.getName(), paciente.getAtivo());
	}

	public PacienteList(Optional<Paciente> paciente) {
		this(paciente.get().getId(), paciente.get().getName(), paciente.get().getAtivo());
	}
}
