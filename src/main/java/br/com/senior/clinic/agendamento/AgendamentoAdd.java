package br.com.senior.clinic.agendamento;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record AgendamentoAdd(Integer id,@JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime dataConsulta, Integer doctor, Integer patient, String description) {

	

	
}
