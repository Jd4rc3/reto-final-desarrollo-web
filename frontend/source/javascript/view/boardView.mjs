/**
 * view for see a board detail
 */
export class BoardView {
    #data

    constructor(board) {
        this.#data = board
    }

    async render() {
        const board = await this.#data;
        const container = document.querySelector('#container')

        const createdBoard = this.renderBoard(board);
        container.append(await createdBoard)
    }

    renderBoard(board) {
        return board.drawBoard();
    }

    // div.addEventListener('click', this.goToBoard.bind(this, board.getId()));
    /*  goToBoard(boardId) {
          window.location.href = `index.html`
      }*/
}

// {
//     "id": 4,
//     "name": "hola4",
//     "createdAt": "2022-07-30T02:34:40Z",
//     "updatedAt": "2022-07-30T02:38:24Z",
//     "columns": [
//     {
//         "id": 1,
//         "name": "Por realizar",
//         "createdAt": "2022-07-30T06:15:19Z",
//         "updatedAt": null,
//         "tasks": [
//             {
//                 "id": 5,
//                 "columnId": 1,
//                 "boardId": 4,
//                 "name": "aaaaaaaaaaaaaaaaaaaaaaaa",
//                 "description": null,
//                 "deliveryDate": null,
//                 "createdAt": "2022-07-30T02:48:24Z",
//                 "updatedAt": null,
//                 "history": [
//                     {
//                         "id": 4,
//                         "taskId": 5,
//                         "previousId": 1,
//                         "currentId": 1,
//                         "createdAt": "2022-07-30T02:48:24Z"
//                     }
//                 ]
//             }
//         ]
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