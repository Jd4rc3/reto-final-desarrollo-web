import { ButtonsHandler } from "../../controller/buttons.controller.mjs";
import { Button } from "./button.component.mjs";

export class Card {
  #board;

  constructor(board) {
    this.#board = board;

    this.button1 = new Button("🗑", "danger", ButtonsHandler.buttonHandler);
    this.button2 = new Button("📝", "info", ButtonsHandler.buttonHandler, [
      { name: "data-bs-toggle", value: "modal" },
      { name: "data-bs-target", value: "#update-board-modal" },
    ]);
  }

  drawCard() {
    const card = document.createElement("div");
    card.classList.add("card");
    card.setAttribute("style", "width: 18rem;");
    card.id = `board-${this.#board.getId()}`;

    const cardHeader = document.createElement("div");
    cardHeader.classList.add("card-header");
    cardHeader.innerHTML = `${this.#board.getName()}`;

    const ul = document.createElement("ul");
    ul.classList.add("list-group");
    ul.classList.add("list-group-flush");

    const li = document.createElement("li");
    li.classList.add("list-group-item");
    li.append(this.button1.buildButton(), this.button2.buildButton());

    ul.append(li);
    card.append(cardHeader, ul);

    return card;
  }

  static deleteCard(boardId) {
    const container = document.querySelector("#container");
    const card = document.querySelector(`#${boardId}`);
    container.removeChild(card);
  }
}
