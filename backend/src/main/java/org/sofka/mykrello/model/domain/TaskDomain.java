package org.sofka.mykrello.model.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "krl_task")
public class TaskDomain implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tsk_id", nullable = false)
    private Integer id;

    @Column(name = "clm_id_column")
    private Integer columnId;

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
}
