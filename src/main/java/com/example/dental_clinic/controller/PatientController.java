package com.example.dental_clinic.controller;

import com.example.dental_clinic.entities.Patient;
import com.example.dental_clinic.exceptions.ResourceNotFoundException;
import com.example.dental_clinic.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    //Requests
    //SAVE
    @PostMapping
    public Patient savePatient(@RequestBody Patient patient){
        return patientService.save(patient);
    }

    //FIND BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Patient> findPatient(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Patient> patient = patientService.findById(id);
        if(patient.isPresent()){
            return ResponseEntity.ok(patient.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    //FIND BY EMAIL
    @GetMapping("/email/{email}")
    public ResponseEntity<Patient> findPatientByEmail(@PathVariable String email) throws ResourceNotFoundException {
        Optional<Patient> patient = patientService.findByEmail(email);
        if(patient.isPresent()){
            return ResponseEntity.ok(patient.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    //FIND ALL
    @GetMapping
    public List<Patient> findAllPatients(){
        return patientService.findAll();
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePaciente(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Patient> patient = patientService.findById(id);
        if(patient.isPresent()){
            patientService.delete(id);
            return ResponseEntity.ok("The patient with id "+id+" has been deleted");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The patient couldn't be deleted because the id "+ id+" doesn't exist");
        }
    }
    //UPDATE
    @PutMapping
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient) throws ResourceNotFoundException {
        Optional<Patient> patientWanted = patientService.findById(patient.getId());
        if(patientWanted.isPresent()){
            Patient updatedPatient = patientService.update(patient);
            return ResponseEntity.ok(updatedPatient);
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }
}
