package org.sofka.mykrello.model.repository;

import org.sofka.mykrello.model.domain.LogDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LogRepository extends JpaRepository<LogDomain, Integer> {
    @Query(value = "SELECT  clm_id_current FROM krl_log WHERE tsk_id_task = ?1 ORDER BY log_id "
            + "DESC LIMIT 1;", nativeQuery = true)
    Integer getCurrentColumn(Integer taskId);

    @Modifying
    @Query(value = "INSERT  into krl_log (tsk_id_task, clm_id_current, clm_id_previous ) values "
            + "(?1,?2,?3) ", nativeQuery = true)
    void setNewColumns(Integer taskId, Integer current, Integer previous);

    @Query(value = "SELECT * FROM krl_log WHERE tsk_id_task = ?1", nativeQuery = true)
    List<LogDomain> findAllByTaskId(Integer taskId);
}

