package com.tanto.wingman.data.entities;

import com.tanto.wingman.data.IssueStatus;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class Issue {

    @Id
    @Column(updatable = false)
    @GeneratedValue
    private UUID id;

    @Column(unique = true, nullable = false, updatable = false, length = 10)
    private String code;

    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @Column
    private Date acceptedAt;

    @Column
    private Date closedAt;

    @Column(nullable = false)
    private IssueStatus status;

    @JoinColumn(nullable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Account client;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Account employee;

    @JoinColumn(nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Department department;

    public UUID getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getAcceptedAt() {
        return acceptedAt;
    }

    public void setAcceptedAt(Date acceptedAt) {
        this.acceptedAt = acceptedAt;
    }

    public Date getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Date closedAt) {
        this.closedAt = closedAt;
    }

    public IssueStatus getStatus() {
        return status;
    }

    public void setStatus(IssueStatus status) {
        this.status = status;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Account getClient() {
        return client;
    }

    public void setClient(Account client) {
        this.client = client;
    }

    public Account getEmployee() {
        return employee;
    }

    public void setEmployee(Account employee) {
        this.employee = employee;
    }
}
