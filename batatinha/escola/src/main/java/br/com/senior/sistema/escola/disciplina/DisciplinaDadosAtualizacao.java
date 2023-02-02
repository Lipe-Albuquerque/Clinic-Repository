package br.com.senior.sistema.escola.disciplina;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public record DisciplinaDadosAtualizacao(
		@NotNull
		Long id,
		String nome,
		BigDecimal cargaHoraria) {
}