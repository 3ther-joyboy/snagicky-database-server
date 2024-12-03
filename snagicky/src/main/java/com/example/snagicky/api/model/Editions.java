package com.example.snagicky.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "editions")
public class Editions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long Id;
    @Column(name = "name",nullable = false)
    public String Name;

    @JsonIgnore
    @ManyToMany(mappedBy = "Editions")
    public Set<Card> Cards;

    public Editions(String name){
        this.Name = name;
    }

    public Editions() {
    }
}
