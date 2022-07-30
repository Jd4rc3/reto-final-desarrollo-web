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

/**
 * Board domain class is used to represent a board in the database.
 */
@Data
@Entity
@Table(name = "krl_board")
public class BoardDomain {
    /**
     * preUpdate is used to set a timestamp when the board is updated if updatedAt is not set.
     */
    @PreUpdate
    public void preUpdate() {
        if (this.updatedAt == null)
            this.updatedAt = Instant.now();
    }

    /**
     * The id of the board.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brd_id", nullable = false)
    private Integer id;

    /**
     * The name of the board.
     */
    @Column(name = "brd_name", nullable = false, length = 100)
    private String name;

    /**
     * Timestamp when the board was created.
     */
    @Column(name = "brd_created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    /**
     * Timestamp when the board was updated.
     */
    @Column(name = "brd_updated_at")
    private Instant updatedAt;
}