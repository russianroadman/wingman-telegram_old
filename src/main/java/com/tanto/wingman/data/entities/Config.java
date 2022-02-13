package com.tanto.wingman.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Config{

    @Id
    @Column(updatable = false)
    @GeneratedValue
    private UUID id;

    @Column(unique = true, nullable = false)
    private String key;

    @Column(nullable = false)
    private String value;

    public UUID getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
