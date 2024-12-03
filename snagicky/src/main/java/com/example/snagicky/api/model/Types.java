package com.example.snagicky.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name = "types")
public class Types {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long Id;

    @Column(nullable = false,name = "name")
    public String Name;

    @Column(nullable = true,name = "card_type")
    public int CardType;

    @JsonIgnoreProperties({"Type"})
    @OneToMany(mappedBy = "Type")
    public Set<Card> Cards;

    public Types(String name, int cardType) {
        Name = name;
        CardType = cardType;
    }
    public Types() {}
}
