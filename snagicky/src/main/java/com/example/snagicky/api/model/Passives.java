package com.example.snagicky.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "passives")
public class Passives {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long Id;
    @Column(name = "name",nullable = false)
    public String Name;
    @Column(name = "description")
    public String Description;

    @JsonIgnore
    @ManyToMany(mappedBy = "Passives")
    public Set<Card> Cards;

    public Passives(String name, String description) {
        Name = name;
        Description = description;
    }
    public Passives() {}

}
