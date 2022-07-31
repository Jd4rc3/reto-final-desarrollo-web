package org.sofka.mykrello.model.service.interfaces;

import org.sofka.mykrello.model.domain.TaskDomain;

/**
 * TaskServiceInterface interface most necessary methods for taskService
 *
 * @author LuisaAvila <luisaavila304@gmail.com>
 */
public interface TaskServiceInterface {
    /**
     * find a task by id from the database.
     *
     * @param id the id of the task.
     * @return task found.
     */
    TaskDomain findById(Integer id);

    /**
     * create a new task in the database.
     *
     * @param task the task to create.
     * @return the task created.
     */
    TaskDomain create(TaskDomain task);

    /**
     * update a task in the database.
     *
     * @param id   the id of the task.
     * @param task the task to update.
     * @return the task updated.
     */
    TaskDomain update(Integer id, TaskDomain task);

    /**
     * delete a task from the database by id.
     *
     * @param id the id of the task.
     */
    void deleteAllByBoardId(Integer id);
}
