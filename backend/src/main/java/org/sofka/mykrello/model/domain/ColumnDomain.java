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

@Data
@Entity
@JsonIgnoreProperties({"createdAt", "updatedAt"})
@Table(name = "krl_column")
public class ColumnDomain {
    @PreUpdate
    public void preUpdate() {
        if (this.updatedAt == null)
            this.updatedAt = Instant.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clm_id", nullable = false)
    private Integer id;

    @Column(name = "clm_name", nullable = false, length = 100)
    private String name;

    @Column(name = "clm_created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    @Column(name = "clm_updated_at")
    private Instant updatedAt;
}