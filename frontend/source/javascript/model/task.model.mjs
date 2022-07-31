/**
 * Task model class represents a task and its fields which can be manipulated.
 */
import {HistoryModel} from "./history.model.mjs";

export class TaskModel {
    #id;
    #name;
    #createdAt;
    #updatedAt;
    #columnId;
    #boardId
    #description
    #deliveryDate
    #history

    constructor(task) {
        const {id, name, createdAt, updatedAt, columnId, boardId, description, deliveryDate, history} = task;

        this.#id = id;
        this.#name = name;
        this.#createdAt = createdAt;
        this.#updatedAt = updatedAt;
        this.#columnId = columnId;
        this.#boardId = boardId;
        this.#description = description;
        this.#deliveryDate = deliveryDate;
        this.#history = [];

        history.forEach(row => {
            this.#history.push(new HistoryModel(row));
        })
    }

    drawTask() {
        const container = document.createElement('div');
        const taskHistory = document.createElement('div');
        const description = this.#description ? this.#description : "";
        const deliveryDate = this.#deliveryDate ? this.#deliveryDate : "";

        container.classList.add('task');
        taskHistory.classList.add('task-history');
        container.innerHTML = `
            <div class="task-name">${this.#name}</div>
            <div class="task-description">${description}</div>
            <div class="task-delivery-date">${deliveryDate}</div>
        `;

        this.#history.forEach(row => {
            taskHistory.append(row.drawHistory())
        });

        container.append(taskHistory);

        return container;
    }

    getName() {
        return this.#name;
    }
}

/*
{
    "id": 5,
    "columnId": 1,
    "boardId": 4,
    "name": "aaaaaaaaaaaaaaaaaaaaaaaa",
    "description": null,
    "deliveryDate": null,
    "createdAt": "2022-07-30T02:48:23.778988684Z",
    "updatedAt": null,
    "history": [
    {
        "id": 4,
        "taskId": 5,
        "previousId": 1,
        "currentId": 1,
        "createdAt": "2022-07-30T02:48:23.827993114Z"
    }
]
}*/
