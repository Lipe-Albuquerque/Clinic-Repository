package br.com.senior.sistema.escola.aluno;

import jakarta.validation.constraints.NotNull;

public record AlunoDadosMatriculaTurma(
		@NotNull
		Long id,
		@NotNull
		Long idTurma) {

}
