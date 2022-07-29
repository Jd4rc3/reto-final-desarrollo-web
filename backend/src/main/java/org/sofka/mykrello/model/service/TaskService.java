package org.sofka.mykrello.model.service;

import org.modelmapper.ModelMapper;
import org.sofka.mykrello.model.domain.TaskDomain;
import org.sofka.mykrello.model.repository.TaskRepository;
import org.sofka.mykrello.model.service.interfaces.TaskServiceInterface;
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

    @Override
    public TaskDomain findById(Integer id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public TaskDomain create(TaskDomain task) {
        var savedTask = taskRepository.save(task);
        var taskId = savedTask.getId();
        logService.create(taskId);
        var savedHistory = logService.findByTaskId(taskId);
        savedTask.setHistory(savedHistory);

        return savedTask;
    }

    @Override
    public TaskDomain update(Integer taskId, TaskDomain task) {
        var oldTask = taskRepository.findById(taskId).orElse(null);

        if (oldTask != null) {
            modelMapper.map(task, oldTask);
            return taskRepository.save(oldTask);
        }

        return null;
    }

    @Transactional
    public TaskDomain moveTo(Integer taskId, Integer newColumnId) {
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
}