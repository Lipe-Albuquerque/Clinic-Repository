package br.com.senior.clinic.doctor;

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
@RequestMapping("doctor")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	@GetMapping("/{id}")
	public DoctorDados findById(@PathVariable Integer id) {
		return doctorService.findById(id);
	}

	@GetMapping
	public Page<DoctorList> findAllByAtivoTrue(@PageableDefault(size = 10, sort = { "name" }) Pageable paginacao) {
		return doctorService.findAllByAtivoTrue(paginacao);
	}

	@GetMapping("/desativados")
	public Page<DoctorList> findAllByAtivoFalse(@PageableDefault(size = 10, sort = { "name" }) Pageable paginacao) {
		return doctorService.findAllByAtivoFalse(paginacao);
	}

	@PostMapping
	@Transactional
	public Doctor add(@RequestBody DoctorAdd doctor) {
		return doctorService.add(doctor);
	}

	@PutMapping("/{id}")
	@Transactional
	public void edit(@PathVariable Integer id,@RequestBody doctorEdit doctor) {
		doctorService.edit(id,doctor);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public void delete(@PathVariable Integer id) {
		doctorService.delete(id);
	}

	@GetMapping("/agendamento/{id}")
	public Page<AgendamentoList> listAgendamentoAgendamentoAberto(@PathVariable Integer id,
			@PageableDefault(size = 10) Pageable paginacao) {
		return doctorService.listarAgendamentoAberto(paginacao, id);
	}

	@GetMapping("/agendamento/finalizado/{id}")
	public Page<AgendamentoList> listAgendamentosAgendamentoFechado(@PathVariable Integer id,
			@PageableDefault(size = 10) Pageable paginacao) {
		return doctorService.listarAgendamentoFechado(paginacao, id);
	}

	@PutMapping("ativar/{id}")
	@Transactional
	public void ativarPatient(@PathVariable Integer id) {
		doctorService.ativarDoctor(id);
	}
	
	
}
