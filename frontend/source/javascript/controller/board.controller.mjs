"use strict";

import {BoardsService} from "../model/services/boards.service.mjs";
import {BoardView} from "../view/boardView.mjs";

class BoardController {
    #BoardService

    constructor() {
        this.#BoardService = new BoardsService();
    }

    async init() {
        const board = await this.getBoard();
        const boardView = new BoardView(board);
        await boardView.render();
    }

    getBoard() {
        const URL = new URLSearchParams(window.location.search);
        const id = parseInt(URL.get('id'))
        return this.#BoardService.getBoardById(id)
    }
}

const board = new BoardController();
board.init();

