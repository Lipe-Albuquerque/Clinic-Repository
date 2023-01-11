package br.com.senior.clinic.paciente;

import org.hibernate.validator.constraints.br.CPF;

public record PacienteEdit(Integer id, String name,@CPF String cpf) {

}
