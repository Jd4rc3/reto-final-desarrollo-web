/**
 * History Model is a log of all movements of the task
 */
export class HistoryModel {
    #id;
    #taskId;
    #previousId;
    #currentId;
    #createdAt;

    constructor(id, taskId, previousId, currentId, createdAt) {
        this.#id = id;
        this.#taskId = taskId;
        this.#previousId = previousId;
        this.#currentId = currentId;
        this.#createdAt = createdAt;
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