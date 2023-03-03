package com.example.dental_clinic.services;

import com.example.dental_clinic.entities.Dentist;
import com.example.dental_clinic.exceptions.ResourceNotFoundException;
import com.example.dental_clinic.repositories.DentistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistService {
    private DentistRepository dentistRepository;

    @Autowired
    public DentistService(DentistRepository dentistRepository){
        this.dentistRepository = dentistRepository;
    }

    public Dentist save(Dentist dentist){
        return dentistRepository.save(dentist);
    }
    public List<Dentist> findAll(){
        return dentistRepository.findAll();
    }
    public Optional<Dentist> findById(Long id) throws ResourceNotFoundException {
        Optional<Dentist> dentist = dentistRepository.findById(id);
        if(dentist.isPresent()){
            return dentist;
        }
        else{
            throw new ResourceNotFoundException("The dentist with id "+ id+" does not exist");
        }
    }
    public Dentist update(Dentist dentist) throws ResourceNotFoundException{
        Optional<Dentist> dentistUpdated = dentistRepository.findById(dentist.getId());
        if(dentistUpdated.isPresent()){
            return dentistRepository.save(dentist);
        }
        else{
            throw new ResourceNotFoundException("The dentist with id "+ dentist.getId()+" does not exist, so it can´t be updated");
        }
    }

    public void delete(Long id) throws ResourceNotFoundException{
        Optional<Dentist> dentist = dentistRepository.findById(id);
        if(dentist.isPresent()){
            dentistRepository.deleteById(id);
        }
        else{
            throw new ResourceNotFoundException("The dentist with id "+ id+" does not exist, so it can´t be deleted");
        }
    }
}
