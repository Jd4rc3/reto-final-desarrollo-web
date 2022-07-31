import {ColumnModel} from "./column.model.mjs";
import {Button} from "../view/components/button.component.mjs";
import {ButtonsHandler} from "../controller/buttons.controller.mjs";

/**
 * Board model class represents a board and its fields which can be manipulated.
 */
export class BoardModel {
    #id;
    #name;
    #createdAt;
    #updatedAt;
    #columns;

    constructor(board) {
        const {id, name, createdAt, updatedAt, columns} = board;

        this.#columns = [];
        this.#id = id;
        this.#name = name;
        this.#createdAt = createdAt;
        this.#updatedAt = updatedAt;

        if (columns) {
            columns.forEach((column) => {
                this.#columns.push(new ColumnModel(column));
            });
        }
    }

    /**
     * Build all containers for the board and its children.
     * @returns {HTMLDivElement} board container
     */
    drawBoard() {
        const columns = this.#columns;
        const container = document.createElement("div");
        const title = document.createElement("h1");
        const columnsContainer = document.createElement("div");
        const addTaskButton = this.getButton("New Task", "success");

        columnsContainer.classList.add("columns-container");
        container.classList.add("board-container");
        title.classList.add("board-title");
        title.textContent = this.#name;

        columns.forEach((column) => {
            columnsContainer.append(column.drawColumn());
        });

        container.append(title, columnsContainer, addTaskButton);

        return container;
    }

    /**
     * @method getButton - Creates a button with the given text
     * @param text - The text to be displayed on the button
     * @param color - The color of the button
     * @returns {HTMLButtonElement} - The button
     */
    getButton(text, color) {
        return new Button(text, color, ButtonsHandler.buttonHandler, [
            {
                name: "data-bs-toggle",
                value: "modal",
            },
            {name: "data-bs-target", value: "#update-board-modal"},
        ]).buildButton();
    }

    /**
     * @method Getter to get the board id
     * @returns {*} - The board id
     */
    getId() {
        return this.#id;
    }

    /**
     * @method Getter to get the board name
     * @returns {*} - The board name
     */
    getName() {
        return this.#name;
    }
}
