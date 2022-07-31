package org.sofka.mykrello.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * TaskDomain is a domain class that represents a task
 *
 * @author JuanDanielArce <jdarce91@misena.edu.co>
 */
@Data
@Entity
@JsonIgnoreProperties(value = {"history", "column"}, ignoreUnknown = true, allowGetters = true)
@Table(name = "krl_task")
public class TaskDomain {
    /**
     * id is the primary key of the task.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tsk_id", nullable = false)
    private Integer id;

    /**
     * columnId is the id of the column it belongs to.
     */
    @Column(name = "clm_id_column")
    private Integer columnId;

    /**
     * boardId is the id of the board it belongs to.
     */
    @Column(name = "brd_id_board")
    private Integer boardId;

    /**
     * name is the name of the task.
     */
    @Column(name = "tsk_name")
    private String name;

    /**
     * description is the description of the task.
     */
    @Column(name = "tsk_description")
    private String description;

    /**
     * deliveryDate is the date when the task will be delivered.
     */
    @Column(name = "tsk_delivery_date")
    private LocalDate deliveryDate;

    /**
     * createdAt is the date when the task was created.
     */
    @Column(name = "tsk_created_at")
    private Instant createdAt = Instant.now();

    /**
     * updatedAt is the date when the task was updated.
     */
    @Column(name = "tsk_updated_at")
    private Instant updatedAt;

    /**
     * history is a list of logs that belongs to the task.
     */
    @JsonManagedReference(value = "log-task")
    @ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER, targetEntity = LogDomain.class, cascade = CascadeType.ALL
            , mappedBy = "taskLog")
    private List<LogDomain> history = new ArrayList<>();
}