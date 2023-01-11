package br.com.senior.clinic.agendamento;

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

@RequestMapping("agendamento")
@RestController
public class AgendamentoController {

	@Autowired
	private AgendamentoService agendamentoService;

	@GetMapping("/{id}")
	public AgendamentoDados findById(@PathVariable Integer id) {
		return agendamentoService.findById(id);
	}

	@GetMapping
	@Transactional
	public Page<AgendamentoList> listAll(@PageableDefault(size = 10, sort = { "id" }) Pageable paginacao) {
		return agendamentoService.listAllAtivo(paginacao);
	}

	@GetMapping("/finalizados")
	public Page<AgendamentoList> listAllFinalizados(@PageableDefault(size = 10, sort = { "id" }) Pageable paginacao) {
		return agendamentoService.listAllDesativados(paginacao);
	}

	@PostMapping
	@Transactional
	public void add(@RequestBody AgendamentoAdd agendamento) {
		agendamentoService.add(agendamento);
	}

	@PutMapping("/{id}")
	@Transactional
	public void edit(@PathVariable Integer id, @RequestBody AgendamentoEdit agendamento) {
		agendamentoService.edit(id, agendamento);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public void delete(@PathVariable Integer id) {
		agendamentoService.delete(id);
	}
}
