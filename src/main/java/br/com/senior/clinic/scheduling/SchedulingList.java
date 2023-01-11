package br.com.senior.clinic.scheduling;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record SchedulingList(Integer id,String description ,@JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime dataConsulta, Integer doctor_id, Integer patient_id, Boolean ativo) {
	
}
