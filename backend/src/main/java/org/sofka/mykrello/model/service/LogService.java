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
    public List<LogDomain> findByTaskId(Integer id) {
        return logRepository.findLogDomainBy(id);
    }

    @Override
    public LogDomain create(LogDomain log) {
        updateColumns(log, log.getCurrentId());
        return logRepository.save(log);
    }

    public void update(LogDomain log) {
        updateColumns(log, log.getCurrentId());
        logRepository.save(log);
    }

    private void updateColumns(LogDomain log, Integer newCurrentId) {
        var oldCurrent = log.getCurrentId();
        log.setPreviousId(oldCurrent);
        log.setCurrentId(newCurrentId);
    }

    public void delete(Integer logId) {
        logRepository.deleteById(logId);
    }
}
