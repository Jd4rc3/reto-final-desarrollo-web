package org.sofka.mykrello.model.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.time.Instant;

@Data
@Entity
@Table(name = "krl_board")
public class BoardDomain {
    @PreUpdate
    public void preUpdate() {
        if (this.updatedAt == null)
            this.updatedAt = Instant.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brd_id", nullable = false)
    private Integer id;

    @Column(name = "brd_name", nullable = false, length = 100)
    private String name;

    @Column(name = "brd_created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    @Column(name = "brd_updated_at")
    private Instant updatedAt;
}