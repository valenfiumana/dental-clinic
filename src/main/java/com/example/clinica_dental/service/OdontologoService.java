package com.example.clinica_dental.service;

import com.example.clinica_dental.entities.Odontologo;
import com.example.clinica_dental.exceptions.ResourceNotFoundException;
import com.example.clinica_dental.repositories.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService{
    //creo repository y lo inyecto por constructor
    private OdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    //métodos
    public Odontologo guardar(Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }
    public List<Odontologo> buscarTodos(){
        return odontologoRepository.findAll();
    }
    public Optional<Odontologo> busquedaXid(Long id) throws ResourceNotFoundException{
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        if(odontologo.isPresent()){
            return odontologo;
        }
        else {
            throw new ResourceNotFoundException("El odontologo con id " + id + " no existe");
        }
    }

    public Odontologo actualizar(Odontologo odontologo) throws ResourceNotFoundException{
        Optional<Odontologo> odontologoAActualizar= odontologoRepository.findById(odontologo.getId());
        if (odontologoAActualizar.isPresent()){
            return odontologoRepository.save(odontologo);
        }
        else {
            throw new ResourceNotFoundException("No se pudo actualizar por no existir el id"+ odontologo.getId());
        }
    }
    public void borrar(Long id) throws ResourceNotFoundException{
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        if(odontologo.isPresent()){
            odontologoRepository.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("No se pudo borrar porque el odontologo con id " + id + " no existe");
        }
    }
}