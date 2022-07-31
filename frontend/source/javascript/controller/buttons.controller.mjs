import { BoardsService } from "../model/services/boards.service.mjs";
import { Card } from "../view/components/card.component.mjs";
import { TasksService } from "../model/services/tasks.service.mjs";
import { Modal } from "../view/modal.mjs";

/**
 * @class ButtonsHandler - Handles all the buttons clicks in krello app
 */
export class ButtonsHandler {
  constructor() {}

  /**
   * @method Handles all button clicks on krello
   * @param event
   * @returns {Promise<void>}
   */
  static async buttonHandler(event) {
    const button = event.target;
    const card = button.parentElement.parentElement;
    const boardId = card.parentElement.id;

    ButtonsHandler.updateModalToNewBoard(button);

    await ButtonsHandler.commitNewTask(button);

    await ButtonsHandler.moveTaskButtons(button);

    ButtonsHandler.updateTaskModalToNew(button);

    ButtonsHandler.fillModalData(button);

    ButtonsHandler.updateModalToBoardUpdate(button);

    await ButtonsHandler.commitModalUpdate(button);

    await ButtonsHandler.handleDeleteButtons(button, boardId);

    await ButtonsHandler.updateTask(button);

    await ButtonsHandler.updateBoard(button);
  }

  /**
   * @method Commits the board update using the BoardsService
   * @param button - The button that was clicked
   */
  static async updateBoard(button) {
    if (button.innerHTML === "Edit board") {
      await BoardsService.updateBoard();
    }
  }

  /**
   * @method commits the update using the task service
   * @param button - The button that was clicked
   */
  static async updateTask(button) {
    if (button.innerHTML === "Edit task") {
      const taskId = localStorage.getItem("taskId");
      await TasksService.updateTask(taskId);
    }
  }

  /**
   * Handles the delete button click to board and task getting its parent component
   * @param button - The button that was clicked
   * @param boardId - The board id
   */
  static async handleDeleteButtons(button, boardId) {
    if (button.innerHTML === "🗑") {
      if (confirm("Are you sure you want to delete this?")) {
        if (button.id === "deleteTask") {
          const taskId = button.parentElement.parentElement.id.split("-")[1];
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
    }
  }

  /**
   * @method Call createBoard service to create a new board using the modal inputs
   * @param button - The button that was clicked
   */
  static async commitModalUpdate(button) {
    if (button.innerHTML === "Create") {
      await BoardsService.createBoard();
    }
  }

  /**
   * @method Updates the modal title and button text to update a board
   * @param button - The button that was clicked
   */
  static updateModalToBoardUpdate(button) {
    if (button.id === "updateBoardButton") {
      document.querySelector(".modal-title").innerHTML = "Update Board";
      document.querySelector("#modal-footer-button").innerHTML = "Edit board";
    }
  }

  /**
   * @method Extracts task id from the button parent that was clicked and fill the modal with the task data
   * @param button - The button that was clicked
   */
  static fillModalData(button) {
    if (button.id === "editTask") {
      const taskId = button.parentElement.parentElement.id.split("-")[1];
      localStorage.setItem("taskId", taskId);
      TasksService.fillFields(taskId);
      document.querySelector(".modal-title").innerHTML = "EditTask";
      document.querySelector("#modal-footer-button").innerHTML = "Edit task";
    }
  }

  /**
   * @method Change the modal title and button text
   * @param button - The button that was clicked
   */
  static updateTaskModalToNew(button) {
    if (button.innerHTML === "New Task") {
      document.querySelector(".modal-title").innerHTML = "New Task";
      document.querySelector("#modal-footer-button").innerHTML = "Create task";
      Modal.cleanAllInputs();
    }
  }

  /**
   * @method Extracts the task id from the button parent that was clicked and moves the task to the new board
   * @param button - The button that was clicked
   */
  static async moveTaskButtons(button) {
    if (button.innerHTML === "🔙" || button.innerHTML === "🔜") {
      const newColumnId = button.value;
      const taskId = button.parentElement.parentElement.id.split("-")[1];
      await TasksService.moveTask(taskId, newColumnId);
    }
  }

  /**
   * @method Takes values from modal inputs and creates a new task using the TasksService
   * @param button - The button that was clicked
   */
  static async commitNewTask(button) {
    if (button.innerHTML === "Create task") {
      await TasksService.createTask();
    }
  }

  /**
   * @method Updates the modal to the new board
   * @param button - The button that was clicked
   */
  static updateModalToNewBoard(button) {
    if (button.innerHTML === "New Board") {
      document.querySelector(".modal-title").innerHTML = "New Board";
      document.querySelector("#modal-footer-button").innerHTML = "Create";
    }
  }
}
