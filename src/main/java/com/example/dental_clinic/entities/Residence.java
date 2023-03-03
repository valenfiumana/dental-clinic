package com.example.dental_clinic.entities;

import javax.persistence.*;

@Entity
@Table(name = "residences")
public class Residence {
    //attributes --> Id + Columns
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String street;
    @Column
    private String number;
    @Column
    private String city;
    @Column
    private String state;

    //unión con paciente
    @OneToOne(mappedBy = "residence")
    private Patient patient;

    //getters & setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
}
