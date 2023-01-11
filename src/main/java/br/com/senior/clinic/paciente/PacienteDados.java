package br.com.senior.clinic.paciente;

import java.util.Optional;

public record PacienteDados(Integer id, String name, String cpf, Boolean ativo) {

	public PacienteDados(Optional<Paciente> paciente) {
		this(paciente.get().getId(), paciente.get().getName(), paciente.get().getCpf(), paciente.get().getAtivo());
	}

	public PacienteDados(Paciente obj) {
		this(obj.getId(), obj.getName(), obj.getCpf(), obj.getAtivo());
	}

}
