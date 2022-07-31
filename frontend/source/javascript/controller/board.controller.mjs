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
        console.log(board)
        const boardView = new BoardView(board);
        await boardView.render();
    }

    async getBoard() {
        const URL = new URLSearchParams(window.location.search);
        const id = parseInt(URL.get('id'))
        try {
            const board = await this.#BoardService.getBoard(id);
            // board.
        } catch (error) {
            alert(error);
        }
    }
}

const board = new BoardController();
board.init();

