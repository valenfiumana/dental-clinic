package com.example.dental_clinic.services;

import com.example.dental_clinic.dto.AppointmentDTO;
import com.example.dental_clinic.entities.Appointment;
import com.example.dental_clinic.entities.Dentist;
import com.example.dental_clinic.entities.Patient;
import com.example.dental_clinic.exceptions.ResourceNotFoundException;
import com.example.dental_clinic.repositories.AppointmentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService implements IAppointmentService{
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository){
        this.appointmentRepository = appointmentRepository;
    }

    @Autowired
    ObjectMapper mapper;

    @Override
    public AppointmentDTO save(AppointmentDTO appointmentDTO) {
        Appointment appointment = new Appointment();

        Patient patient = new Patient();
        patient.setId(appointmentDTO.getPatient_id());

        Dentist dentist = new Dentist();
        dentist.setId(appointmentDTO.getDentist_id());

        appointment.setDate(appointmentDTO.getDate());
        appointment.setPatient(patient);
        appointment.setDentist(dentist);

        Appointment savedAppointment = appointmentRepository.save(appointment);

        AppointmentDTO appointmentToReturn = new AppointmentDTO();
        appointmentToReturn.setDate(savedAppointment.getDate());
        appointmentToReturn.setDentist_id(savedAppointment.getDentist().getId());
        appointmentToReturn.setPatient_id(savedAppointment.getPatient().getId());
        appointmentToReturn.setId(savedAppointment.getId());
        return appointmentToReturn;
    }

    @Override
    public Optional<AppointmentDTO> findById(Long id) throws ResourceNotFoundException {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        Optional<AppointmentDTO> appointmentDTO;
        if(appointment.isPresent()){
            Appointment appointmentWanted = appointment.get();
            AppointmentDTO appDtoToReturn = new AppointmentDTO();
            appDtoToReturn.setId(appointmentWanted.getId());
            appDtoToReturn.setDate(appointmentWanted.getDate());
            appDtoToReturn.setPatient_id(appointmentWanted.getPatient().getId());
            appDtoToReturn.setDentist_id(appointmentWanted.getDentist().getId());
            appointmentDTO=Optional.of(appDtoToReturn);
        }
        else{
            throw new ResourceNotFoundException("The appointment with id "+id+" does not exist");
        }
        return appointmentDTO;
    }

    @Override
    public AppointmentDTO update(AppointmentDTO appointmentDTO) throws Exception {
        Optional<Appointment> appointment = appointmentRepository.findById(appointmentDTO.getId());
        if(appointment.isPresent()){
            return save(appointmentDTO);
        }
        else{
            throw new Exception("The appointment with id "+appointmentDTO.getId()+" does not exist, so It couldn't be updated");
        }
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if(appointment.isPresent()){
            appointmentRepository.deleteById(id);
        }
        else{
            throw new ResourceNotFoundException("It could not be deleted as the appointment with id "+id+" does not exist");
        }
    }

    @Override
    public List<AppointmentDTO> findAll() {
        List<Appointment> appointments = appointmentRepository.findAll();
        List<AppointmentDTO> appointmentDTOs = new ArrayList<>();
        for(Appointment appointment : appointments){
            appointmentDTOs.add(new AppointmentDTO(appointment.getId(), appointment.getDentist().getId(),
                    appointment.getPatient().getId(), appointment.getDate()));
        }
        return appointmentDTOs;
    }
}
