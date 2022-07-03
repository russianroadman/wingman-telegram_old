package com.tanto.wingman.data.entities;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
public class Account {

    @Id
    @Column(updatable = false)
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column
    private String patronymic;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false, unique = true)
    private String chatId;

    @Column
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Department> departments;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Issue current;

    @Column
    private Boolean isClient;

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public Issue getCurrent() {
        return current;
    }

    public void setCurrent(Issue current) {
        this.current = current;
    }

    public Boolean getClient() {
        return isClient;
    }

    public void setClient(Boolean client) {
        isClient = client;
    }
}
