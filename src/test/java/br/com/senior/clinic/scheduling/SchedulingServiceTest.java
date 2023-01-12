package br.com.senior.clinic.scheduling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import br.com.senior.clinic.doctor.Doctor;
import br.com.senior.clinic.doctor.DoctorDados;
import br.com.senior.clinic.patient.Patient;
import br.com.senior.clinic.patient.PatientDados;

@SpringBootTest
class SchedulingServiceTest extends Mockito {

	private final int id = 1;
	private final boolean active = true;
	private final String nameDoctor = "filipe";
	private final String namePatient = "rafael";
	private final String cpf = "822.883.990-1";
	private final String crm = "12345sc";
	private final String descricao = "Consulta De Vista";
	private final LocalDateTime plusDays = LocalDateTime.now().plusDays(1);
	private final LocalDateTime vencido = LocalDateTime.now().minusDays(2);

	private final PageRequest paginacao = PageRequest.of(1, 10);

	@InjectMocks
	private SchedulingService service;

	@Mock
	private SchedulingRepository repository;

	private Optional<Scheduling> schedulingOptional;

	private Scheduling scheduling;

	private Scheduling schedulingVencido;

	private SchedulingList schedulingRecordList;
	
	private SchedulingList schedulingRecordListVencido;

	private SchedulingDados schedulingDados;

	private DoctorDados doctorDados;

	private PatientDados patientDados;

	private Doctor doctor;

	private Patient patient;

	private List<Scheduling> list;

	private Page<SchedulingList> listPaginado;

	private List<SchedulingList> listRecord;

	@BeforeEach
	public void initi() {
		MockitoAnnotations.openMocks(this);
		startScheduling();

	}

	@Test
	void DeveriaRetornarUmAgendamentoDados() {
		when(repository.findById(anyInt())).thenReturn(schedulingOptional);
		SchedulingDados response = service.findById(scheduling.getId());
		assertEquals(SchedulingDados.class, response.getClass());
	}

	@Test
	void DeveriaRetornarListDeSchedulingList() {

		when(repository.findAllByAtivoTrue(paginacao)).thenReturn(listPaginado);

		Page<SchedulingList> response = service.listAllAtivo(paginacao);

		assertEquals(2, response.getSize());

	}
	
	@Test
	void DeveRetornarListaComDatasVencidas() {

		when(repository.findAllByAtivoFalse(paginacao)).thenReturn(listPaginado);
		Page<SchedulingList> response = service.listAllDesativados(paginacao);
		List<SchedulingList> test = new ArrayList<>();
		response.forEach(i -> test.add(i));
		assertFalse(test.get(1).ativo());

	}


	private void startScheduling() {

		list = new ArrayList<>();

		listRecord = new ArrayList<>();

		doctor = new Doctor(id, nameDoctor, crm, list, active);

		patient = new Patient(id, namePatient, cpf, list, active);

		scheduling = new Scheduling(id, plusDays, doctor, patient, descricao, active);

		schedulingVencido = new Scheduling(id, vencido, doctor, patient, descricao, active);

		schedulingRecordList = new SchedulingList(id, descricao, plusDays, id, id, active);

		schedulingRecordListVencido = new SchedulingList(id, descricao, vencido, id, id, false);

		schedulingOptional = Optional.of(new Scheduling(id, plusDays, doctor, patient, descricao, active));

		list.add(scheduling);
		
		list.add(schedulingVencido);

		doctorDados = new DoctorDados(doctor);

		patientDados = new PatientDados(patient);

		schedulingDados = new SchedulingDados(schedulingOptional);

		listRecord.add(schedulingRecordList);
		
		listRecord.add(schedulingRecordListVencido);

		listPaginado = new PageImpl<SchedulingList>(listRecord);


	}

}