"use strict";

import { BoardsService } from "../model/services/boards.service.mjs";
import { IndexView } from "../view/indexView.mjs";

/**
 * @class IndexController - Controller for the index view
 */
class IndexController {
  constructor() {}

  async init() {
    const boards = await BoardsService.getAllBoards();
    const boardsView = new IndexView(boards);
    await boardsView.render();
  }
}

export const index = new IndexController();
index.init();
