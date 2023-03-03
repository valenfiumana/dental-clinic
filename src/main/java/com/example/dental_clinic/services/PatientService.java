package com.example.dental_clinic.services;

import com.example.dental_clinic.entities.Patient;
import com.example.dental_clinic.exceptions.ResourceNotFoundException;
import com.example.dental_clinic.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    @Autowired
    public PatientService(PatientRepository patientRepository){
        this.patientRepository=patientRepository;
    }
    public Patient save(Patient patient){
        return patientRepository.save(patient);
    }
    public Optional<Patient> findById(Long id) throws ResourceNotFoundException{
        Optional<Patient> patient = patientRepository.findById(id);
        if(patient.isPresent()){
            return patient;
        }
        else{
            throw new ResourceNotFoundException("The patient with id "+id+" does not exist");
        }
    }
    public List<Patient> findAll(){
        return patientRepository.findAll();
    }
    public Optional<Patient> findByEmail(String email) throws ResourceNotFoundException{
        Optional<Patient> patient = patientRepository.findByEmail(email);
        if(patient.isPresent()){
            return patient;
        }
        else{
            throw new ResourceNotFoundException("There is no patient with the email "+email);
        }
    }
    public void delete(Long id) throws ResourceNotFoundException{
        Optional<Patient> patient = patientRepository.findById(id);
        if(patient.isPresent()){
            patientRepository.deleteById(id);
        }
        else{
            throw new ResourceNotFoundException("The patient with id "+id+" doesn't exist so It couldn't be deleted");
        }
    }
    public Patient update(Patient patient) throws ResourceNotFoundException{
        Optional<Patient> patientUpdated = patientRepository.findById(patient.getId());
        if(patientUpdated.isPresent()){
            return patientRepository.save(patient);
        }
        else{
            throw new ResourceNotFoundException("The patient with id "+patient.getId()+" doesn't exist so It couldn't be updated");
        }
    }
}
