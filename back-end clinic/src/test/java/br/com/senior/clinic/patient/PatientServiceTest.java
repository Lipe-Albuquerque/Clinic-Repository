package br.com.senior.clinic.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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
import br.com.senior.clinic.scheduling.Scheduling;
import br.com.senior.clinic.scheduling.SchedulingList;
import br.com.senior.clinic.scheduling.SchedulingRepository;

@SpringBootTest
class PatientServiceTest{

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
	private PatientService patientService;

	@Mock
	private PatientRepository patientRepository;

	@Mock
	private SchedulingRepository schedulingRepository;

	private Patient patientEntity;
	
	private Patient patientEntityDesativado;

//	private Optional<Patient> optional;

	private Doctor doctorEntity;

	private PatientDados patientDados;

	private PatientAdd patientAdd;

	private PatientList patientList;
	
	private PatientList patientListDesativado;

	private PatientEdit patientEdit;

	private Scheduling scheduling;

	private Scheduling schedulingVencido;

	private SchedulingList schedulingRecordList;

	private SchedulingList schedulingRecordListVencido;

	private List<Scheduling> list;
	
	private Page<SchedulingList> listPaginadoScheduling;

	private List<SchedulingList> listRecordScheduling;
	
	private List<Patient> listPatients;

	private Page<PatientList> listPaginado;

	private List<PatientList> listRecord;

	@BeforeEach
	public void initi() {
		startPatient();
	}

	@Test
	void findById() {
		
		
		when(patientRepository.findById(1)).thenReturn(Optional.of(patientEntity));
		PatientDados response = patientService.findById(1);
		assertEquals(PatientDados.class, response.getClass());
	}

	@Test
	void add() {
		when(patientRepository.findByCpf(any())).thenReturn(null);
		when(patientRepository.save(any())).thenReturn(patientEntity);
		when(patientRepository.getReferenceById(anyInt())).thenReturn(patientEntity);
		patientService.add(patientAdd);
		PatientDados response = new PatientDados(patientRepository.getReferenceById(patientEntity.getId()));

		assertNotNull(response);
		assertEquals(patientId, response.id());
		assertEquals(namePatient, response.name());
		assertEquals(cpf, response.cpf());
		assertEquals(active, response.ativo());

	}

	@Test
	void listar() {
		when(patientRepository.findAllByAtivoTrue(paginacao)).thenReturn(listPaginado);
		
		Page<PatientList> response = patientService.findAllByAtivoTrue(paginacao);

		assertEquals(2, response.getSize());
	}

	@Test
	void edit() {
		when(patientRepository.getReferenceById(anyInt())).thenReturn(patientEntity);
		patientService.edit(1, patientEdit);
		
		assertEquals(patientEdit.name(), patientEntity.getName());
		
	}

	@Test
	void delete() {
		when(patientRepository.findById(anyInt())).thenReturn(Optional.of(patientEntity));
		when(patientRepository.getReferenceById(anyInt())).thenReturn(patientEntity);
		when(schedulingRepository.findByPatientIdAndAtivoTrue(anyInt())).thenReturn(Collections.emptyList());
		when(patientRepository.findByIdAndAtivoFalse(anyInt())).thenReturn(null);
		
		assertNotNull(patientService.delete(1));
		

	}

	@Test
	void listarDesativados() {
		when(patientRepository.findAllByAtivoFalse(any())).thenReturn(listPaginado);
		Page<PatientList> response = patientService.findAllByAtivoFalse(paginacao);
		List<PatientList> test = new ArrayList<>();
		
		response.filter(i -> i.ativo() == false)
		.forEach(i -> test.add(i));
		
		assertFalse(test.get(0).ativo());
	}

	@Test
	void listarAgendamentoAberto() {

		when(schedulingRepository.findAllByPatientIdAndAtivoTrue(any(), any())).thenReturn(listPaginadoScheduling);
		Page<SchedulingList> response = patientService.listarAgendamentoAberto(paginacao, 1);
		List<SchedulingList> test = new ArrayList<>();
		
		response.filter(i -> i.ativo() == true)
		.forEach(i -> test.add(i));
		
		assertTrue(test.get(0).ativo());
		
	}

	@Test
	void listarAgendamentoFechado() {

		when(schedulingRepository.findAllByPatientIdAndAtivoFalse(any(), any())).thenReturn(listPaginadoScheduling);
		Page<SchedulingList> response = patientService.listarAgendamentoFechado(paginacao, 1);
		List<SchedulingList> test = new ArrayList<>();
		
		response.filter(i -> i.ativo() == false)
		.forEach(i -> test.add(i));
		
		assertFalse(test.get(0).ativo());
		
		
	}

	@Test
	void ativarPatient() {
		when(patientRepository.findByIdAndAtivoFalse(anyInt())).thenReturn(patientEntityDesativado);
		when(patientRepository.getReferenceById(anyInt())).thenReturn(patientEntityDesativado);
		patientService.ativarPatient(1);
		Patient response = patientRepository.getReferenceById(1);
		assertTrue(response.getAtivo());
	}

	private void startPatient() {

		list = new ArrayList<>();

		listRecord = new ArrayList<>();
		
		listRecordScheduling = new ArrayList<>();

		patientEntity = new Patient(patientId, namePatient, cpf, list, active);
		
		patientEntityDesativado = new Patient(patientId, namePatient, cpf, list, false);

		doctorEntity = new Doctor(doctorId, nameDoctor, crm, list, active);

		scheduling = new Scheduling(id, plusDays, doctorEntity, patientEntity, descricao, active);

		list.add(scheduling);

		schedulingVencido = new Scheduling(id, vencido, doctorEntity, patientEntity, descricao, active);

		list.add(schedulingVencido);

		schedulingRecordList = new SchedulingList(id, descricao, plusDays, doctorId, patientId, active);

		schedulingRecordListVencido = new SchedulingList(id, descricao, vencido, doctorId, patientId, false);

//		optional = Optional.of(patientEntity);

		patientDados = new PatientDados(patientEntity);

		patientAdd = new PatientAdd(patientId, namePatient, cpf);
		
		patientList = new PatientList(patientId, namePatient, active);
		
		patientListDesativado = new PatientList(patientId, namePatient, false);

		listRecord.add(patientList);

		listRecord.add(patientListDesativado);

		listPaginado = new PageImpl<PatientList>(listRecord);
		
		listRecordScheduling.add(schedulingRecordList);
		
		listRecordScheduling.add(schedulingRecordListVencido);
		
		listPaginadoScheduling = new PageImpl<>(listRecordScheduling);
		
		patientEdit = new PatientEdit(null, "pedro", null);
		

	}
}
