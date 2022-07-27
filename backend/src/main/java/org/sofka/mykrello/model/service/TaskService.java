package org.sofka.mykrello.model.service;

import org.modelmapper.ModelMapper;
import org.sofka.mykrello.model.domain.TaskDomain;
import org.sofka.mykrello.model.repository.TaskRepository;
import org.sofka.mykrello.model.service.interfaces.TaskServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements TaskServiceInterface {

    @Autowired
    private LogService logService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TaskDomain> findAllTasksByBoardId(Integer idBoard) {
        return taskRepository.findAllByBoardId(idBoard);
    }

    @Override
    public TaskDomain findById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TaskDomain create(TaskDomain task) {
        return taskRepository.save(task);
    }

    @Override
    public TaskDomain update(Integer id, TaskDomain task) {
        var oldTask = taskRepository.findById(id).orElse(null);

        if (oldTask != null) {
            modelMapper.map(task, oldTask);
            return taskRepository.save(oldTask);
        }

        return null;
    }

 /*   private TaskDomain mapToTaskDomain(TaskDomain task, TaskDomain oldTask) {
        var name = task.getName();
        var description = task.getDescription();
        var date = task.getDeliveryDate();

        if (name != null) {
            oldTask.setName(name);
        }

        if (description != null) {
            oldTask.setDescription(description);
        }

        if (date != null) {
            oldTask.setDeliveryDate(date);
        }

        return oldTask;
    }*/

    @Override
    public TaskDomain delete(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }
}
