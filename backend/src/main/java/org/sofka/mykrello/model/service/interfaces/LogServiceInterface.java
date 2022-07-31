package org.sofka.mykrello.model.service.interfaces;

import org.sofka.mykrello.model.domain.LogDomain;

import java.util.List;

/**
 * LogService interface to subscribe most necessary methods for LogDomain
 *
 * @author JuanDanielArce <jdarce91@misena.edu.co>
 */
public interface LogServiceInterface {
    /**
     * find all the logs from the database given a task id.
     *
     * @param id the id of the task.
     * @return a list of logs.
     */
    List<LogDomain> findByTaskId(Integer id);

    /**
     * create a new log in the database and set the task id.
     *
     * @param taskId the id of the task.
     * @return a new log.
     */
    LogDomain create(Integer taskId);
}
