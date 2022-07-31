/**
 * Task model class represents a task and its fields which can be manipulated.
 */
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

    constructor(id, name, createdAt, updatedAt, columnId, boardId, description, deliveryDate, history) {
        this.#id = id;
        this.#name = name;
        this.#createdAt = createdAt;
        this.#updatedAt = updatedAt;
        this.#columnId = columnId;
        this.#boardId = boardId;
        this.#description = description;
        this.#deliveryDate = deliveryDate;
        this.#history = history;
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
