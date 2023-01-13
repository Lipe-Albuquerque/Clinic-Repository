package br.com.senior.clinic.scheduling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import br.com.senior.clinic.doctor.Doctor;
import br.com.senior.clinic.doctor.DoctorDados;
import br.com.senior.clinic.doctor.DoctorRepository;
import br.com.senior.clinic.patient.Patient;
import br.com.senior.clinic.patient.PatientDados;
import br.com.senior.clinic.patient.PatientRepository;

@SpringBootTest
class SchedulingServiceTest {

	private final int id = 1;
	private final int doctorId = 1;
	private final int patientId = 1;
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
	
	@Mock
	private DoctorRepository doctorRepository;
	
	@Mock
	private PatientRepository patientRepository;

	private Optional<Scheduling> schedulingOptional;

	private Scheduling scheduling;

	private Scheduling schedulingVencido;

	private SchedulingList schedulingRecordList;
	
	private SchedulingList schedulingRecordListVencido;

	private SchedulingDados schedulingDados;

	private DoctorDados doctorDados;

	private PatientDados patientDados;
	
	private SchedulingEdit schedulingEdit;
	
	private SchedulingAdd schedulingAdd;

	private Doctor doctor;

	private Patient patient;

	private List<Scheduling> list;

	private Page<SchedulingList> listPaginado;

	private List<SchedulingList> listRecord;

	@BeforeEach
	public void initi() {
		startScheduling();
	}

	@Test
	void DeveriaRetornarUmAgendamentoDados() {
		when(repository.findById(1)).thenReturn(schedulingOptional);
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
		
		response.filter(i -> i.ativo() == false)
		.forEach(i -> test.add(i));
		
		assertFalse(test.get(0).ativo());

	}

	@Test
	void DeveriaAtualizarADataDoAgendamento() {
		
		when(repository.getReferenceById(1)).thenReturn(scheduling);
		when(repository.findByIdAndAtivoTrue(1)).thenReturn(scheduling);
		when(doctorRepository.getReferenceById(1)).thenReturn(scheduling.getDoctor());
		when(patientRepository.getReferenceById(1)).thenReturn(scheduling.getPaciente());

		
		service.edit(1, schedulingEdit);
		assertEquals(schedulingEdit.description(), scheduling.getDescricao());
		assertEquals(schedulingEdit.dataConsulta(), scheduling.getDataConsulta());
		
	}
	
	@Test
	void DeveriaCriarUmaEntidadeDeAgendamento() {
		when(repository.save(any())).thenReturn(scheduling);
		when(repository.getReferenceById(any())).thenReturn(scheduling);
		when(doctorRepository.getReferenceById(any())).thenReturn(scheduling.getDoctor());
		when(patientRepository.getReferenceById(any())).thenReturn(scheduling.getPaciente());
		service.add(schedulingAdd);
		SchedulingDados response = new SchedulingDados(repository.getReferenceById(scheduling.getId()));
		assertNotNull(response);
		assertEquals(SchedulingDados.class, response.getClass());
		assertEquals(id, response.id());
		assertEquals(plusDays, response.dataConsulta());
		assertEquals(doctor, doctorRepository.getReferenceById(response.doctor().id()));
		assertEquals(patient, patientRepository.getReferenceById(response.patient().id()));
		assertEquals(descricao, response.description());
		assertEquals(active, response.ativo());

	}

	private void startScheduling() {

		list = new ArrayList<>();

		listRecord = new ArrayList<>();

		doctor = new Doctor(id, nameDoctor, crm, list, active);

		patient = new Patient(id, namePatient, cpf, list, active);

		scheduling = new Scheduling(id, plusDays, doctor, patient, descricao, active);

		schedulingVencido = new Scheduling(id, vencido, doctor, patient, descricao, active);

		schedulingRecordList = new SchedulingList(id, descricao, plusDays, doctorId, patientId, active);

		schedulingRecordListVencido = new SchedulingList(id, descricao, vencido, doctorId, patientId, false);

		schedulingOptional = Optional.of(scheduling);

		schedulingAdd = new SchedulingAdd(id, LocalDateTime.now().plusDays(1), doctorId, patientId, descricao);

		schedulingEdit = new SchedulingEdit(descricao, LocalDateTime.of(2023, 10, 20, 10, 20), 1) ;
		
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
