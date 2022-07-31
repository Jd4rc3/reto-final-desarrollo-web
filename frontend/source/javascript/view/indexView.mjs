/**
 * view for main view of boards
 */
export class IndexView {
    #data

    constructor(boards) {
        this.#data = boards
    }

    async render() {
        const boards = [...this.#data];
        const container = document.querySelector('#container')

        boards.forEach(board => {
            const createdBoard = this.createBoard(board);
            container.append(createdBoard)
        });
    }

    createBoard(board) {
        const name = board.getName();
        const createdAt = board.getCreatedAt();
        const div = document.createElement('div');
        const boardName = document.createElement('p');
        const boardCreatedAt = document.createElement('p');

        div.classList.add('board-container');
        div.addEventListener('click', this.goToBoard.bind(this, board.getId()));
        boardName.textContent = name;
        boardCreatedAt.textContent = createdAt;

        div.append(boardName, boardCreatedAt)

        return div
    }

    goToBoard(boardId) {
        window.location.href = `board.html?id=${boardId}`
    }
}