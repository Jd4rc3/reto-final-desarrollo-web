package org.sofka.mykrello.model.service;

import org.sofka.mykrello.model.domain.LogDomain;
import org.sofka.mykrello.model.repository.LogRepository;
import org.sofka.mykrello.model.service.interfaces.LogServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * LogService class to handle all the logic for the LogDomain.
 *
 * @author JuanDanielArce <jdarce91@misena.edu.co>
 */
@Service
public class LogService implements LogServiceInterface {
    /**
     * Log repository bean to handle all the logic for the LogDomain.
     */
    @Autowired
    LogRepository logRepository;

    /**
     * find all the logs from the database given a task id.
     *
     * @param taskId the id of the task.
     * @return a list of logs.
     */
    @Override
    public List<LogDomain> findByTaskId(Integer taskId) {
        return logRepository.findAllByTaskId(taskId);
    }

    /**
     * create a new log in the database and set the task id.
     *
     * @param taskId the id of the task.
     * @return a new log.
     */
    @Override
    public LogDomain create(Integer taskId) {
        LogDomain log = new LogDomain();
        log.setTaskId(taskId);
        log.setPreviousId(1);
        log.setCurrentId(1);
        return logRepository.save(log);
    }

    /**
     * update columns if the task is moved.
     *
     * @param taskId   the id of the task.
     * @param newColum column id of the new column.
     */
    public void update(Integer taskId, Integer newColum) {
        var oldColumn = logRepository.getCurrentColumn(taskId);
        logRepository.setNewColumns(taskId, newColum, oldColumn);
    }
}