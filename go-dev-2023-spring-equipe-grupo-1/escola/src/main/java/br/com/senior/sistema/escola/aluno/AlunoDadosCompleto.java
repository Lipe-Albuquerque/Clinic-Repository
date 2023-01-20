package br.com.senior.sistema.escola.aluno;

import br.com.senior.sistema.escola.endereco.EnderecoDadosListagem;
import br.com.senior.sistema.escola.turma.TurmaDadosListagem;

public record AlunoDadosCompleto(Long id, String nome, String matricula, String cpf, String telefone, String email, EnderecoDadosListagem endereco, TurmaDadosListagem turma) {
	
	public AlunoDadosCompleto(Aluno aluno) {
		
		this(aluno.getId(), aluno.getNome(), aluno.getMatricula(), aluno.getCpf(), aluno.getTelefone(), aluno.getEmail(), new EnderecoDadosListagem(aluno.getEndereco()), (aluno.getTurma() != null) ? new TurmaDadosListagem(aluno.getTurma()) : null);
		
	}
	
}
