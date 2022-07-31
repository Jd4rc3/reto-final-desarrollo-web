import {BoardsService} from "../model/services/boards.service.mjs";
import {Card} from "../view/components/card.component.mjs";

export class ButtonsHandler {
    constructor() {
    }

    static async buttonHandler(event) {
        const button = event.target;
        const card = button.parentElement.parentElement;
        const boardId = card.parentElement.id;

        if (button.innerHTML === "New Board") {
            document.querySelector(".modal-title").innerHTML = "New Board";
            document.querySelector("#modal-footer-button").innerHTML = "Create";
            // toggleModal();
            return;
        }

        if (button.innerHTML === "Create") {
            await BoardsService.createBoard();
            return;
        }

        if (button.innerHTML === "🗑") {
            if (confirm("Are you sure you want to delete this card?")) {
                let boardIdSplinted = boardId.split("-")[1];
                const board = await BoardsService.deleteBoard(boardIdSplinted);

                if (board.error) {
                    alert(board.message);
                    return;
                }

                Card.deleteCard(boardId);
            }

            return;
        }

        if (button.innerHTML === "Edit") {
            await BoardsService.updateBoard();
        }
    }
}