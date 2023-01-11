package br.com.senior.clinic.paciente;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import br.com.senior.clinic.agendamento.Agendamento;
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
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Boolean ativo = true;
	@CPF
	private String cpf;
	@OneToMany(mappedBy = "patient")
	private List<Agendamento> listAgendamento = new ArrayList<>();

	public Paciente() {

	}

	public Paciente(PacienteAdd paciente) {
		this.id = paciente.id();
		this.name = paciente.name();
		this.cpf = paciente.cpf();
	}

	public Paciente(PacienteList paciente) {
		this.id = paciente.id();
		this.name = paciente.name();
	}

	public Paciente(Paciente obj) {
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

	public List<Agendamento> getListAgendamento() {
		return listAgendamento;
	}

	public void setListAgendamento(List<Agendamento> listAgendamento) {
		this.listAgendamento = listAgendamento;
	}

	public void atualizarDados(PacienteEdit paciente) {

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

	public void addAgendamento(Agendamento agendamento) {
		this.listAgendamento.add(agendamento);
	}

	public void ativar() {

		this.ativo = true;

	}

}
