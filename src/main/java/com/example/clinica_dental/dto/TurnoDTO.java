package com.example.clinica_dental.dto;

import java.time.LocalDate;

public class TurnoDTO {
    //atributos
    private Long id;
    private Long odontologo_id;
    private Long paciente_id;
    private LocalDate fecha;

    public TurnoDTO() {
    }

    public TurnoDTO(Long id, Long odontologo_id, Long paciente_id, LocalDate fecha) {
        this.id = id;
        this.odontologo_id = odontologo_id;
        this.paciente_id = paciente_id;
        this.fecha = fecha;
    }

    //getters & setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOdontologo_id() {
        return odontologo_id;
    }

    public void setOdontologo_id(Long odontologo_id) {
        this.odontologo_id = odontologo_id;
    }

    public Long getPaciente_id() {
        return paciente_id;
    }

    public void setPaciente_id(Long paciente_id) {
        this.paciente_id = paciente_id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
