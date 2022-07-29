package org.sofka.mykrello.model.repository;

import org.sofka.mykrello.model.domain.TaskDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskDomain, Integer> {
    List<TaskDomain> findAllByBoardId(Integer idBoard);

    List<TaskDomain> findAllByBoardIdAndColumnId(Integer idBoard, Integer idColumn);
}
