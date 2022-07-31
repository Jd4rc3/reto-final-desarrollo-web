export class Config {
    static BackendURL = "http://localhost:9091/api/v1";
}

/**
 * Get the board if from url and return it.
 * @returns {number} board id
 */
export function getBoardIdFromURL() {
    const URL = new URLSearchParams(window.location.search);
    return parseInt(URL.get("id"));
}
