"use strict";

import { BoardView } from "../view/boardView.mjs";
import { BoardsService } from "../model/services/boards.service.mjs";
import { getBoardIdFromURL } from "../config.mjs";

/**
 * @class BoardController - Controller for the board view
 */
export class BoardController {
  constructor() {}

  async init() {
    const board = await this.getBoard();
    const boardView = new BoardView(board);
    await boardView.render();
  }

  async getBoard() {
    const boardService = new BoardsService();
    const id = getBoardIdFromURL();
    return boardService.getBoardById(id);
  }
}

const board = new BoardController();
await board.init();
