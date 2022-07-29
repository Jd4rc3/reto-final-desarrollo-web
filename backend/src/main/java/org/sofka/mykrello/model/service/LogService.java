package org.sofka.mykrello.model.service;

import org.sofka.mykrello.model.domain.LogDomain;
import org.sofka.mykrello.model.repository.LogRepository;
import org.sofka.mykrello.model.service.interfaces.LogServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService implements LogServiceInterface {
    @Autowired
    LogRepository logRepository;

    @Override
    public List<LogDomain> findByTaskId(Integer taskId) {
        return logRepository.findAllByTaskId(taskId);
    }

    @Override
    public LogDomain create(Integer taskId) {
        LogDomain log = new LogDomain();
        log.setTaskId(taskId);
        log.setPreviousId(1);
        log.setCurrentId(1);
        return logRepository.save(log);
    }

    public void update(Integer taskId, Integer newColum) {
        var oldColumn = logRepository.getCurrentColumn(taskId);
        logRepository.setNewColumns(taskId, newColum, oldColumn);
    }
}