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


@Data
@Entity
@JsonIgnoreProperties(value = {"previous", "current"}, ignoreUnknown = true, allowGetters = true)
@Table(name = "krl_log")
public class LogDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id", nullable = false, updatable = false)
    private Integer id;

    @JoinColumn(name = "tsk_id_task", referencedColumnName = "tsk_id")
    @JsonBackReference(value = "log-task")
    @ManyToOne(fetch = FetchType.EAGER)
    private TaskDomain taskLog;

    @Column(name = "clm_id_previous")
    private Integer previousId;

    @Column(name = "clm_id_current")
    private Integer currentId;

    @Column(name = "log_created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();
}

