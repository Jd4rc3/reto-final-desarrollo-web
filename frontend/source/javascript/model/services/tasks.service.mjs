import {Config, getBoardIdFromURL} from "../../config.mjs";
import {DataSender} from "./sendData.mjs";

/**
 * Tasks service to handle all the CRUD operations for the tasks.
 */
export class TasksService {
    static BackendURL = Config.BackendURL;

    constructor() {
    }

    /**
     * Creates a new task in the backend.
     * @param task
     * @returns {Promise<*>}
     */
    static create(task) {
        return DataSender.sendData(`${TasksService.BackendURL}/tasks`, {columnId: 1, ...task}, "POST");
    }

    /**
     * Moves a task to a new column.
     * @param taskId {number} id of the task to move
     * @param columnId {number} id of the column to move to
     * @returns {Promise<*>} Promise with the response from the backend
     */
    static move(taskId, columnId) {
        return DataSender.sendData(`${TasksService.BackendURL}/tasks/move/${taskId}/${columnId}`, {}, "PATCH");
    }

    /**
     * Updates a task in the backend.
     * @param id    {number} id of the task to update
     * @param task {object} object with the new values for the task
     * @returns {Promise<*>} Promise with the response from the backend
     */
    static update(id, task) {
        return DataSender.sendData(`${TasksService.BackendURL}/tasks/${id}`, task, "PUT");
    }

    static async delete(id) {
        return await DataSender.sendData(`${TasksService.BackendURL}/tasks/${id}`, {}, "DELETE");
    }

    /**
     * Get all task values from a modal check if at least one field is filled and send the data to the backend.
     */
    static async createTask() {
        const {name, description, deliveryDate} = this.getInputValues();

        if (name.trim() === "") {
            alert("Name is required");
            return;
        }

        const boardId = getBoardIdFromURL();
        const data = await this.create({
            name, description, deliveryDate, boardId,
        });

        if (!data.error) {
            window.location.reload();
            return;
        }

        alert(data.message);
    }

    /**
     * Update a task in the backend with the values from the modal.
     * @param id {number} id of the task to update
     */
    static async updateTask(id) {
        const task = TasksService.getInputValues();

        const {name, description, deliveryDate} = task;

        if (name.trim() === "") {
            alert("Name or description is required");
            return;
        }

        const data = await this.update(id, {name, description, deliveryDate});

        if (!data.error) {
            window.location.reload();
            return;
        }

        alert(data.message);
    }

    static async moveTask(id, columnId) {
        const data = await this.move(id, columnId);

        if (!data.error) {
            window.location.reload();
            return;
        }

        alert(data.message);
    }

    /**
     * Delete a task in the backend with the given id.
     * @param id {number} id of the task to delete
     */
    static async deleteTask(id) {
        const data = await this.delete(id);

        if (!data.error) {
            window.location.reload();
            return;
        }

        alert(data.message);
    }

    /**
     * Get the values of a task from the backend.
     * @param id {number} id of the task to get
     * @returns {{name: string, description: string, deliveryDate: string}} object with the values of the task
     */
    static getTaskValues(id) {
        const task = document.querySelector(`#task-${id}`).childNodes;
        const nodes = Array.from(task);

        const name = nodes.filter((node) => node.className === "task-name")[0].textContent;
        const description = nodes.filter((node) => node.className === "task-description")[0].textContent;
        const deliveryDate = nodes.filter((node) => node.className === "task-delivery-date")[0].textContent;

        return {name, description, deliveryDate};
    }

    /**
     * Get the values of a task from the modal and return them as an object.
     * @returns {{name, description, deliveryDate}}
     */
    static getInputValues() {
        return {
            name: document.querySelector("#boardNameToEdit").value,
            description: document.querySelector("#description").value,
            deliveryDate: document.querySelector("#deliveryDate").value,
        };
    }

    /**
     * Get the values of task component and fill the modal with them.
     * @param id {number} id of the task to fill the modal with
     */
    static fillFields(id) {
        const task = this.getTaskValues(id);

        document.querySelector("#boardNameToEdit").value = task.name;
        document.querySelector("#description").value = task.description;
        document.querySelector("#deliveryDate").value = task.deliveryDate;
    }
}
