package com.example.snagicky.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long Id;

    @Column(name = "name",nullable = false)
    public String Name;
    @Column(name = "description")
    public String Description;
    @Column(name = "story")
    public String Story;
    @Column(name = "Rarity",nullable = false)
    public String Rarity;
    @Column(name = "note")
    public String Note;

    @JsonIgnoreProperties({"Cards"})
    @ManyToMany()
    @JoinTable(
            name = "card_passives",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "passive_id")
    )
    public Set<Passives> Passives;

    @JsonIgnoreProperties({"Cards"})
    @ManyToMany()
    @JoinTable(
            name = "card_edition",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "edition_id")
    )
    public Set<Editions> Editions;

    @ManyToOne
    @JsonIgnoreProperties({"Cards"})
    @JoinColumn(name = "type",nullable = false)
    public Types Type;

    @Column(name = "attack")
    public Integer Attack;
    @Column(name = "defense")
    public Integer Defense;

    @Column(name = "multi",nullable = false)
    public int Multi;
    @Column(name = "red",nullable = false)
    public int Red;
    @Column(name = "blue",nullable = false)
    public int Blue;
    @Column(name = "green",nullable = false)
    public int Green;
    @Column(name = "white",nullable = false)
    public int White;
    @Column(name = "cost")
    public String Cost;

    @Column(name = "background",nullable = false)
    public int Background;
    @Column(name = "creator")
    public String Creator;
    @Column(name = "image")
    public String Image;

    @CreationTimestamp
    private java.sql.Timestamp Created;
    @UpdateTimestamp
    private java.sql.Timestamp Updated;

    public Card(){}
}
