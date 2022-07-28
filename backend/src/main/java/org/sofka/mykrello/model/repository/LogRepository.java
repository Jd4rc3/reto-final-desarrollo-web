package org.sofka.mykrello.model.repository;

import org.sofka.mykrello.model.domain.LogDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LogRepository extends JpaRepository<LogDomain, Integer> {

    @Query(value = "select clm_id_current from krl_log where tsk_id_task = ?1", nativeQuery = true)
    Integer getCurrentColumn(Integer taskId);

    @Modifying
    @Query(value = "INSERT  into krl_log (tsk_id_task, clm_id_current, clm_id_previous ) values (?1,?2,?3) ", nativeQuery = true)
    void  setNewColumns(Integer taskId, Integer current, Integer previous);
}

