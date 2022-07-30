package org.sofka.mykrello.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
 * Column domain is a domain class that represents a column in a board.
 */
@Data
@Entity
@JsonIgnoreProperties({"createdAt", "updatedAt"})
@Table(name = "krl_column")
public class ColumnDomain {
    /**
     * preUpdate is a method that is called before the update of a column.
     */
    @PreUpdate
    public void preUpdate() {
        if (this.updatedAt == null)
            this.updatedAt = Instant.now();
    }

    /**
     * id is the primary key of the column.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clm_id", nullable = false)
    private Integer id;

    /**
     * name is the name of the column.
     */
    @Column(name = "clm_name", nullable = false, length = 100)
    private String name;

    /**
     * createdAt is the date when the column was created.
     */
    @Column(name = "clm_created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    /**
     * updatedAt is the date when the column was updated.
     */
    @Column(name = "clm_updated_at")
    private Instant updatedAt;
}