package com.example.clinica_dental.controller;

import com.example.clinica_dental.entities.Paciente;
import com.example.clinica_dental.exceptions.ResourceNotFoundException;
import com.example.clinica_dental.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    //inyecto service por constructor
    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    //Requests
    //GUARDAR
    @PostMapping
    public Paciente registrarPaciente(@RequestBody Paciente paciente){
        return pacienteService.guardar(paciente);
    }

    //BUSCAR X ID
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> findPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Paciente> paciente = pacienteService.busquedaXid(id);
        if(paciente.isPresent()){
            return ResponseEntity.ok(paciente.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    //BUSCAR X EMAIL
    @GetMapping("/email/{email}")
    public ResponseEntity<Paciente> findPacienteByEmail(@PathVariable String email) throws ResourceNotFoundException {
        Optional<Paciente> paciente = pacienteService.buscarXemail(email);
        if(paciente.isPresent()){
            return ResponseEntity.ok(paciente.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    //BUSCAR TODOS
    @GetMapping
    public List<Paciente> findAllPacientes(){
        return pacienteService.buscarTodos();
    }

    //BORRAR
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePaciente(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Paciente> paciente = pacienteService.busquedaXid(id);
        if(paciente.isPresent()){
            pacienteService.borrar(id);
            return ResponseEntity.ok("Se ha eliminado al paciente con id "+id);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo borrar al paciente porque " +
                    "no existen con id "+ id);
        }
    }
    //ACTUALIZAR
    @PutMapping
    public ResponseEntity<Paciente> actualizarPaciente(@RequestBody Paciente paciente) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado = pacienteService.busquedaXid(paciente.getId());
        if(pacienteBuscado.isPresent()){
            Paciente pacienteActualizado = pacienteService.actualizar(paciente);
            return ResponseEntity.ok(pacienteActualizado);
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }
}