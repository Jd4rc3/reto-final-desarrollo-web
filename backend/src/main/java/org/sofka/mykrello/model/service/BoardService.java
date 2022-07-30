package org.sofka.mykrello.model.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.sofka.mykrello.model.domain.BoardDomain;
import org.sofka.mykrello.model.dtos.BoardDTO;
import org.sofka.mykrello.model.dtos.ColumnDTO;
import org.sofka.mykrello.model.repository.BoardRepository;
import org.sofka.mykrello.model.repository.ColumnForBoardRepository;
import org.sofka.mykrello.model.repository.ColumnRepository;
import org.sofka.mykrello.model.service.interfaces.BoardServiceInterface;
import org.sofka.mykrello.utilities.exceptions.MismatchDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * board service handle all the logic for the board domain.
 */
@Service
@Slf4j
public class BoardService implements BoardServiceInterface {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ColumnRepository columnRepository;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ColumnForBoardRepository columnForBoardRepository;

    /**
     * find all the boards from the database.
     *
     * @return a list of boards.
     */
    @Override
    @Transactional(readOnly = true)
    public List<BoardDomain> getAll() {
        return boardRepository.findAll();
    }

    /**
     * find a board by id from the database.
     *
     * @param id the id of the board.
     * @return BoardDTO
     */
    @Override
    @Transactional(readOnly = true)
    public BoardDTO findById(Integer id) {
        checkId(id);
        var board = boardRepository.findById(id).orElse(new BoardDomain());
        var boardDTO = new BoardDTO();

        modelMapper.map(board, boardDTO);

        boardDTO.setColumns(filterTaskByColumn(id));
        return boardDTO;
    }

    /**
     * filters all tasks by column and return a list of columns with respective tasks.
     *
     * @param boardId the id of the board.
     * @return List<ColumnDTO> a list of columns with respective tasks.
     */
    @Transactional
    public List<ColumnDTO> filterTaskByColumn(Integer boardId) {
        List<ColumnDTO> columnDTOs = getColumnDTOS(boardId);

        columnDTOs.forEach(column -> column.setTasks(
                taskService.findAllByBoardIdAndColumnId(boardId, column.getId())));

        return columnDTOs;
    }

    /**
     * find all the columns from the database and map them to ColumnDTO.
     *
     * @param boardId the id of the board.
     * @return List<ColumnDTO> a list of columns.
     */
    private List<ColumnDTO> getColumnDTOS(Integer boardId) {
        var columns = columnRepository.findAllByBoardId(boardId);
        List<ColumnDTO> columnDTOs = new ArrayList<>();

        columns.forEach(column -> columnDTOs.add(modelMapper.map(column, ColumnDTO.class)));
        return columnDTOs;
    }

    /**
     * create a new board in the database and map it to BoardDTO.
     *
     * @param board the board to be created.
     * @return BoardDTO the created board.
     */
    @Override
    @Transactional
    public BoardDTO create(BoardDomain board) {
        checkName(board);
        var newBoard = boardRepository.save(board);
        var boardId = newBoard.getId();
        BoardDTO boardDTO = modelMapper.map(newBoard, BoardDTO.class);
        columnForBoardRepository.createColumns(boardId);
        var columnList = getColumnDTOS(boardId);

        boardDTO.setColumns(columnList);

        return boardDTO;
    }


    /**
     * update a board name in the database.
     *
     * @param id    the id of the board.
     * @param board the board to be updated.
     * @return the updated board.
     */
    @Override
    @Transactional
    public BoardDomain update(Integer id, BoardDomain board) {
        checkId(id);
        checkName(board);
        board.setId(id);
        return boardRepository.save(board);
    }

    /**
     * delete a board from the database.
     *
     * @param id the id of the board.
     */
    @Override
    @Transactional
    public void delete(Integer id) {
        checkId(id);

        columnForBoardRepository.deleteAllByBoardId(id);
        taskService.deleteAllByBoardId(id);
        boardRepository.deleteById(id);
    }

    /**
     * check if the board exists in the database if not throw an exception.
     *
     * @param id the id of the board.
     */
    public void checkId(Integer id) {
        if (!boardRepository.existsById(id)) {
            throw new MismatchDataException("Board doesn't exists " + id);
        }
    }

    /**
     * check if the board name is empty if not throw an exception.
     *
     * @param board the board to be checked.
     */
    private void checkName(BoardDomain board) {
        if (board.getName().isEmpty()) {
            throw new MismatchDataException("Board name cannot be empty");
        }
    }
}