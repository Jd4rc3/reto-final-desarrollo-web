import { TaskModel } from "./task.model.mjs";

/**
 * model class represents a column inside a board and its fields which can be manipulated.
 */
export class ColumnModel {
  #id;
  #name;
  #createdAt;
  #updatedAt;
  #tasks;

  constructor(column) {
    const { id, name, createdAt, updatedAt, tasks } = column;

    this.#tasks = [];
    this.#id = id;
    this.#name = name;
    this.#createdAt = createdAt;
    this.#updatedAt = updatedAt;

    tasks.forEach((task) => {
      this.#tasks.push(new TaskModel(task));
    });

    this.saveColumnNames();
  }

  drawColumn() {
    const container = document.createElement("div");
    const tasks = document.createElement("div");

    container.classList.add("column");
    container.id = `column-${this.#id}`;
    tasks.classList.add("column-tasks");
    container.innerHTML = `
            <h2 class="column-name" id="${this.#id}">${this.#name}</h2>
        `;

    this.#tasks.forEach((task) => {
      tasks.append(task.drawTask());
    });

    container.append(tasks);

    return container;
  }

  saveColumnNames() {
    localStorage.setItem(this.#id, this.#name);
  }

  getName() {
    return this.#name;
  }
}
