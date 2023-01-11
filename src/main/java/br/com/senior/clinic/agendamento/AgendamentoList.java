package br.com.senior.clinic.agendamento;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public record AgendamentoList(Integer id,String description ,@JsonFormat(pattern = "dd/MM/yyyy") LocalDate dataConsulta, Integer doctor_id, Integer patient_id, Boolean ativo) {
	
}
