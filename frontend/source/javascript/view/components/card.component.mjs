import {ButtonsHandler} from "../../controller/buttons.controller.mjs";
import {Button} from "./button.component.mjs";
import {IndexView} from "../indexView.mjs";

/**
 * Card model class represents a board with some buttons to manipulate it.
 */
export class Card {
    #board;

    constructor(board) {
        this.#board = board;

        this.button1 = new Button("🗑", "danger", ButtonsHandler.buttonHandler);
        this.button2 = new Button("📝", "info", ButtonsHandler.buttonHandler, [
            {
                name: "data-bs-toggle",
                value: "modal",
            },
            {name: "data-bs-target", value: "#update-board-modal"},
            {name: "id", value: "updateBoardButton"},
        ]);
    }

    /**
     * Build all containers for the board.
     * @returns {HTMLDivElement}
     */
    drawCard() {
        const boardId = this.#board.getId();

        const card = document.createElement("div");
        card.id = `board-${boardId}`;
        card.classList.add("card");
        card.addEventListener(
            "click",
            IndexView.saveEditingBoard.bind(this, boardId)
        );
        card.setAttribute("style", "width: 18rem;");

        const cardHeader = document.createElement("div");
        cardHeader.addEventListener(
            "click",
            IndexView.goToBoard.bind(this, boardId)
        );
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

    /**
     * Add a new card to container
     */
    newCard() {
        const newCard = this.drawCard();
        const container = document.querySelector("#container");
        container.insertBefore(newCard, container.firstChild);
    }

    /**
     * Delete a card from container
     */
    static deleteCard(boardId) {
        const container = document.querySelector("#container");
        const card = document.querySelector(`#${boardId}`);
        container.removeChild(card);
    }
}
