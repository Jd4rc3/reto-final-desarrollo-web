package org.sofka.mykrello.model.repository;

import org.sofka.mykrello.model.domain.BoardDomain;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author LuisaAvila <luisaavila304@gmail.com>
 * * BoardRepository is an interface which inherits from the JPA repository and allows access to
 * the methods to be consumed by the service to perform the business logic.
 */
public interface BoardRepository extends JpaRepository<BoardDomain, Integer> {}
