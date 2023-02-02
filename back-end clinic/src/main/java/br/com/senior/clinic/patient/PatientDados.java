package br.com.senior.clinic.patient;

import java.util.Optional;

public record PatientDados(Integer id, String name, String cpf, Boolean ativo) {

	public PatientDados(Optional<Patient> paciente) {
		this(paciente.get().getId(), paciente.get().getName(), paciente.get().getCpf(), paciente.get().getAtivo());
	}

	public PatientDados(Patient obj) {
		this(obj.getId(), obj.getName(), obj.getCpf(), obj.getAtivo());
	}

}
