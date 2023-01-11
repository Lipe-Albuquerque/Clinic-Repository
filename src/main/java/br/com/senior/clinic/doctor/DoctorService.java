package br.com.senior.clinic.doctor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.senior.clinic.scheduling.SchedulingList;
import br.com.senior.clinic.scheduling.SchedulingRepository;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private SchedulingRepository agendamentoRepository;

	public DoctorDados findById(Integer id) {
		Optional<Doctor> doctor = doctorRepository.findById(id);
		DoctorDados doctorDados = new DoctorDados(doctor);
		return doctorDados;
	}

	public Page<DoctorList> findAllByAtivoTrue(Pageable paginacao) {

		return doctorRepository.findAllByAtivoTrue(paginacao);
	}

	public Page<DoctorList> findAllByAtivoFalse(Pageable paginacao) {

		return doctorRepository.findAllByAtivoFalse(paginacao);
	}

	public Doctor add(DoctorAdd doctor) {
		Doctor doctorAdd = new Doctor(doctor);
		doctorRepository.saveAndFlush(doctorAdd);
		return doctorAdd;
	}

	public void edit(Integer id, doctorEdit doctor) {
		if (doctorRepository.getReferenceById(id) == null) {
			throw new IllegalArgumentException("doctor does not exist!");
		}
		Doctor doctorEdit = doctorRepository.getReferenceById(id);
		doctorEdit.atualizarDados(doctor);
	}

	public void delete(Integer id) {
		if (doctorRepository.findById(id) == null) {
			throw new IllegalArgumentException("doctor does not exist!");
		}
		if (!agendamentoRepository.findByDoctorIdAndAtivoTrue(id).isEmpty()) {
			throw new IllegalArgumentException("doctor cannot be deleted, as he has active appointments");
		}
		doctorRepository.getReferenceById(id).delete();
	}

	public Page<SchedulingList> listarAgendamentoAberto(Pageable paginacao, Integer id) {

		return agendamentoRepository.findAllByDoctorIdAndAtivoTrue(paginacao, id);

	}

	public Page<SchedulingList> listarAgendamentoFechado(Pageable paginacao, Integer id) {
		
		return agendamentoRepository.findAllByDoctorIdAndAtivoFalse(paginacao, id);

	}

	public void ativarDoctor(Integer id) {

		if (doctorRepository.findByIdAndAtivoFalse(id) != null) {
			doctorRepository.findByIdAndAtivoFalse(id).ativar();
		} else {
			throw new IllegalArgumentException("DOCTOR DONT EXIST OR ALREADY ACTIVO");
		}

	}

}
