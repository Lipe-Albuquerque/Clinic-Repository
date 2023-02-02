package br.com.senior.clinic.patient;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer>{

	Page<PatientList> findAllByAtivoTrue(Pageable paginacao);

	Page<PatientList> findAllByAtivoFalse(Pageable paginacao);

	Patient findByCpf(String cpf);

	Patient findByIdAndAtivoFalse(Integer id);

}
