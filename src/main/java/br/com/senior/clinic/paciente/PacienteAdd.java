package br.com.senior.clinic.paciente;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotBlank;

public record PacienteAdd(Integer id,@NotBlank String name,
@CPF String cpf) {

}
