package org.sofka.mykrello.controller;

import org.sofka.mykrello.model.domain.TaskDomain;
import org.sofka.mykrello.model.service.TaskService;
import org.sofka.mykrello.utilities.MyResponseUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    @Autowired
    private MyResponseUtility response;

    @Autowired
    private TaskService taskService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<MyResponseUtility> findById(@PathVariable("id") Integer taskId) {
        response.setFields(false, taskService.findById(taskId));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<MyResponseUtility> createTask(@RequestBody TaskDomain task) {
        response.setFields(false, taskService.create(task));

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/move/{taskId}/{newColumnId}")
    public ResponseEntity<MyResponseUtility> moveTask(@PathVariable("taskId") Integer taskId,
            @PathVariable("newColumnId") Integer newColumnId) {

        var movedTask = taskService.moveTo(taskId, newColumnId);

        response.setFields(false, "Done moving task", movedTask);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MyResponseUtility> updateTask(@PathVariable("id") Integer taskId,
            @RequestBody TaskDomain task) {

        var updatedValue = taskService.update(taskId, task);
        response.setFields(false, updatedValue);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<MyResponseUtility> deleteTask(@PathVariable("id") Integer taskId) {
        taskService.delete(taskId);
        response.setFields(false,"successfully deleted");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}