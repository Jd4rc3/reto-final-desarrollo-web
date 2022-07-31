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

        document.querySelector(".modal-title").innerHTML = "New task";
        document.querySelector("#modal-footer-button").innerHTML = "Create task";
        const modalBody = document.querySelector('#modal-body')
        const description = document.createElement('textarea')
        description.setAttribute('id', 'description')
        description.setAttribute('placeholder', 'Description')

        const deliveryDate = document.createElement('input')
        deliveryDate.setAttribute('id', 'deliveryDate')
        deliveryDate.setAttribute('type', 'date')

        modalBody.append(description, deliveryDate)

        const createdBoard = this.renderBoard(board);
        container.append(await createdBoard)
    }

    renderBoard(board) {
        return board.drawBoard();
    }
}