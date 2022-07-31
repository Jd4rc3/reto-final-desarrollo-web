import {BoardsService} from "../model/services/boards.service.mjs";
import {Card} from "../view/components/card.component.mjs";
import {TasksService} from "../model/services/tasks.service.mjs";
import {Modal} from "../view/modal.mjs";

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
            return;
        }

        if (button.innerHTML === "Create task") {
            await TasksService.createTask();
            return;
        }

        if (button.innerHTML === "🔙" || button.innerHTML === "🔜") {
            const newColumnId = button.value;
            const taskId = button.parentElement.id.split('-')[1];
            await TasksService.moveTask(taskId,newColumnId);
        }

        if (button.innerHTML === "New Task") {
            document.querySelector(".modal-title").innerHTML = "New Task";
            document.querySelector("#modal-footer-button").innerHTML = "Create task";
            Modal.cleanAllInputs()

            return;
        }

        if (button.id === "editTask") {
            const taskId = button.parentElement.id.split("-")[1];
            localStorage.setItem("taskId", taskId);
            TasksService.fillFields(taskId);
            document.querySelector(".modal-title").innerHTML = "EditTask";
            document.querySelector("#modal-footer-button").innerHTML = "Edit task";
            return;
        }

        if (button.id === "updateBoardButton") {
            document.querySelector(".modal-title").innerHTML = "Update Board";
            document.querySelector("#modal-footer-button").innerHTML = "Edit board";
            return;
        }

        if (button.innerHTML === "Create") {
            await BoardsService.createBoard();
            return;
        }

        if (button.innerHTML === "🗑") {
            if (confirm("Are you sure you want to delete this?")) {
                if (button.id === "deleteTask") {
                    const taskId = button.parentElement.id.split("-")[1];
                    await TasksService.deleteTask(taskId);

                    return;
                }

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

        if (button.innerHTML === "Edit task") {
            const taskId = localStorage.getItem("taskId");
            await TasksService.updateTask(taskId);
            return;
        }

        if (button.innerHTML === "Edit board") {
            await BoardsService.updateBoard();
        }
    }
}