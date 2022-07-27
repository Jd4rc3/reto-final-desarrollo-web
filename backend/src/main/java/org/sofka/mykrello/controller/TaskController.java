package org.sofka.mykrello.controller;

import org.sofka.mykrello.model.service.TaskService;
import org.sofka.mykrello.utilities.MyResponseUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
