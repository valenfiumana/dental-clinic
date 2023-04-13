package com.example.clinica_dental.service;

import com.example.clinica_dental.dto.TurnoDTO;
import com.example.clinica_dental.entities.Odontologo;
import com.example.clinica_dental.entities.Paciente;
import com.example.clinica_dental.entities.Turno;
import com.example.clinica_dental.exceptions.ResourceNotFoundException;
import com.example.clinica_dental.repositories.TurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService{
    //inyecto repository por constructor
    private final TurnoRepository turnoRepository;
    @Autowired
    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    @Autowired
    ObjectMapper mapper;


    @Override
    public TurnoDTO guardar(TurnoDTO turno) {
        Turno turnoEntity= new Turno();

        Paciente paciente= new Paciente();
        paciente.setId(turno.getPaciente_id());

        Odontologo odontologo= new Odontologo();
        odontologo.setId(turno.getOdontologo_id());


        turnoEntity.setFecha(turno.getFecha());
        turnoEntity.setPaciente(paciente);
        turnoEntity.setOdontologo(odontologo);

        Turno turnoGuardado=turnoRepository.save(turnoEntity);

        TurnoDTO turnoADevolver= new TurnoDTO();
        turnoADevolver.setFecha(turnoGuardado.getFecha());
        turnoADevolver.setOdontologo_id(turnoGuardado.getOdontologo().getId());
        turnoADevolver.setPaciente_id(turnoGuardado.getPaciente().getId());
        turnoADevolver.setId(turnoGuardado.getId());

        return turnoADevolver;
    }

    @Override
    public Optional<TurnoDTO> busquedaXid(Long id) throws ResourceNotFoundException {
        Optional<Turno> turnoBuscado=turnoRepository.findById(id);
        Optional<TurnoDTO> turnoDTOBuscado;
        if (turnoBuscado.isPresent()){
            Turno turnoRealBuscado=turnoBuscado.get();
            TurnoDTO turnoDTOADevolver= new TurnoDTO();
            turnoDTOADevolver.setId(turnoRealBuscado.getId());
            turnoDTOADevolver.setFecha(turnoRealBuscado.getFecha());
            turnoDTOADevolver.setPaciente_id(turnoRealBuscado.getPaciente().getId());
            turnoDTOADevolver.setOdontologo_id(turnoRealBuscado.getOdontologo().getId());
            turnoDTOBuscado=Optional.of(turnoDTOADevolver);
        }
        else{
            throw new ResourceNotFoundException("El turno con id "+id+" no existe");
        }
        return turnoDTOBuscado;
    }

    @Override
    public TurnoDTO actualizarTurno(TurnoDTO turnoDTO) throws Exception {
        Optional<Turno> turnoEncontrado = turnoRepository.findById(turnoDTO.getId());
        if(turnoEncontrado.isPresent()){
            return guardar(turnoDTO);
        }
        else{
            throw new Exception("No se actualizo porque el turno con id "+turnoDTO.getId()+" no existe");
        }
    }

    @Override
    public void borrarTurno(Long id) throws ResourceNotFoundException {
        Optional<Turno> turno = turnoRepository.findById(id);
        if(turno.isPresent()){
            turnoRepository.deleteById(id);
        }
        else{
            throw new ResourceNotFoundException("No se pudo borrar porque el turno con id "+id+" no existe");
        }
    }

    @Override
    public List<TurnoDTO> buscarTodos() {
        List<Turno> turnos = turnoRepository.findAll();
        List<TurnoDTO> turnosDTO = new ArrayList<>();
        for(Turno turno : turnos){
            turnosDTO.add(new TurnoDTO(turno.getId(), turno.getOdontologo().getId(), turno.getPaciente().getId(), turno.getFecha()));
        }
        return turnosDTO;
    }
}