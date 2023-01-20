package br.com.senior.sistema.escola.aluno;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.com.senior.sistema.escola.email.EmailService;
import br.com.senior.sistema.escola.endereco.EnderecoDadosAtualizacao;
import br.com.senior.sistema.escola.endereco.EnderecoDadosCadastro;
import br.com.senior.sistema.escola.turma.Serie;
import br.com.senior.sistema.escola.turma.Turma;
import br.com.senior.sistema.escola.turma.TurmaRepository;
import br.com.senior.sistema.escola.turma.TurmaService;

@ExtendWith(MockitoExtension.class)
class AlunoServiceTest {
	
	@InjectMocks
	private AlunoService service;
	
	@Mock
	private AlunoRepository repository;
	
	@Mock
	private TurmaRepository turmaRepository;
	
	@Mock
	private EmailService emailService;
	
	@Mock
	private TurmaService turmaService;
	
	@Test
	void deveriaJogarExcecaoQuandoNaoEncontrarAluno() {
		when(repository.findByIdAndAtivoTrue(any())).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		assertThrows(ResponseStatusException.class, () -> service.buscarEntidadeAtiva(any()));
	}
	
	@Test
	void deveriaJogarExcecaoDeConflitoQuandoCadastrarAlunoComMesmoCPFDeAlunoJaCadastrado() {
		Aluno aluno = entidadeAluno();
		
		when(repository.findByCpf(any())).thenReturn(Optional.of(aluno));
		
		AlunoDadosCadastro novoAluno = cadastroAluno();
		
		assertThrows(ResponseStatusException.class, () -> service.cadastarAluno(novoAluno));
	}
	
	@Test
	void deveriaJogarExcecaoDeConflitoQuandoCadastrarAlunoComMesmaMatriculaDeAlunoJaCadastrado() {
		Aluno aluno = entidadeAluno();
		
		when(repository.findByMatricula(any())).thenReturn(Optional.of(aluno));
		
		AlunoDadosCadastro novoAluno = cadastroAluno();
		
		assertThrows(ResponseStatusException.class, () -> service.cadastarAluno(novoAluno));
	}
	
	@Test
	void deveriaAtualizarUmAluno() {
		Aluno aluno = entidadeAluno();
		when(repository.findByIdAndAtivoTrue(any())).thenReturn(Optional.of(aluno));
		
		AlunoDadosAtualizacao alunoAtualizado = new AlunoDadosAtualizacao(1L, "Maria Silva", "maria@email.com", "47991000011", new EnderecoDadosAtualizacao("Rua Francisco Valhdieck", 987, "Na esquina", "Fortaleza", "Itajaí", "RG", "00000411"));
		
		service.atualizarAluno(alunoAtualizado);
		
		assertEquals(alunoAtualizado.nome(), aluno.getNome());
		assertEquals(alunoAtualizado.email(), aluno.getEmail());
		assertEquals(alunoAtualizado.telefone(), aluno.getTelefone());
		assertEquals(alunoAtualizado.endereco().logradouro(), aluno.getEndereco().getLogradouro());
		assertEquals(alunoAtualizado.endereco().numero(), aluno.getEndereco().getNumero());
		assertEquals(alunoAtualizado.endereco().complemento(), aluno.getEndereco().getComplemento());
		assertEquals(alunoAtualizado.endereco().bairro(), aluno.getEndereco().getBairro());
		assertEquals(alunoAtualizado.endereco().cidade(), aluno.getEndereco().getCidade());
		assertEquals(alunoAtualizado.endereco().uf(), aluno.getEndereco().getUf());
		assertEquals(alunoAtualizado.endereco().cep(), aluno.getEndereco().getCep());
	}
	
	@Test
	void deveriaMatricularUmAluno() {
		Aluno aluno = entidadeAluno();
		when(repository.findByIdAndAtivoTrue(any())).thenReturn(Optional.of(aluno));
		
		Turma turma = entidadeTurma();
		when(turmaService.buscarEntidadeAtiva(any())).thenReturn(turma);
		
		AlunoDadosMatriculaTurma dados = new AlunoDadosMatriculaTurma(1L, 1L);
		
		service.matricularAluno(dados);
		
		assertEquals(aluno.getTurma(), turma);
		
		verify(emailService, only()).sendSimpleMail(any());
	}
	
	@Test
	void deveriaReativarUmaAluno() {
		Aluno aluno = entidadeAluno();
		aluno.desativar();
		when(repository.findById(any())).thenReturn(Optional.of(aluno));
		
		service.reativarAluno(1L);
		
		assertTrue(aluno.getAtivo());
	}
	
	@Test
	void deveriaDesativarUmaAluno() {
		Aluno aluno = entidadeAluno();
		when(repository.findByIdAndAtivoTrue(any())).thenReturn(Optional.of(aluno));
		
		service.desativarAluno(1L);
		
		assertFalse(aluno.getAtivo());
	}
	
	@Test
	void deveriaVerificarSeEmailRealmenteEnviaOsDados() {
	
		Aluno aluno = entidadeAluno();
		when(repository.findByIdAndAtivoTrue(anyLong())).thenReturn(Optional.of(aluno));
		when(turmaRepository.findByIdAndAtivoTrue(anyLong())).thenReturn(Optional.of(entidadeTurma()));
		System.out.println(aluno);
		System.out.println(aluno.getTurma());
		AlunoDadosMatriculaTurma dados = new AlunoDadosMatriculaTurma(1l, 1l);
		service.matricularAluno(dados);
		System.out.println(aluno);
		System.out.println(aluno.getTurma());
		verify(emailService, times(1)).sendSimpleMail(any());
		
	}
	
	private Aluno entidadeAluno() {
		Aluno aluno = new Aluno(cadastroAluno());
		return aluno;
	}
	
	private Turma entidadeTurma() {
		return Turma.builder()
		.id(1L)
		.codigo("A1")
		.serie(Serie.PRIMEIRO_ANO)
		.build();
	}
	
	private AlunoDadosCadastro cadastroAluno() {
		return new AlunoDadosCadastro("João Puel", "808080", "00000000010", "joao.puel@email", "47999000010", new EnderecoDadosCadastro("Rua São Paulo", 800, null, "Centro", "Blumenau", "SC", "00000410"));
	}
	
}
