"use strict";

import {BoardsService} from "../model/services/boards.service.mjs";
import {IndexView} from "../view/indexView.mjs";

class IndexController {
    #BoardService

    constructor() {
        this.#BoardService = new BoardsService();
    }

    async init() {
        const boards = await this.#BoardService.getAllBoards();
        const boardsView = new IndexView(boards);
        await boardsView.render()
    }
}

export const index = new IndexController();
index.init();
