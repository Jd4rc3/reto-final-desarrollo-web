"use strict";

import {BoardsService} from "../model/services/boards.service.mjs";
import {BoardsView} from "../view/boards.view.mjs";

class IndexController {
    #BoardService

    constructor() {
        this.#BoardService = new BoardsService();
    }

    async init() {
        const boards = await this.#BoardService.getAllBoards();
        const boardsView = new BoardsView(boards);
        console.log(await boardsView.render());
    }
}

export const index = new IndexController();
index.init();
