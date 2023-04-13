package com.example.clinica_dental.service;

import com.example.clinica_dental.dto.TurnoDTO;
import com.example.clinica_dental.entities.Turno;
import com.example.clinica_dental.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ITurnoService {

    TurnoDTO guardar(TurnoDTO turno);
    Optional<TurnoDTO> busquedaXid(Long id) throws ResourceNotFoundException;
    TurnoDTO actualizarTurno(TurnoDTO turnoDTO) throws Exception;
    void borrarTurno(Long id) throws ResourceNotFoundException;
    List<TurnoDTO> buscarTodos();
}