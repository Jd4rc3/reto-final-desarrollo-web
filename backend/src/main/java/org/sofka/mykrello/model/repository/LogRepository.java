package org.sofka.mykrello.model.repository;

import org.sofka.mykrello.model.domain.LogDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * LogRepository is an interface which inherits from the JPA repository and allows access to the
 * methods to be consumed by the service to perform the business logic.
 *
 * @author LuisaAvila <luisaavila304@gmail.com>
 */
public interface LogRepository extends JpaRepository<LogDomain, Integer> {

    /**
     * getCurrentColumn a query is made to obtain the current value of the column where the task
     * is located
     *
     * @param taskId refers to the task identifier of the task to be queried
     * @return List<LogDomain> allows to obtain a list of logs of a specific task
     * @author LuisaAvila <luisaavila304@gmail.com>
     */
    @Query(value = "SELECT  clm_id_current FROM krl_log WHERE tsk_id_task = ?1 ORDER BY log_id " +
            "DESC LIMIT 1;", nativeQuery = true)
    Integer getCurrentColumn(Integer taskId);

    /**
     * setNewColumns allows inserting into the database the values of the column where the task
     * was located and the value of the current column.
     *
     * @param taskId   refers to the identifier of the task to which the column is to be switched to
     * @param current  refers to the value of the current column where the task will be placed
     * @param previous refers to the previous position where the task was located.
     * @author LuisaAvila <luisaavila304@gmail.com>
     */
    @Modifying
    @Query(value = "INSERT  into krl_log (tsk_id_task, clm_id_current, clm_id_previous ) values " +
            "(?1,?2,?3) ", nativeQuery = true)
    void setNewColumns(Integer taskId, Integer current, Integer previous);

    /**
     * findAllByTaskId  makes a query to obtain the logs of a specific task
     *
     * @param taskId refers to the identifier of the task to which we can query the logs.
     * @author LuisaAvila <luisaavila304@gmail.com>
     */
    @Query(value = "SELECT * FROM krl_log WHERE tsk_id_task = ?1", nativeQuery = true)
    List<LogDomain> findAllByTaskId(Integer taskId);
}

