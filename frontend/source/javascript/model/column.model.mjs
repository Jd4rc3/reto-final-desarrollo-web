/**
 * model class represents a column inside a board and its fields which can be manipulated.
 */
export class ColumnModel {
    #id;
    #name;
    #createdAt;
    #updatedAt;
    #tasks;

    constructor(id, name, createdAt, updatedAt, tasks) {
        this.#id = id;
        this.#name = name;
        this.#createdAt = createdAt;
        this.#updatedAt = updatedAt;
        this.#tasks = tasks;
    }

    getName() {
        return this.#name;
    }
}

// {
//         "id": 1,
//         "name": "Por realizar",
//         "createdAt": "2022-07-30T06:15:19Z",
//         "updatedAt": null,
//         "tasks": []
//     },