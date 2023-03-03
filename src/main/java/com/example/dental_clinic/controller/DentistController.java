package com.example.dental_clinic.controller;


import com.example.dental_clinic.entities.Dentist;
import com.example.dental_clinic.exceptions.ResourceNotFoundException;
import com.example.dental_clinic.services.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dentists")
public class DentistController {
    private final DentistService dentistService;

    @Autowired
    public DentistController(DentistService dentistService) {
        this.dentistService = dentistService;
    }

    //SAVE
    @PostMapping
    public Dentist saveDentist(@RequestBody Dentist dentist){
        return dentistService.save(dentist);
    }

    //FIND BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Dentist> findDentist(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Dentist> dentist=dentistService.findById(id);
        if (dentist.isPresent()){
            return ResponseEntity.ok(dentist.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    //FIND ALL
    @GetMapping
    public List<Dentist> findAllDentists(){
        return dentistService.findAll();
    }


    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDentist(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Dentist> dentist=dentistService.findById(id);
        if (dentist.isPresent()){
            dentistService.delete(id);
            return ResponseEntity.ok("The dentist with id "+id+" has been deleted");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("As the dentist with id "+id+" does not exist, It couldn't be deleted");
        }
    }

    //UPDATE
    @PutMapping
    public ResponseEntity<Dentist> updateDentist(@RequestBody Dentist dentist) throws ResourceNotFoundException {
        Optional<Dentist> foundDentist=dentistService.findById(dentist.getId());
        if (foundDentist.isPresent()){
            Dentist updatedDentist=dentistService.update(dentist);
            return ResponseEntity.ok(updatedDentist);
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }
}