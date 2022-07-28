package org.sofka.mykrello.model.service;

import java.util.List;

import org.sofka.mykrello.model.domain.LogDomain;
import org.sofka.mykrello.model.repository.LogRepository;
import org.sofka.mykrello.model.service.interfaces.LogServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        // TODO Auto-generated method stub
        return null;
    }
}
