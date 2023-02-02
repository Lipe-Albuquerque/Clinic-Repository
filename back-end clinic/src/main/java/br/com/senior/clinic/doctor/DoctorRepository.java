package br.com.senior.clinic.doctor;
	
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer>{

	Page<DoctorList> findAllByAtivoTrue(Pageable paginacao);

	Page<DoctorList> findAllByAtivoFalse(Pageable paginacao);

	Doctor findByIdAndAtivoFalse(Integer id);

}
