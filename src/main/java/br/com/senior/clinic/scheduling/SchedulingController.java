package br.com.senior.clinic.scheduling;

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

import jakarta.transaction.Transactional;

@RequestMapping("scheduling")
@RestController
public class SchedulingController {

	@Autowired
	private SchedulingService agendamentoService;

	@Autowired
	public SchedulingController(SchedulingService agendamentoService) {
		super();
		this.agendamentoService = agendamentoService;
	}

	@GetMapping("/{id}")
	public SchedulingDados findById(@PathVariable Integer id) {
		return agendamentoService.findById(id);
	}

	@GetMapping
	@Transactional
	public Page<SchedulingList> listAll(@PageableDefault(size = 10, sort = { "id" }) Pageable paginacao) {
		return agendamentoService.listAllAtivo(paginacao);
	}

	@GetMapping("/finalized")
	public Page<SchedulingList> listAllFinalizados(@PageableDefault(size = 10, sort = { "id" }) Pageable paginacao) {
		return agendamentoService.listAllDesativados(paginacao);
	}

	@PostMapping
	@Transactional
	public void add(@RequestBody SchedulingAdd agendamento) {
		agendamentoService.add(agendamento);
	}

	@PutMapping("/{id}")
	@Transactional
	public void edit(@PathVariable Integer id, @RequestBody SchedulingEdit agendamento) {
		agendamentoService.edit(id, agendamento);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public void delete(@PathVariable Integer id) {
		agendamentoService.delete(id);
	}
}
