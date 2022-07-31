/**
 * History Model is a log of all movements of the task
 */

/**
 * @class HistoryModel - Handles all the history of a task
 */
export class HistoryModel {
    #id;
    #taskId;
    #previousId;
    #currentId;
    #createdAt;

    constructor(history) {
        const {id, taskId, previousId, currentId, createdAt} = history;
        this.#id = id;
        this.#taskId = taskId;
        this.#previousId = previousId;
        this.#currentId = currentId;
        this.#createdAt = createdAt;
    }

    /**
     * @method Builds the history container for the task
     * @returns {HTMLParagraphElement} - The history container
     */
    drawHistory() {
        const previous = this.getColumnName(this.#previousId);
        const current = this.getColumnName(this.#currentId);
        const createdAt = this.#createdAt;

        let row = `${previous} | ${current} | ${createdAt}`;

        const container = document.createElement("p");
        container.classList.add("log");
        container.innerHTML = row;

        return container;
    }

    /**
     * @method getColumnName - Gets the name of the column to which the task belongs
     * @param id - The id of the column
     * @returns {string} - The name of the column
     */
    getColumnName(id) {
        return localStorage.getItem(id);
    }
}
