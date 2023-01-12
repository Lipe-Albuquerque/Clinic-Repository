package br.com.senior.clinic.doctor;

import java.util.List;

import br.com.senior.clinic.scheduling.Scheduling;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
@Entity
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Boolean ativo = true;
	private String crm;
	@OneToMany(mappedBy = "doctor")
	private List<Scheduling> listAgendamentos;
	 
	public Doctor(Integer id, String name ,String crm, List<Scheduling> listAgendamentos, Boolean active) {
		super();
		this.id = id;
		this.name = name; 
		this.crm = crm;
		this.listAgendamentos = listAgendamentos;
		this.ativo = active;
	}
	
	public Doctor(Integer id, String name ,String crm, List<Scheduling> listAgendamentos) {
		super();
		this.id = id;
		this.name = name; 
		this.crm = crm;
		this.listAgendamentos = listAgendamentos;
	}

	protected Doctor() {
		super();
	}

	public Doctor(DoctorAdd doctor) {
		this.id = doctor.id();
		this.name = doctor.name();
		this.crm = doctor.crm();
		this.ativo = true;
	}
	
	public Doctor(Doctor obj) {
		this.id = obj.id;
		this.crm = obj.crm;
		this.name = obj.name;
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

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public List<Scheduling> getListAgendamentos() {
		return listAgendamentos;
	}

	public void setListAgendamentos(List<Scheduling> listAgendamentos) {
		this.listAgendamentos = listAgendamentos;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public void delete() {
		this.ativo = false;
	}
	
	public void addAgendamento(Scheduling agendamento) {
		this.listAgendamentos.add(agendamento);
	}

	public void atualizarDados(doctorEdit doctor) {
		if(doctor.name() != null) {
			this.name = doctor.name();
		}
		if(doctor.crm() != null) {
			this.crm = doctor.crm();
		}	
	}

	public void ativar() {
		this.ativo = true;
	}
	
}
