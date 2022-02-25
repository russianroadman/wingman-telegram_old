package com.tanto.wingman.data.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(indexes = {
    @Index(name = "idx_message_issue_id", columnList = "issue_id, created_by_id")
})
public class Message {

    @Id
    @Column(updatable = false)
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn
    private Issue issue;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, updatable = false)
    private Account createdBy;

    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @Column(nullable = false, updatable = false)
    private String chatId;

    @Column(nullable = false, updatable = false)
    private Integer telegramMessageId;

    @Column(nullable = false)
    private Boolean readByReceiver;

    public UUID getId() {
        return id;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public Account getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Account createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public Integer getTelegramMessageId() {
        return telegramMessageId;
    }

    public void setTelegramMessageId(Integer messageId) {
        this.telegramMessageId = messageId;
    }

    public Boolean getReadByReceiver() {
        return readByReceiver;
    }

    public void setReadByReceiver(Boolean readByReceiver) {
        this.readByReceiver = readByReceiver;
    }
}
