package org.sofka.mykrello.model.service;

import org.modelmapper.ModelMapper;
import org.sofka.mykrello.model.domain.TaskDomain;
import org.sofka.mykrello.model.repository.BoardRepository;
import org.sofka.mykrello.model.repository.ColumnRepository;
import org.sofka.mykrello.model.repository.TaskRepository;
import org.sofka.mykrello.model.service.interfaces.TaskServiceInterface;
import org.sofka.mykrello.utilities.exceptions.MismatchDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TaskService class to handle all the logic for the TaskDomain.
 */
@Service
public class TaskService implements TaskServiceInterface {
    @Autowired
    private LogService logService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ColumnRepository columnRepository;

    /**
     * find a task by id from the database.
     *
     * @param id the id of the task.
     * @return task found.
     */
    @Override
    public TaskDomain findById(Integer id) {
        return taskRepository.findById(id).orElseThrow(
                () -> new MismatchDataException("Task not found with id: " + id));
    }

    /**
     * find all task from the database by board id and column id
     *
     * @param boardId  the id of the board.
     * @param columnId the id of the column.
     * @return a list of tasks.
     */
    public List<TaskDomain> findAllByBoardIdAndColumnId(Integer boardId, Integer columnId) {
        return taskRepository.findAllByBoardIdAndColumnId(boardId, columnId);
    }

    /**
     * create a task in the database with its initial log and return the task created.
     *
     * @param task the task to create.
     * @return the task created.
     */
    @Override
    @Transactional
    public TaskDomain create(TaskDomain task) {
        checkBoardId(task.getBoardId());
        checkName(task);

        var savedTask = taskRepository.save(task);
        var taskId = savedTask.getId();
        logService.create(taskId);
        var savedHistory = logService.findByTaskId(taskId);
        savedTask.setHistory(savedHistory);

        return savedTask;
    }

    /**
     * update a task in the database and return the task updated.
     *
     * @param taskId the id of the task.
     * @param task   the task to update.
     * @return the task updated.
     */
    @Override
    public TaskDomain update(Integer taskId, TaskDomain task) {
        checkId(taskId);
        checkName(task);

        var oldTask = taskRepository.findById(taskId).orElse(null);

        if (oldTask != null) {
            modelMapper.map(task, oldTask);
            return taskRepository.save(oldTask);
        }

        return null;
    }

    /**
     * move a task to a new column in the database and return the task moved
     *
     * @param taskId      the id of the task.
     * @param newColumnId column to move the task.
     * @return the task moved.
     */
    @Transactional
    public TaskDomain moveTo(Integer taskId, Integer newColumnId) {
        checkColumnId(newColumnId);
        checkId(taskId);

        var task = taskRepository.findById(taskId).orElse(null);

        if (task != null) {
            task.setColumnId(newColumnId);
            logService.update(taskId, newColumnId);
            var savedTask = taskRepository.save(task);
            var updatedHistory = logService.findByTaskId(taskId);
            savedTask.setHistory(updatedHistory);

            return savedTask;
        }

        return null;
    }

    /**
     * delete all tasks from the database by board id.
     *
     * @param id the id of the board.
     */
    @Override
    public void deleteAllByBoardId(Integer id) {
        var taskBoard = taskRepository.findAllByBoardId(id);
        taskRepository.deleteAll(taskBoard);
    }

    /**
     * delete a task from the database by id.
     *
     * @param id the id of the task.
     */
    public void delete(Integer id) {
        checkId(id);
        taskRepository.deleteById(id);
    }

    /**
     * check if the task id is valid
     *
     * @param id the id of the task.
     */
    private void checkId(Integer id) {
        if (!taskRepository.existsById(id)) {
            throw new MismatchDataException("Task doesn't exists " + id);
        }
    }

    /**
     * check if the name of the task is not null or empty.
     *
     * @param task the task to check.
     */
    private void checkName(TaskDomain task) {
        if (task.getName().isEmpty()) {
            throw new MismatchDataException("Task name cannot be empty");
        }
    }

    /**
     * check if the board id exists
     *
     * @param id the id of the board.
     */
    public void checkBoardId(Integer id) {
        if (!boardRepository.existsById(id)) {
            throw new MismatchDataException("Board doesn't exists " + id);
        }
    }

    /**
     * check if the column id exists
     *
     * @param columnId the id of the column.
     */
    private void checkColumnId(Integer columnId) {
        if (!columnRepository.existsById(columnId)) {
            throw new MismatchDataException("Column doesn't exists " + columnId);
        }
    }
}