package br.com.senior.clinic.paciente;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer>{

	Page<Paciente> findAllByAtivoTrue(Pageable paginacao);

	Page<Paciente> findAllByAtivoFalse(Pageable paginacao);

	Paciente findByCpf(String cpf);

	Paciente findByIdAndAtivoFalse(Integer id);

}
