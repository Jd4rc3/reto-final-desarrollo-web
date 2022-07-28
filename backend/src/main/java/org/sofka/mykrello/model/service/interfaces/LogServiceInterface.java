package org.sofka.mykrello.model.service.interfaces;

import java.util.List;

import org.sofka.mykrello.model.domain.LogDomain;

public interface LogServiceInterface {
     List<LogDomain> findByTaskId(Integer id);
     LogDomain create(LogDomain log);
}
