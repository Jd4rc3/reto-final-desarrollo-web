import {ColumnModel} from "./column.model.mjs";

/**
 * Board model class represents a board and its fields which can be manipulated.
 */
export class BoardModel {
    #id
    #name
    #createdAt
    #updatedAt
    #columns

    constructor(board) {
        const {id, name, createdAt, updatedAt, columns} = board

        this.#columns = [];
        this.#id = id;
        this.#name = name;
        this.#createdAt = createdAt;
        this.#updatedAt = updatedAt;

        columns.forEach(column => {
            this.#columns.push(new ColumnModel(column));
        })
    }

    drawBoard() {
        const columns = this.#columns;
        const container = document.createElement('div')
        const columnsContainer = document.createElement('div')
        container.classList.add('board-container')
        container.innerHTML = `
        <h1>${this.#name}</h1>
        `

        columns.forEach(column => {
            columnsContainer.append(column.drawColumn())
        })

        container.append(columnsContainer)

        return container;
    }

    getId() {
        return this.#id;
    }

    setId(value) {
        this.#id = value;
    }

    getName() {
        return this.#name;
    }

    setName(value) {
        this.#name = value;
    }

    getCreatedAt() {
        return this.#createdAt;
    }

    setCreatedAt(value) {
        this.#createdAt = value;
    }

    getUpdatedAt() {
        return this.#updatedAt;
    }

    setUpdatedAt(value) {
        this.#updatedAt = value;
    }

    getColumns() {
        return this.#columns;
    }

    setColumns(value) {
        this.#columns = value;
    }
}

// {
//     "id": 3,
//     "name": "nuevo",
//     "createdAt": "2022-07-30T01:45:38Z",
//     "updatedAt": null,
//     "columns": [
//     {
//         "id": 1,
//         "name": "Por realizar",
//         "createdAt": "2022-07-30T06:15:19Z",
//         "updatedAt": null,
//         "tasks": []
//     },
//     {
//         "id": 2,
//         "name": "En progreso",
//         "createdAt": "2022-07-30T06:15:19Z",
//         "updatedAt": null,
//         "tasks": []
//     },
//     {
//         "id": 3,
//         "name": "Terminado",
//         "createdAt": "2022-07-30T06:15:19Z",
//         "updatedAt": null,
//         "tasks": []
//     }
// ]
// }