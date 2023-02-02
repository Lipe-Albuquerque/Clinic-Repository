package br.com.senior.sistema.escola.professor;

import java.math.BigDecimal;

import br.com.senior.sistema.escola.endereco.EnderecoDadosCadastro;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ProfessorDadosCadastro(
		@NotBlank
		String nome,
		@NotBlank
		@Pattern(regexp = "\\d{6}")
		String matricula,
		@NotBlank
		@Pattern(regexp = "\\d{11}")
		String cpf,
		@NotBlank
		@Pattern(regexp = "\\d{10,11}")
		String telefone,
		@NotBlank
		@Email
		String email,
		@NotNull
		BigDecimal salario,
		String formacao,
		@NotNull
		@Valid
		EnderecoDadosCadastro endereco
		) {

}

