package org.sofka.mykrello.controller;

import org.sofka.mykrello.model.domain.BoardDomain;
import org.sofka.mykrello.model.service.BoardService;
import org.sofka.mykrello.utilities.MyResponseUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * BoardController class exposes the REST API for the BoardDomain using field injection from the
 * BoardService and custom response.
 *
 * @author LuisaAvila <luisaavila304@gmail.com>
 */
@RestController
@CrossOrigin(value = "*")
@RequestMapping("/api/v1/boards")
public class BoardController {
    /**
     * Response object to handle same responses.
     */
    @Autowired
    private MyResponseUtility response;

    /**
     * BoardService object to handle the business logic.
     */
    @Autowired
    private BoardService boardService;

    /**
     * getAll endPoint returns all the boards in the database.
     *
     * @return ResponseEntity<List < BoardDomain>>
     */
    @GetMapping(path = "")
    public ResponseEntity<MyResponseUtility> getAll() {
        response.setFields(false, boardService.getAll());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * getById endPoint returns the board with the given id.
     *
     * @param id the id of the board to be returned.
     * @return ResponseEntity<MyResponseUtility> the found board.
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<MyResponseUtility> getBoardById(@PathVariable(value = "id") Integer id) {
        response.setFields(false, boardService.findById(id));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * createBoard endPoint creates a new board and returns the created board.
     *
     * @param board the board to be created.
     * @return ResponseEntity<MyResponseUtility> the created board inside the response.
     */
    @PostMapping(path = "")
    public ResponseEntity<MyResponseUtility> create(@RequestBody BoardDomain board) {
        response.setFields(false, boardService.create(board));

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * updateBoard endPoint updates the board with the given id and returns the updated board.
     *
     * @param id    the id of the board to be updated.
     * @param board the board to be updated.
     * @return ResponseEntity<MyResponseUtility> the updated board inside the response.
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity<MyResponseUtility> updateBoard(@PathVariable(value = "id") Integer id,
            @RequestBody BoardDomain board) {
        response.setFields(false, boardService.update(id, board));

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * deleteBoard endPoint deletes the board with the given id.
     *
     * @param id the id of the board to be deleted.
     * @return ResponseEntity<MyResponseUtility> the deleted board inside the response.
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<MyResponseUtility> deleteBoard(@PathVariable(value = "id") Integer id) {
        response.setFields(false);
        boardService.delete(id);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}