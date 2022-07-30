package org.sofka.mykrello.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.Instant;

/**
 * LogDomain is a domain class that represents a column in a board
 */
@Data
@Entity
@JsonIgnoreProperties(value = {"previous", "current"}, ignoreUnknown = true, allowGetters = true)
@Table(name = "krl_log")
public class LogDomain {
    /**
     * id is the primary key of the log.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id", nullable = false, updatable = false)
    private Integer id;

    /**
     * taskId is the id of the task it owns the log.
     */
    @Column(name = "tsk_id_task")
    private Integer taskId;

    /**
     * previousId is the id where task was before the change.
     */
    @Column(name = "clm_id_previous")
    private Integer previousId;

    /**
     * currentId is the id where task is now.
     */
    @Column(name = "clm_id_current")
    private Integer currentId;

    /**
     * createdAt is the date when the log was created.
     */
    @Column(name = "log_created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    /**
     * taskLog is relationship with the task domain.
     */
    @JoinColumn(name = "tsk_id_task", referencedColumnName = "tsk_id", insertable = false,
            updatable = false)
    @JsonBackReference(value = "log-task")
    @ManyToOne(fetch = FetchType.EAGER)
    private TaskDomain taskLog;
}