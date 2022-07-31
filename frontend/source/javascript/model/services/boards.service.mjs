import {DataSender} from "./sendData.mjs";
import {Config} from "../../config.mjs";
import {BoardModel} from "../board.model.mjs";
import {toggleModal} from "../../view/modal.mjs";

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
    async getAllBoards() {
        const board = await DataSender.getData(`${this.#BackendURL}/boards`);
        const {data} = board;

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
        return await DataSender.sendData(`${BoardsService.BackendURL}/boards/${id}`, {}, "DELETE");
    }

    /**
     * Create a new board.
     *
     * @param data {object} board data
     * @returns {Promise<*>} response
     */
    async createBoard(data) {
        const board = await DataSender.sendData(`${this.#BackendURL}/boards`, data);
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
        console.log(id)
        const BackedURL = Config.BackendURL;

        return DataSender.sendData(`${BackedURL}/boards/${id}`, {name: newName}, "PUT");
    }
}
