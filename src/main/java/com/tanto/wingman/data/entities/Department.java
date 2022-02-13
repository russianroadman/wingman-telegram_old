package com.tanto.wingman.data.entities;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
public class Department {

    @Id
    @Column(updatable = false)
    @GeneratedValue
    private UUID id;

    @Column(unique = true, nullable = false)
    private String name;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
