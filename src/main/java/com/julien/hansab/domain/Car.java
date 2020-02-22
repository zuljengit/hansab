package com.julien.hansab.domain;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "numberplate")
    private String numberplate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumberplate() {
        return numberplate;
    }

    public void setNumberplate(String numberplate) {
        this.numberplate = numberplate;
    }
}
