package br.com.senior.clinic.doctor;

import java.util.Optional;

public record DoctorDados(Integer id, String name, String crm, Boolean ativo) {

	public DoctorDados(Optional<Doctor> doctor) {
		this(doctor.get().getId(), doctor.get().getName(), doctor.get().getCrm(), doctor.get().getAtivo());
	}

	public DoctorDados(Doctor obj) {
		this(obj.getId(), obj.getName(), obj.getCrm(), obj.getAtivo());
	}
	
}
