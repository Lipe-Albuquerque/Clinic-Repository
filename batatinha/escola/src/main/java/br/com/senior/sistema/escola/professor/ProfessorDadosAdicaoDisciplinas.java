package br.com.senior.sistema.escola.professor;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public record ProfessorDadosAdicaoDisciplinas(
		@NotNull Long idProfessor,
		@NotNull List<Long> idDisciplinas) {

}
