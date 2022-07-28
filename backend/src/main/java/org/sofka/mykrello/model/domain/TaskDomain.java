package org.sofka.mykrello.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "krl_task")
public class TaskDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tsk_id", nullable = false)
    private Integer id;

    @JoinColumn(name = "clm_id_column", referencedColumnName = "clm_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JsonBackReference(value = "column-task")
    private ColumnDomain column;

    @Column(name = "brd_id_board")
    private Integer boardId;

    @Column(name = "tsk_name")
    private String name;

    @Column(name = "tsk_description")
    private String description;

    @Column(name = "tsk_delivery_date")
    private LocalDate deliveryDate;

    @Column(name = "tsk_created_at")
    private Instant createdAt = Instant.now();

    @Column(name = "tsk_updated_at")
    private Instant updatedAt;

    @JsonManagedReference(value = "log-taks")
    @OneToMany(fetch = FetchType.EAGER,targetEntity = LogDomain.class, cascade = CascadeType.ALL, mappedBy = "taskLog")
    List<LogDomain> history = new ArrayList<>();
}
