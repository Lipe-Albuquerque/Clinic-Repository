package br.com.senior.clinic.scheduling;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulingRepository extends JpaRepository<Scheduling, Integer> {

	Page<SchedulingList> findAllByAtivoTrue(Pageable paginacao);

	Page<SchedulingList> findAllByAtivoFalse(Pageable paginacao);

	Page<SchedulingList> findAllByPatientId(Pageable paginacao, Integer id);

	List<Scheduling> findByPatientIdAndAtivoTrue(Integer id);

	Page<SchedulingList> findAllByDoctorId(Pageable paginacao, Integer id);

	List<Scheduling> findByDoctorIdAndAtivoTrue(Integer id);

	List<Scheduling> findAllByAtivoTrue();

	List<Scheduling> findAllByAtivoFalse();

	Page<SchedulingList> findAllByPatientIdAndAtivoTrue(Pageable paginacao, Integer id);

	Page<SchedulingList> findAllByPatientIdAndAtivoFalse(Pageable paginacao, Integer id);

	Page<SchedulingList> findAllByDoctorIdAndAtivoTrue(Pageable paginacao, Integer id);

	Page<SchedulingList> findAllByDoctorIdAndAtivoFalse(Pageable paginacao, Integer id);

}
