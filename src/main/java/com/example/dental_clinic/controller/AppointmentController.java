package com.example.dental_clinic.controller;

import com.example.dental_clinic.dto.AppointmentDTO;
import com.example.dental_clinic.exceptions.ResourceNotFoundException;
import com.example.dental_clinic.services.AppointmentService;
import com.example.dental_clinic.services.DentistService;
import com.example.dental_clinic.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final PatientService patientService;
    private final DentistService dentistService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, PatientService patientService, DentistService dentistService) {
        this.appointmentService = appointmentService;
        this.patientService = patientService;
        this.dentistService = dentistService;
    }

    @PostMapping
    public ResponseEntity<AppointmentDTO> saveAppointment(@RequestBody AppointmentDTO appointmentDTO) throws ResourceNotFoundException {
        ResponseEntity<AppointmentDTO> response;
        if (dentistService.findById(appointmentDTO.getDentist_id()).isPresent()
                &&patientService.findById(appointmentDTO.getPatient_id()).isPresent()){
            response=ResponseEntity.ok(appointmentService.save(appointmentDTO));
        }
        else{
            response=ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @GetMapping()
    public ResponseEntity<List<AppointmentDTO>> findAllAppointments(){
        return ResponseEntity.ok(appointmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> findAppointment(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<AppointmentDTO> appointmentDTO=appointmentService.findById(id);
        if (appointmentDTO.isPresent()){
            return ResponseEntity.ok(appointmentDTO.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id) throws ResourceNotFoundException {
        appointmentService.delete(id);
        return ResponseEntity.ok("The appointment with id "+id+" has been deleted");
    }
    @PutMapping
    public ResponseEntity<AppointmentDTO> updateAppointment(@RequestBody AppointmentDTO appointmentDTO) throws Exception {
        ResponseEntity<AppointmentDTO> response;
        if (appointmentService.findById(appointmentDTO.getId()).isPresent()){
            if (appointmentService.findById(appointmentDTO.getDentist_id()).isPresent()
                    &&patientService.findById(appointmentDTO.getPatient_id()).isPresent()){
                response=ResponseEntity.ok(appointmentService.update(appointmentDTO));
            }
            else{
                response=ResponseEntity.badRequest().build();
            }
        }
        else{
            response=ResponseEntity.badRequest().build();
        }
        return response;
    }

}