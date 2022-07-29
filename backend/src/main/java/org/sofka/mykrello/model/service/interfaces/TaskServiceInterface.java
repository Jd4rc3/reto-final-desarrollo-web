package org.sofka.mykrello.model.service.interfaces;

import org.sofka.mykrello.model.domain.TaskDomain;

public interface TaskServiceInterface {
    TaskDomain findById(Integer id);

    TaskDomain create(TaskDomain task);

    TaskDomain update(Integer id, TaskDomain task);

    void deleteAllByBoardId(Integer id);
}
