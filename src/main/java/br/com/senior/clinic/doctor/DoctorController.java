package br.com.senior.clinic.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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

	@GetMapping("/finalized")
	public Page<DoctorList> findAllByAtivoFalse(@PageableDefault(size = 10, sort = { "name" }) Pageable paginacao) {
		return doctorService.findAllByAtivoFalse(paginacao);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<DoctorDados> add(@RequestBody DoctorAdd doctor) {
		return ResponseEntity.ok().body(doctorService.add(doctor));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<DoctorDados> edit(@PathVariable Integer id,@RequestBody DoctorEdit doctor) {
		return ResponseEntity.ok().body(doctorService.edit(id,doctor));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public boolean delete(@PathVariable Integer id) {
		return doctorService.delete(id);
	}

	@GetMapping("/scheduling/{id}")
	public Page<SchedulingList> listAgendamentoAgendamentoAberto(@PathVariable Integer id,
			@PageableDefault(size = 10) Pageable paginacao) {
		return doctorService.listarAgendamentoAberto(paginacao, id);
	}

	@GetMapping("/scheduling/finalized/{id}")
	public Page<SchedulingList> listAgendamentosAgendamentoFechado(@PathVariable Integer id,
			@PageableDefault(size = 10) Pageable paginacao) {
		return doctorService.listarAgendamentoFechado(paginacao, id);
	}

	@PutMapping("active/{id}")
	@Transactional
	public boolean ativarPatient(@PathVariable Integer id) {
		return doctorService.ativarDoctor(id);
	}
	
	
}
