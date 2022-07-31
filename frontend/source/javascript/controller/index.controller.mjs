"use strict";

import {BoardsService} from "../model/services/boards.service.mjs";

class IndexController {
    #BoardService

    constructor() {
        this.#BoardService = new BoardsService();

    }

    async init() {
        // this.#BoardService.getAllBoards().then(data => console.log(data));
    }
}

export const index = new IndexController();
index.init();
