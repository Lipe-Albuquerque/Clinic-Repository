package br.com.senior.clinic.patient;

import org.hibernate.validator.constraints.br.CPF;

public record PatientEdit(Integer id, String name,@CPF String cpf) {

}
