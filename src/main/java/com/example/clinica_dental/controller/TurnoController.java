package com.example.clinica_dental.controller;

import com.example.clinica_dental.dto.TurnoDTO;
import com.example.clinica_dental.entities.Turno;
import com.example.clinica_dental.exceptions.ResourceNotFoundException;
import com.example.clinica_dental.service.OdontologoService;
import com.example.clinica_dental.service.PacienteService;
import com.example.clinica_dental.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private final TurnoService turnoService;
    private final PacienteService pacienteService;
    private final OdontologoService odontologoService;

    @Autowired
    public TurnoController(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @PostMapping
    public ResponseEntity<TurnoDTO> guardarTurno(@RequestBody TurnoDTO turno) throws ResourceNotFoundException {
        ResponseEntity<TurnoDTO> respuesta;
        if (odontologoService.busquedaXid(turno.getOdontologo_id()).isPresent()
                &&pacienteService.busquedaXid(turno.getPaciente_id()).isPresent()){
            respuesta=ResponseEntity.ok(turnoService.guardar(turno));
        }
        else{
            respuesta=ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return respuesta;
    }

    @GetMapping()
    public ResponseEntity<List<TurnoDTO>> buscarTodosTurnos(){
        return ResponseEntity.ok(turnoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<TurnoDTO> turnoBuscado=turnoService.busquedaXid(id);
        if (turnoBuscado.isPresent()){
            return ResponseEntity.ok(turnoBuscado.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.borrarTurno(id);
        return ResponseEntity.ok("Se eliminó el turno con id="+id);
    }
    @PutMapping
    public ResponseEntity<TurnoDTO> actualizarTurno(@RequestBody TurnoDTO turno) throws Exception {
        ResponseEntity<TurnoDTO> respuesta;
        if (turnoService.busquedaXid(turno.getId()).isPresent()){
            if (odontologoService.busquedaXid(turno.getOdontologo_id()).isPresent()
                    &&pacienteService.busquedaXid(turno.getPaciente_id()).isPresent()){
                respuesta=ResponseEntity.ok(turnoService.actualizarTurno(turno));
            }
            else{
                respuesta=ResponseEntity.badRequest().build();
            }
        }
        else{
            respuesta=ResponseEntity.badRequest().build();
        }
        return respuesta;
    }

}