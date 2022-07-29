package org.sofka.mykrello.model.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.sofka.mykrello.model.domain.BoardDomain;
import org.sofka.mykrello.model.domain.ColumnForBoardDomain;
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

    @Override
    @Transactional(readOnly = true)
    public List<BoardDomain> getAll() {
        return boardRepository.findAll();
    }

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

    @Transactional
    public List<ColumnDTO> filterTaskByColumn(Integer boardId) {
        List<ColumnDTO> columnDTOs = getColumnDTOS(boardId);

        columnDTOs.forEach(column -> column.setTasks(
                taskService.findAllByBoardIdAndColumnId(boardId, column.getId())));

        return columnDTOs;
    }

    private List<ColumnDTO> getColumnDTOS(Integer boardId) {
        var columns = columnRepository.findAllByBoardId(boardId);
        List<ColumnDTO> columnDTOs = new ArrayList<>();

        columns.forEach(column -> columnDTOs.add(modelMapper.map(column, ColumnDTO.class)));
        return columnDTOs;
    }


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


    @Override
    @Transactional
    public BoardDomain update(Integer id, BoardDomain board) {
        checkId(id);
        checkName(board);
        board.setId(id);
        return boardRepository.save(board);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        checkId(id);

        columnForBoardRepository.deleteAllByBoardId(id);
        taskService.deleteAllByBoardId(id);
        boardRepository.deleteById(id);
    }

    public void checkId(Integer id) {
        if (!boardRepository.existsById(id)) {
            throw new MismatchDataException("Board doesn't exists " + id);
        }
    }

    private void checkName(BoardDomain board) {
        if (board.getName().isEmpty()) {
            throw new MismatchDataException("Board name cannot be empty");
        }
    }
}