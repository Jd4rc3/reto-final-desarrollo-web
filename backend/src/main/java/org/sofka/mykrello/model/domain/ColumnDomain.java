package org.sofka.mykrello.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(fetch = FetchType.LAZY, targetEntity = ColumnForBoardDomain.class, cascade =
            CascadeType.ALL, mappedBy = "column")
    @JsonBackReference(value = "column-columnForBoard")
    private List<ColumnForBoardDomain> columnForBoards = new ArrayList<>();

    @OneToMany(targetEntity = TaskDomain.class, fetch = FetchType.EAGER, mappedBy = "column")
    @JsonManagedReference(value = "column-task")
    private List<TaskDomain> tasks = new ArrayList<>();
}