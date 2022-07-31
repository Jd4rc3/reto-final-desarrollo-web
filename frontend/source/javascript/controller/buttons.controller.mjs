import {BoardsService} from "../model/services/boards.service.mjs";
import {Card} from "../view/components/card.component.mjs";
import {IndexView} from "../view/indexView.mjs";
import {toggleModal} from "../view/modal.mjs";

export class ButtonsHandler {
    constructor() {
    }

    static async buttonHandler(event) {
        const button = event.target;
        const card = button.parentElement.parentElement;
        const boardId = card.parentElement.id;

        if (button.textContent === "📝") {
            IndexView.saveEditingBoard(boardId);
        }

        if (button.innerHTML === "New Board") {
            
            toggleModal();
            return;
        }

        if (button.innerHTML === "🗑") {
            if (confirm("Are you sure you want to delete this card?")) {
                let boardIdSplinted = boardId.split("-")[1];

                await BoardsService.deleteBoard(boardIdSplinted);
                Card.deleteCard(boardId);
            }

            return;
        }

        if (button.innerHTML === "Edit") {
            const input = document.querySelector("#boardNameToEdit");
            const id = localStorage.getItem("boardId");
            const newName = input.value;
            const board = await BoardsService.updateBoardName(id, newName);

            console.log(board)

            if (newName.length > 0) {
                if (board.error) {
                    alert(board.message);
                    window.location.href = "index.html";
                }

                const boardContainer = document.querySelector(`#board-${id}`);
                boardContainer.childNodes[0].textContent = newName;
                input.value = "";
                toggleModal();
                return;
            }

            alert("Please enter a name");
        }
    }
}