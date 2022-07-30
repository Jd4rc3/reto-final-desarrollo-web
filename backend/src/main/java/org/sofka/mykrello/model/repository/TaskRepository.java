package org.sofka.mykrello.model.repository;

import org.sofka.mykrello.model.domain.TaskDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @LuisaAvila @DanielArce
 * TaskRepository is an interface which inherits from the JPA repository and allows access to the methods to be consumed by the service to perform the business logic.
 */
public interface TaskRepository extends JpaRepository<TaskDomain, Integer> {
    /**
     * @LuisaAvila @DanielArce
     * findAllByBoardId allows to obtain a list of a specific board
     * @param idBoard refers to the identifier of the board for which you want to query tasks
     * @return
     */
    List<TaskDomain> findAllByBoardId(Integer idBoard);

    /**
     * @LuisaAvila @DanielArce
     * findAllByBoardIdAndColumnId allows you to find the tasks that are in a specific board and column.
     * @param idBoard refers to the board identifier
     * @param idColumn refers to the column identifier
     */
    List<TaskDomain> findAllByBoardIdAndColumnId(Integer idBoard, Integer idColumn);
}
