package com.example.dental_clinic.services;

import com.example.dental_clinic.dto.AppointmentDTO;
import com.example.dental_clinic.exceptions.ResourceNotFoundException;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

public interface IAppointmentService {
    AppointmentDTO save(AppointmentDTO appointmentDTO);
    Optional<AppointmentDTO> findById(Long id) throws ResourceNotFoundException;
    AppointmentDTO update(AppointmentDTO appointmentDTO) throws Exception;
    void delete(Long id) throws ResourceNotFoundException;
    List<AppointmentDTO> findAll();
}
