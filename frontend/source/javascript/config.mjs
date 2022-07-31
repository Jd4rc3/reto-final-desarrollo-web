export class Config {
    static BackendURL = "http://localhost:9091/api/v1";
    // static FrontendURL = "http://localhost:5500/source";
}

export function getBoardIdFromURL() {
    const URL = new URLSearchParams(window.location.search);
    return parseInt(URL.get('id'));
}