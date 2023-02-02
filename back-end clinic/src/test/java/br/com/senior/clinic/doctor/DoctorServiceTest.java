package br.com.senior.clinic.doctor;

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
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import br.com.senior.clinic.patient.Patient;
import br.com.senior.clinic.scheduling.Scheduling;
import br.com.senior.clinic.scheduling.SchedulingList;
import br.com.senior.clinic.scheduling.SchedulingRepository;
@SpringBootTest
class DoctorServiceTest {

	private final int id = 1;
	private final int patientId = 1;
	private final int doctorId = 1;
	private final boolean active = true;
	private final String namePatient = "filipe";
	private final String nameDoctor = "rafael";
	private final String cpf = "822.883.990-1";
	private final String crm = "12345sc";
	private final String descricao = "Consulta De Vista";
	private final LocalDateTime plusDays = LocalDateTime.now().plusDays(1);
	private final LocalDateTime vencido = LocalDateTime.now().minusDays(2);
	private final PageRequest paginacao = PageRequest.of(1, 10);

	
	@InjectMocks
	private DoctorService doctorService;

	@Mock
	private DoctorRepository doctorRepository;

	@Mock
	private SchedulingRepository schedulingRepository;

	private Doctor doctorEntity;
	
	private Doctor doctorEntityDesativado;

	private Optional<Doctor> optional;

	private Patient patientEntity;

	private DoctorDados doctorDados;

	private DoctorAdd doctorAdd;

	private DoctorList doctorList;
	
	private DoctorList doctorListDesativado;

	private DoctorEdit doctorEdit;

	private Scheduling scheduling;

	private Scheduling schedulingVencido;

	private SchedulingList schedulingRecordList;

	private SchedulingList schedulingRecordListVencido;

	private List<Scheduling> list;
	
	private Page<SchedulingList> listPaginadoScheduling;

	private List<SchedulingList> listRecordScheduling;
	
	private List<Doctor> listdoctors;

	private Page<DoctorList> listPaginado;

	private List<DoctorList> listRecord;
	
	@BeforeEach
	public void initi() {
		startDoctor();

	}
	

	@Test
	void findById() {
		when(doctorRepository.findById(1)).thenReturn(optional);
		DoctorDados response = doctorService.findById(1);
		assertEquals(DoctorDados.class, response.getClass());
	}

	@Test
	void add() {
		when(doctorRepository.save(any())).thenReturn(doctorEntity);
		when(doctorRepository.getReferenceById(anyInt())).thenReturn(doctorEntity);
		doctorService.add(doctorAdd);
		DoctorDados response = new DoctorDados(doctorRepository.getReferenceById(doctorEntity.getId()));
		assertNotNull(response);
		assertEquals(doctorId, response.id());
		assertEquals(nameDoctor, response.name());
		assertEquals(crm, response.crm());
		assertEquals(active, response.ativo());

	}

	@Test
	void listar() {
		when(doctorRepository.findAllByAtivoTrue(paginacao)).thenReturn(listPaginado);
		
		Page<DoctorList> response = doctorService.findAllByAtivoTrue(paginacao);

		assertEquals(2, response.getSize());
	}

	@Test
	void edit() {
		when(doctorRepository.getReferenceById(anyInt())).thenReturn(doctorEntity);
		doctorService.edit(1, doctorEdit);
		
		assertEquals(doctorEdit.name(), doctorEntity.getName());
		
	}

	@Test
	void delete() {
		when(doctorRepository.findById(anyInt())).thenReturn(optional);
		when(doctorRepository.getReferenceById(anyInt())).thenReturn(doctorEntity);
		when(schedulingRepository.findByDoctorIdAndAtivoTrue(anyInt())).thenReturn(Collections.emptyList());
		when(doctorRepository.findByIdAndAtivoFalse(anyInt())).thenReturn(null);
		
		assertNotNull(doctorService.delete(1));
		

	}

	@Test
	void listarDesativados() {
		when(doctorRepository.findAllByAtivoFalse(any())).thenReturn(listPaginado);
		Page<DoctorList> response = doctorService.findAllByAtivoFalse(paginacao);
		List<DoctorList> test = new ArrayList<>();
		
		response.filter(i -> i.ativo() == false)
		.forEach(i -> test.add(i));
		
		assertFalse(test.get(0).ativo());
	}

	@Test
	void listarAgendamentoAberto() {

		when(schedulingRepository.findAllByDoctorIdAndAtivoTrue(any(), any())).thenReturn(listPaginadoScheduling);
		Page<SchedulingList> response = doctorService.listarAgendamentoAberto(paginacao, 1);
		List<SchedulingList> test = new ArrayList<>();
		
		response.filter(i -> i.ativo() == true)
		.forEach(i -> test.add(i));
		
		assertTrue(test.get(0).ativo());
		
	}

	@Test
	void listarAgendamentoFechado() {

		when(schedulingRepository.findAllByDoctorIdAndAtivoFalse(any(), any())).thenReturn(listPaginadoScheduling);
		Page<SchedulingList> response = doctorService.listarAgendamentoFechado(paginacao, 1);
		List<SchedulingList> test = new ArrayList<>();
		
		response.filter(i -> i.ativo() == false)
		.forEach(i -> test.add(i));
		
		assertFalse(test.get(0).ativo());
		
		
	}

	@Test
	void ativarPatient() {
		when(doctorRepository.findByIdAndAtivoFalse(anyInt())).thenReturn(doctorEntityDesativado);
		when(doctorRepository.getReferenceById(anyInt())).thenReturn(doctorEntityDesativado);
		doctorService.ativarDoctor(1);
		Doctor response = doctorRepository.getReferenceById(1);
		assertTrue(response.getAtivo());
	}

	
	private void startDoctor() {

		list = new ArrayList<>();

		listRecord = new ArrayList<>();
		
		listRecordScheduling = new ArrayList<>();

		patientEntity = new Patient(patientId, namePatient, cpf, list, active);
		
		doctorEntityDesativado = new Doctor(patientId, namePatient, cpf, list, false);

		doctorEntity = new Doctor(doctorId, nameDoctor, crm, list, active);

		scheduling = new Scheduling(id, plusDays, doctorEntity, patientEntity, descricao, active);

		list.add(scheduling);

		schedulingVencido = new Scheduling(id, vencido, doctorEntity, patientEntity, descricao, active);

		list.add(schedulingVencido);

		schedulingRecordList = new SchedulingList(id, descricao, plusDays, doctorId, patientId, active);

		schedulingRecordListVencido = new SchedulingList(id, descricao, vencido, doctorId, patientId, false);

		optional = Optional.of(doctorEntity);

		doctorDados = new DoctorDados(doctorEntity);

		doctorAdd = new DoctorAdd(doctorId, nameDoctor, crm);
		
		doctorList = new DoctorList(doctorId, nameDoctor, active);
		
		doctorListDesativado = new DoctorList(doctorId, nameDoctor, false);

		listRecord.add(doctorList);

		listRecord.add(doctorListDesativado);

		listPaginado = new PageImpl<DoctorList>(listRecord);
		
		listRecordScheduling.add(schedulingRecordList);
		
		listRecordScheduling.add(schedulingRecordListVencido);
		
		listPaginadoScheduling = new PageImpl<>(listRecordScheduling);
		
		doctorEdit = new DoctorEdit(null, "pedro", null, null);
		

	}
	
}
