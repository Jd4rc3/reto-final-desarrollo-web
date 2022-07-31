package org.sofka.mykrello.controller;

import org.sofka.mykrello.model.domain.TaskDomain;
import org.sofka.mykrello.model.service.TaskService;
import org.sofka.mykrello.utilities.MyResponseUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TaskController is a class that allows to organize the actions that the program will do, this
 * through the path or endpoint, and communicates with the service consuming the repository.
 * see is injected MyResponseUtility and TaskService
 */
@CrossOrigin(value = "*")
@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    @Autowired
    private MyResponseUtility response;

    @Autowired
    private TaskService taskService;


    /**
     * Endpoint to find a task by id.
     *
     * @param taskId refers to the identifier of the task being searched for
     * @return an object of type ResponseEntity which has the response of the task type object
     * and a status of type HTTP
     * <p>
     * The findId function allows to return a specific task identified with the id obtained by
     * the user's request
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<MyResponseUtility> findById(@PathVariable("id") Integer taskId) {
        response.setFields(false, taskService.findById(taskId));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Endpoint to create a task .
     *
     * @param task refers to the task type object to be entered into the function to be created
     * @return an object of type ResponseEntity with an object of type task that has been created
     * and a status of type HTTP
     */
    @PostMapping("")
    public ResponseEntity<MyResponseUtility> createTask(@RequestBody TaskDomain task) {
        response.setFields(false, taskService.create(task));

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * @param taskId      refers to the identifier of the task to be moved
     * @param newColumnId refers to the identifier of the column to which you want to move the
     *                    task to.
     * @return an object of type ResponseEntity with the task and its updated column attribute,
     * determining its previous and current position
     */

    @PatchMapping("/move/{taskId}/{newColumnId}")
    public ResponseEntity<MyResponseUtility> moveTask(@PathVariable("taskId") Integer taskId,
            @PathVariable("newColumnId") Integer newColumnId) {

        var movedTask = taskService.moveTo(taskId, newColumnId);

        response.setFields(false, "Done moving task", movedTask);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * @param taskId refers to the identifier of the task to be updated
     * @param task   refers to the task object with new attributes
     * @return an object of type ResponseEntity with the task and its attributes updated, with a
     * status of type HTTP
     */
    @PutMapping("/{id}")
    public ResponseEntity<MyResponseUtility> updateTask(@PathVariable("id") Integer taskId,
            @RequestBody TaskDomain task) {

        var updatedValue = taskService.update(taskId, task);
        response.setFields(false, updatedValue);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * @param taskId refers to the identifier of the task to be deleted
     * @return an object of type responseEntity containing a response object indicating whether
     * the task was successfully completed and an HTTP status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<MyResponseUtility> deleteTask(@PathVariable("id") Integer taskId) {
        taskService.delete(taskId);
        response.setFields(false, "successfully deleted");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}