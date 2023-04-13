package com.example.clinica_dental.service;

import com.example.clinica_dental.entities.Odontologo;
import com.example.clinica_dental.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void guardar() {
        Odontologo odontologo=new Odontologo();
        odontologo.setNombre("Sherlock");
        odontologo.setApellido("Holmes");
        odontologo.setMatricula("677666");
        odontologo = odontologoService.guardar(odontologo);
        assertEquals(1, odontologo.getId());
    }

    @Test
    @Order(2)
    void buscarTodos() {
        List<Odontologo> odontologos = odontologoService.buscarTodos();
        assertEquals(1, odontologos.size());

    }

    @Test
    @Order(3)
    void busquedaXid() throws ResourceNotFoundException {
        Optional<Odontologo> odontologo = odontologoService.busquedaXid(1L);
        Odontologo odontologoEncontrado = odontologo.get();
        assertNotNull(odontologoEncontrado);
    }

    @Test
    void actualizar() {
    }

    @Test
    void borrar() throws ResourceNotFoundException {
        odontologoService.borrar(1L);
        List<Odontologo> odontologos = odontologoService.buscarTodos();
        assertEquals(0, odontologos.size());

    }
}