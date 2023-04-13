package com.example.clinica_dental.service;

import com.example.clinica_dental.entities.Paciente;
import com.example.clinica_dental.exceptions.ResourceNotFoundException;
import com.example.clinica_dental.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    //inyecto repo por constructor
    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    //métodos
    public Paciente guardar(Paciente paciente){
        return pacienteRepository.save(paciente);
    }
    public Optional<Paciente> busquedaXid(Long id) throws ResourceNotFoundException {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if(paciente.isPresent()){
            return paciente;
        }
        else{
            throw new ResourceNotFoundException("No existe paciente con id "+id);
        }
    }
    public List<Paciente> buscarTodos(){
        return pacienteRepository.findAll();
    }
    public Optional<Paciente> buscarXemail(String email) throws ResourceNotFoundException{
        Optional<Paciente> paciente = pacienteRepository.findByEmail(email);
        if(paciente.isPresent()){
            return paciente;
        }
        else{
            throw new ResourceNotFoundException("No existe paciente con email "+email);
        }
    }
    public void borrar(Long id) throws ResourceNotFoundException{
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if(paciente.isPresent()){
            pacienteRepository.deleteById(id);
        }
        else{
            throw new ResourceNotFoundException("No se pudo borrar porque el paciente con id "+id+" no existe");
        }
    }
    public Paciente actualizar(Paciente paciente) throws ResourceNotFoundException {
        Optional<Paciente> pacienteAActualizar= pacienteRepository.findById(paciente.getId());
        if (pacienteAActualizar.isPresent()){
            return pacienteRepository.save(paciente);
        }
        else {
            throw new ResourceNotFoundException("No se pudo actualizar por no existir el id"+ paciente.getId());
        }
    }
}