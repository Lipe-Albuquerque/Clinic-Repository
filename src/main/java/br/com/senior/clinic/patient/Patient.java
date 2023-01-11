package br.com.senior.clinic.patient;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import br.com.senior.clinic.scheduling.Scheduling;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Boolean ativo = true;
	@CPF
	private String cpf;
	@OneToMany(mappedBy = "patient")
	private List<Scheduling> listAgendamento = new ArrayList<>();

	public Patient() {

	}

	public Patient(PatientAdd paciente) {
		this.id = paciente.id();
		this.name = paciente.name();
		this.cpf = paciente.cpf();
	}

	public Patient(PatientList paciente) {
		this.id = paciente.id();
		this.name = paciente.name();
	}

	public Patient(Patient obj) {
		this.id = obj.id;
		this.name = obj.name;
		this.cpf = obj.cpf;
		this.listAgendamento = obj.listAgendamento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Scheduling> getListAgendamento() {
		return listAgendamento;
	}

	public void setListAgendamento(List<Scheduling> listAgendamento) {
		this.listAgendamento = listAgendamento;
	}

	public void atualizarDados(PatientEdit paciente) {

		if (paciente.name() != null) {
			this.name = paciente.name();
		}

		if (paciente.cpf() != null) {
			this.cpf = paciente.cpf();
		}

	}

	public void deletar() {

		this.ativo = false;

	}

	public void addAgendamento(Scheduling agendamento) {
		this.listAgendamento.add(agendamento);
	}

	public void ativar() {

		this.ativo = true;

	}

}
