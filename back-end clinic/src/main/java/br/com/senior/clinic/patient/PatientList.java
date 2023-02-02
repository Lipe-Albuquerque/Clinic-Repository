package br.com.senior.clinic.patient;

public record PatientList(Integer id, String name, Boolean ativo) {
//	public PatientList(Patient paciente) {
//		this(paciente.getId(), paciente.getName(), paciente.getAtivo());
//	}
//
//	public PatientList(Optional<Patient> paciente) {
//		this(paciente.get().getId(), paciente.get().getName(), paciente.get().getAtivo());
//	}
}
