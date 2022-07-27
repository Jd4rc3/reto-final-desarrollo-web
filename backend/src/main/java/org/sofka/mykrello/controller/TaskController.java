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
    public ResponseEntity<MyResponseUtility> findAllTasksByBoardId(
            @PathVariable("id") Integer boardId) {

        response.data = taskService.findAllTasksByBoardId(boardId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public  ResponseEntity<MyResponseUtility> createTask(@RequestBody TaskDomain task){

        response.data = taskService.create(task);

        return  new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
