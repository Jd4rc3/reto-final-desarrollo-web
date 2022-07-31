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
}