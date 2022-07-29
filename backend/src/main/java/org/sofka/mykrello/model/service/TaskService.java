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

    @Override
    public TaskDomain findById(Integer id) {
        return taskRepository.findById(id).orElseThrow(
                () -> new MismatchDataException("Task not found with id: " + id));
    }

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

    @Override
    public void deleteAllByBoardId(Integer id) {
        var taskBoard = taskRepository.findAllByBoardId(id);
        taskRepository.deleteAll(taskBoard);
    }

    public void delete(Integer id) {
        checkId(id);
        taskRepository.deleteById(id);
    }

    private void checkId(Integer id) {
        if (!taskRepository.existsById(id)) {
            throw new MismatchDataException("Task doesn't exists " + id);
        }
    }

    private void checkName(TaskDomain task) {
        if (task.getName().isEmpty()) {
            throw new MismatchDataException("Task name cannot be empty");
        }
    }

    public void checkBoardId(Integer id) {
        if (!boardRepository.existsById(id)) {
            throw new MismatchDataException(
                    "Board doesn't exists " + id);
        }
    }

    private void checkColumnId(Integer columnId) {
        if (!columnRepository.existsById(columnId)) {
            throw new MismatchDataException("Column doesn't exists " + columnId);
        }
    }
}