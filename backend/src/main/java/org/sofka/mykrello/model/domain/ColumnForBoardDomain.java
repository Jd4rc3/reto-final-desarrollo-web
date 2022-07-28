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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.time.Instant;

@Data
@Entity
@JsonIgnoreProperties({"createdAt", "updatedAt", "id"})
@Table(name = "krl_column_for_board")
public class ColumnForBoardDomain {
    @PreUpdate
    public void preUpdate() {
        if (this.updatedAt == null)
            this.updatedAt = Instant.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cfb_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = BoardDomain.class, optional = false,
            cascade = CascadeType.DETACH)
    @JoinColumn(name = "brd_id_board", referencedColumnName = "brd_id", nullable = false)
    @JsonBackReference(value = "columnsForBoard")
    private BoardDomain board;

    @Column(name = "cfb_created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ColumnDomain.class, optional = false,
            cascade = CascadeType.DETACH)
    @JoinColumn(name = "clm_id_column", referencedColumnName = "clm_id", nullable = false)
    @JsonManagedReference(value = "column-columnForBoard")
    private ColumnDomain column;

    @Column(name = "cfb_updated_at")
    private Instant updatedAt;

}
