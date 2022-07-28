package org.sofka.mykrello.model.repository;

import org.sofka.mykrello.model.domain.LogDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogRepository extends JpaRepository<LogDomain, Integer>{

}

