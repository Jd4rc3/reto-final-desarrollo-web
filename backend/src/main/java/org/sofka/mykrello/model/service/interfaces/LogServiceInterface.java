package org.sofka.mykrello.model.service.interfaces;

import org.sofka.mykrello.model.domain.LogDomain;

import java.util.List;

/**
 * LogService interface to subscribe most necessary methods for LogDomain
 */
public interface LogServiceInterface {
    List<LogDomain> findByTaskId(Integer id);

    LogDomain create(Integer taskId);
}
