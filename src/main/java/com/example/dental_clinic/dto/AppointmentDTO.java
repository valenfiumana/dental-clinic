package com.example.dental_clinic.dto;

import java.time.LocalDate;

public class AppointmentDTO {
    private Long id;
    private Long dentist_id;
    private Long patient_id;
    private LocalDate date;

    //empty constructor
    public AppointmentDTO() {
    }

    //constructor
    public AppointmentDTO(Long id, Long dentist_id, Long patient_id, LocalDate date) {
        this.id = id;
        this.dentist_id = dentist_id;
        this.patient_id = patient_id;
        this.date = date;
    }

    //getters & setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDentist_id() {
        return dentist_id;
    }

    public void setDentist_id(Long dentist_id) {
        this.dentist_id = dentist_id;
    }

    public Long getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Long patient_id) {
        this.patient_id = patient_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
