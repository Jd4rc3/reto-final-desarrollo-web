import { DataSender } from "./sendData.mjs";
import { Config } from "../../config.mjs";
import { BoardModel } from "../board.model.mjs";
import { Modal, toggleModal } from "../../view/modal.mjs";
import { Card } from "../../view/components/card.component.mjs";

/**
 * Board service to handle all the CRUD operations for the boards.
 */
export class BoardsService {
  static BackendURL = Config.BackendURL;
  #BackendURL;

  constructor() {
    this.#BackendURL = Config.BackendURL;
  }

  /**
   * Get all the boards.
   *
   * @returns {Promise<*>} response
   */
  static async getAllBoards() {
    const board = await DataSender.getData(
      `${BoardsService.BackendURL}/boards`
    );
    const { data } = board;

    return data.map((board) => new BoardModel(board));
  }

  /**
   * Get a board by id.
   *
   * @param id {number} board id
   * @returns {Promise<*>}    response
   */
  async getBoardById(id) {
    const board = await DataSender.getData(`${this.#BackendURL}/boards/${id}`);

    if (board.error) {
      alert(board.message);
      window.location.href = "index.html";
    }

    return new BoardModel(board.data);
  }

  /**
   * Delete a board by id.
   *
   * @param id {string} board id
   * @returns {Promise<*>} response
   */
  static async deleteBoard(id) {
    return await DataSender.sendData(
      `${BoardsService.BackendURL}/boards/${id}`,
      {},
      "DELETE"
    );
  }

  /**
   * Create a new board.
   *
   * @param data {object} board data
   * @returns {Promise<*>} response
   */
  static async createBoardPost(data) {
    const board = await DataSender.sendData(
      `${BoardsService.BackendURL}/boards`,
      { name: data }
    );
    return new BoardModel(board.data);
  }

  /**
   * Update a board by id.
   *
   * @param id {string} board id
   * @param newName
   * @returns {Promise<*>} response
   */
  static updateBoardName(id, newName) {
    const BackedURL = Config.BackendURL;

    return DataSender.sendData(
      `${BackedURL}/boards/${id}`,
      { name: newName },
      "PUT"
    );
  }

  static async createBoard() {
    const name = document.querySelector("#boardNameToEdit").value;
    let board;

    if (!(await Modal.isEmpty())) {
      board = await BoardsService.createBoardPost(name);

      if (!board.error) {
        const newBoard = new Card(board);
        newBoard.newCard();
        Modal.cleanInput();
        toggleModal();
        return;
      }

      alert(board.message);
      toggleModal();
    }
  }

  static async updateBoard() {
    const id = localStorage.getItem("boardId");
    const name = document.querySelector("#boardNameToEdit").value;
    let board;

    if (!(await Modal.isEmpty())) {
      board = await BoardsService.updateBoardName(id, name);

      if (!board.error) {
        const boardContainer = document.querySelector(`#board-${id}`);
        boardContainer.childNodes[0].textContent = name;
        Modal.cleanInput();
        toggleModal();
        return;
      }

      alert(board.message);
      toggleModal();
    }
  }
}
