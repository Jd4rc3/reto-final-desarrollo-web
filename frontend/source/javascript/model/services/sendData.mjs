/**
 * Class for sending data to the server.
 */
export class DataSender {
    constructor() {
    }

    /**
     * Send data to an endpoint through POST method
     *
     * @param url {string} endpoint url
     * @param data {object} data to send
     * @returns {Promise<any>} response
     */
    static async postData(url = '', data = {}) {
        const response = await fetch(url, {
            method: 'POST', mode: 'cors', cache: 'no-cache', credentials: 'same-origin', headers: {
                'Content-Type': 'application/json'
            }, redirect: 'follow', referrerPolicy: 'no-referrer', body: JSON.stringify(data)
        });
        return response.json();
    }

    /**
     * Send data to an endpoint through GET method
     *
     * @param url
     * @returns {Promise<any>}
     */
    static async getData(url) {
        const response = await fetch(url);
        return response.json();
    }
}