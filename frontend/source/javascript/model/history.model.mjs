/**
 * History Model is a log of all movements of the task
 */
import {ColumnModel} from "./column.model.mjs";

export class HistoryModel {
    #id;
    #taskId;
    #previousId;
    #currentId;
    #createdAt;

    constructor(history) {
        const {id, taskId, previousId, currentId, createdAt} = history
        this.#id = id;
        this.#taskId = taskId;
        this.#previousId = previousId;
        this.#currentId = currentId;
        this.#createdAt = createdAt;
    }

    drawHistory() {
        const previous = this.getColumnName(this.#previousId);
        const current = this.getColumnName(this.#currentId);
        const createdAt = this.#createdAt;

        let row = `${previous} | ${current} | ${createdAt}`

        const container = document.createElement('p');
        container.classList.add('log');
        container.innerHTML = row;

        return container;
    }

    getColumnName(id) {
        return localStorage.getItem(id)
    }


    getTaskId() {
        return this.#taskId;
    }

    getPreviousId() {
        return this.#previousId;
    }

    getCurrentId() {
        return this.#currentId;
    }

    getCreatedAt() {
        return this.#createdAt;
    }
}