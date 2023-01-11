package br.com.senior.clinic.patient;

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

import br.com.senior.clinic.scheduling.SchedulingList;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("patient")
public class PatientController {

	@Autowired
	private PatientService pacienteService;
	
	@GetMapping("/{id}")
	public PatientDados findById(@PathVariable Integer id) {
		return pacienteService.findById(id);
	}

	@PostMapping
	@Transactional
	public Patient adicionarPaciente(@RequestBody PatientAdd paciente) {
		return pacienteService.add(paciente);
	}

	@GetMapping
	public Page<PatientList> listarPacientes(@PageableDefault(size = 10, sort = { "name" }) Pageable paginacao) {
		return pacienteService.listar(paginacao);
	}
	
	@GetMapping("/desativados")
	public Page<PatientList> listarPacientesDesativados(@PageableDefault(size = 10, sort = { "name" }) Pageable paginacao) {
		return pacienteService.listarDesativados(paginacao);
	}


	@PutMapping("/{id}")
	@Transactional
	public void editarPaciente(@PathVariable Integer id ,@RequestBody PatientEdit paciente) {
		pacienteService.edit(id,paciente);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public void deletePaciente(@PathVariable Integer id) {
		pacienteService.delete(id);
	}
	
	@GetMapping("/scheduling/{id}")
	public Page<SchedulingList> listAgendamentosPatientAberto(@PathVariable Integer id, @PageableDefault(size = 10) Pageable paginacao){
		return pacienteService.listarAgendamentoAberto(paginacao,id);
	}
	
	@GetMapping("/scheduling/finalized/{id}")
	public Page<SchedulingList> listAgendamentosPatientFechado(@PathVariable Integer id, @PageableDefault(size = 10) Pageable paginacao){
		return pacienteService.listarAgendamentoFechado(paginacao,id);
	}
	
	@PutMapping("active/{id}")
	@Transactional
	public void ativarPatient(@PathVariable Integer id) {
		pacienteService.ativarPatient(id);
	}
	
}
