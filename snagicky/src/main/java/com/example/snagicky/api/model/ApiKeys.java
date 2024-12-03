package com.example.snagicky.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "api_keys")
public class ApiKeys {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long Id;

    @Column(name = "api_key", nullable = false)
    public String Key;

    @Column(name = "info")
    public String Info;
}
