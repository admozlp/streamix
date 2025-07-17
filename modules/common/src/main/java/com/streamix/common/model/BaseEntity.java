package com.streamix.common.model;

import jakarta.persistence.*;

import java.util.UUID;

@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 100, nullable = false)
    private UUID id;

    private boolean deleted;

    public BaseEntity(UUID id, boolean deleted) {
        this.id = id;
        this.deleted = deleted;
    }

    public BaseEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
