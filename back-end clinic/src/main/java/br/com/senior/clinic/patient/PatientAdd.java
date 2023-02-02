package br.com.senior.clinic.patient;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotBlank;

public record PatientAdd(Integer id,@NotBlank String name,
@CPF String cpf) {

}
