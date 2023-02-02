package br.com.senior.clinic.scheduling;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.senior.clinic.doctor.Doctor;
import br.com.senior.clinic.patient.Patient;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Scheduling {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataConsulta;

	private Boolean ativo = true;

	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;

	private String description;

	public Scheduling() {
		super();
	}

	public Scheduling(Integer id, LocalDateTime dataConsulta, Doctor doctor, Patient paciente, String descricao) {
		super();
		this.id = id;
		this.dataConsulta = dataConsulta;
		this.doctor = doctor;
		this.patient = paciente;
		this.description = descricao;
	}
	
	public Scheduling(Integer id, LocalDateTime dataConsulta, Doctor doctor, Patient paciente, String descricao, Boolean active) {
		super();
		this.id = id;
		this.dataConsulta = dataConsulta;
		this.doctor = doctor;
		this.patient = paciente;
		this.description = descricao;
		this.ativo = active;
	}


	public Scheduling(SchedulingAdd obj) {
		this.id = obj.id();
		this.description = obj.description();
		this.dataConsulta = obj.dataConsulta();
	}

	public Scheduling(String description2, LocalDateTime dataConsulta2) {
		this.description = description2;
		this.dataConsulta = dataConsulta2;
	}

	public void delete() {
		setDescricao(description + " / DATA DA CONSULTA ULTRAPASSADA");
		this.ativo = false;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(LocalDateTime dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPaciente() {
		return patient;
	}

	public void setPaciente(Patient paciente) {
		this.patient = paciente;
	}

	public String getDescricao() {
		return description;
	}

	public void setDescricao(String descricao) {
		this.description = descricao;
	}

	public void edit(SchedulingEdit agendamento) {

		if (agendamento.description() != null) {
			this.description = agendamento.description();
		}
		if (agendamento.dataConsulta() != null && agendamento.dataConsulta().isAfter(LocalDateTime.now().plusHours(2))) {
			this.dataConsulta = agendamento.dataConsulta();
		}
	}

}
