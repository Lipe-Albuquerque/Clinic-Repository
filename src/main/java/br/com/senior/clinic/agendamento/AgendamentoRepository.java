package br.com.senior.clinic.agendamento;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

	Page<AgendamentoList> findAllByAtivoTrue(Pageable paginacao);

	Page<AgendamentoList> findAllByAtivoFalse(Pageable paginacao);

	Page<AgendamentoList> findAllByPatientId(Pageable paginacao, Integer id);

	List<Agendamento> findByPatientIdAndAtivoTrue(Integer id);

	Page<AgendamentoList> findAllByDoctorId(Pageable paginacao, Integer id);

	List<Agendamento> findByDoctorIdAndAtivoTrue(Integer id);

	List<Agendamento> findAllByAtivoTrue();

	List<Agendamento> findAllByAtivoFalse();

	Page<AgendamentoList> findAllByPatientIdAndAtivoTrue(Pageable paginacao, Integer id);

	Page<AgendamentoList> findAllByPatientIdAndAtivoFalse(Pageable paginacao, Integer id);

	Page<AgendamentoList> findAllByDoctorIdAndAtivoTrue(Pageable paginacao, Integer id);

	Page<AgendamentoList> findAllByDoctorIdAndAtivoFalse(Pageable paginacao, Integer id);

}
