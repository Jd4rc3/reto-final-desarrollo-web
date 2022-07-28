package org.sofka.mykrello.model.domain;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@JsonIgnoreProperties(value = {"previous", "current"}, ignoreUnknown = true, allowGetters = true)
@Table(name = "krl_log")
public class LogDomain implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "tsk_id_task")
    private Integer idTask;

    @Column(name = "clm_id_previous")
    private Integer previousId;

    @Column(name = "clm_id_current")
    private Integer currentId;

    /*@ManyToOne(fetch = FetchType.LAZY, targetEntity = ColumnDomain.class, optional = false,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "clm_id_previous", updatable = false, insertable = false)
    @JsonBackReference(value = "logPrevious")
    private ColumnDomain previous;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = ColumnDomain.class, optional = false,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "clm_id_current", updatable = false, insertable = false)
    @JsonBackReference(value = "logCurrent")
    private ColumnDomain current;*/

    @Column(name = "log_created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    public LogDomain(Integer idTask, Integer previousId, Integer currentId) {
        this.idTask = idTask;
        this.previousId = previousId;
        this.currentId = currentId;
    }

    public LogDomain(Integer idTask, Integer currentId) {
        this.idTask = idTask;
        this.currentId = currentId;
    }

    public LogDomain() {
    }
}

