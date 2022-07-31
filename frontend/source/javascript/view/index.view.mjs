'use strict';

import {Navbar} from "./components/navbar.component.mjs";
import {Table} from "./components/table.component.mjs";

export class IndexView {
    #privateContainer;
    #privateData;

    constructor() {
        this.#privateContainer = document.querySelector('.container');
    }

    set Data(data) {
        this.#privateData = data;
    }

    init() {
    }
}