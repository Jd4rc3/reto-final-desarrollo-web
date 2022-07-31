import {DataSender} from "./sendData.mjs";
import {Config} from "../../config.mjs";
import {BoardModel} from "../board.model.mjs";

/**
 * Board service to handle all the CRUD operations for the boards.
 */
export class BoardsService {
    #BackendURL

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

        return data.map(board => new BoardModel(board))
    }

    /**
     * Get a board by id.
     *
     * @param id {number} board id
     * @returns {Promise<*>}    response
     */
    async getBoardById(id) {
        const board = await DataSender.getData(`${this.#BackendURL}/boards/${id}`);
        return new BoardModel(board.data);
    }

    /**
     * Delete a board by id.
     *
     * @param id {number} board id
     * @returns {Promise<*>} response
     */
    async deleteBoard(id) {
        return await DataSender.sendData(`${this.#BackendURL}/boards/${id}`, {}, 'DELETE');
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
     * @param id {number} board id
     * @param data {object} board data
     * @returns {Promise<*>} response
     */
    async updateBoard(id, data) {
        const board = DataSender.sendData(`${this.#BackendURL}/boards/${id}`, data, 'PUT');
        return new BoardModel(board.data);
    }
}