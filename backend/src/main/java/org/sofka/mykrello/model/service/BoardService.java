package org.sofka.mykrello.model.service;

import org.sofka.mykrello.model.domain.BoardDomain;
import org.sofka.mykrello.model.domain.ColumnForBoardDomain;
import org.sofka.mykrello.model.repository.BoardRepository;
import org.sofka.mykrello.model.repository.ColumnForBoardRepository;
import org.sofka.mykrello.model.repository.ColumnRepository;
import org.sofka.mykrello.model.service.interfaces.BoardServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService implements BoardServiceInterface {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ColumnRepository columnRepository;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ColumnForBoardRepository columnForBoardRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BoardDomain> getAll() {
        return boardRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public BoardDomain findById(Integer id) {
        var board = boardRepository.findById(id);
        return board.isPresent() ? board.get() : null;
    }

    @Override
    @Transactional
    public BoardDomain create(BoardDomain board) {
        var newBoard = boardRepository.save(board);
        var columns = columnRepository.findAll();
        if (!columns.isEmpty()) {
            columns.forEach(column -> {
                var columnForBoard = new ColumnForBoardDomain();
                columnForBoard.setColumn(column);
                columnForBoard.setBoard(newBoard);
                columnForBoardRepository.save(columnForBoard);
            });
        }
        return newBoard;
    }

    @Override
    @Transactional
    public BoardDomain update(Integer id, BoardDomain board) {
        board.setId(id);
        return boardRepository.save(board);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        taskService.deleteAllByBoardId(id);
        boardRepository.deleteById(id);
    }
}