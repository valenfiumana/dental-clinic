package com.example.clinica_dental.controller;

import com.example.clinica_dental.entities.Odontologo;
import com.example.clinica_dental.exceptions.ResourceNotFoundException;
import com.example.clinica_dental.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    //inyecto service por constructor
    private final OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    //métodos
    //GUARDAR
    @PostMapping
    public Odontologo registrarOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.guardar(odontologo);
    }

    //BUSCAR X ID
    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologo=odontologoService.busquedaXid(id);
        if (odontologo.isPresent()){
            return ResponseEntity.ok(odontologo.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    //BUSCAR TODOS
    @GetMapping
    public List<Odontologo> buscarTodosOdontologos(){
        return odontologoService.buscarTodos();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologo=odontologoService.busquedaXid(id);
        if (odontologo.isPresent()){
            odontologoService.borrar(id);
            return ResponseEntity.ok("Se eliminó al odontólogo con id="+id);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo borrar por no existir el id "+id);
        }
    }

    //ACTUALIZAR
    @PutMapping
    public ResponseEntity<Odontologo> actualizarOdontologo(@RequestBody Odontologo odontologo) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado=odontologoService.busquedaXid(odontologo.getId());
        if (odontologoBuscado.isPresent()){
            Odontologo odontologoActualizado=odontologoService.actualizar(odontologo);
            return ResponseEntity.ok(odontologoActualizado);
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }
}
