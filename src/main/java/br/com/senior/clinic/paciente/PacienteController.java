package br.com.senior.clinic.paciente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senior.clinic.agendamento.AgendamentoList;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("patient")
public class PacienteController {

	@Autowired
	private PacienteService pacienteService;
	
	@GetMapping("/{id}")
	public PacienteDados findById(@PathVariable Integer id) {
		return pacienteService.findById(id);
	}

	@PostMapping
	@Transactional
	public Paciente adicionarPaciente(@RequestBody PacienteAdd paciente) {
		return pacienteService.add(paciente);
	}

	@GetMapping
	public Page<PacienteList> listarPacientes(@PageableDefault(size = 10, sort = { "name" }) Pageable paginacao) {
		return pacienteService.listar(paginacao);
	}
	
	@GetMapping("/desativados")
	public Page<PacienteList> listarPacientesDesativados(@PageableDefault(size = 10, sort = { "name" }) Pageable paginacao) {
		return pacienteService.listarDesativados(paginacao);
	}


	@PutMapping("/{id}")
	@Transactional
	public void editarPaciente(@PathVariable Integer id ,@RequestBody PacienteEdit paciente) {
		pacienteService.edit(id,paciente);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public void deletePaciente(@PathVariable Integer id) {
		pacienteService.delete(id);
	}
	
	@GetMapping("/agendamento/{id}")
	public Page<AgendamentoList> listAgendamentosPatientAberto(@PathVariable Integer id, @PageableDefault(size = 10) Pageable paginacao){
		return pacienteService.listarAgendamentoAberto(paginacao,id);
	}
	
	@GetMapping("/agendamento/finalizado/{id}")
	public Page<AgendamentoList> listAgendamentosPatientFechado(@PathVariable Integer id, @PageableDefault(size = 10) Pageable paginacao){
		return pacienteService.listarAgendamentoFechado(paginacao,id);
	}
	
	@PutMapping("ativar/{id}")
	@Transactional
	public void ativarPatient(@PathVariable Integer id) {
		pacienteService.ativarPatient(id);
	}
	
}
