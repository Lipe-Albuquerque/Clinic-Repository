package br.com.senior.clinic.scheduling;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record SchedulingEdit(String description,@JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime dataConsulta, Integer doctor_id	) {

}
