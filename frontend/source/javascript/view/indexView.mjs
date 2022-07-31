import { Card } from "./components/card.component.mjs";
import { Button } from "./components/button.component.mjs";
import { ButtonsHandler } from "../controller/buttons.controller.mjs";

/**
 * view for main view of boards
 */
export class IndexView {
  #data;
  #button;

  constructor(boards) {
    const attributes = [
      { name: "data-bs-toggle", value: "modal" },
      {
        name: "data-bs-target",
        value: "#update-board-modal",
      },
      { name: "id", value: "newButton" },
    ];

    this.#button = new Button(
      "New Board",
      "success",
      ButtonsHandler.buttonHandler,
      attributes
    );

    this.#data = boards;
  }

  async render() {
    const boards = [...this.#data];
    const container = document.querySelector("#container");

    boards.forEach((board) => {
      const createdBoard = this.createBoard(board);
      container.append(createdBoard);
    });

    container.append(this.#button.buildButton());
  }

  createBoard(board) {
    const card = new Card(board);

    return card.drawCard();
  }

  static saveEditingBoard(boardId) {
    localStorage.setItem("boardId", boardId);
  }

  static goToBoard(boardId) {
    window.location.href = `board.html?id=${boardId}`;
  }
}
