/**
 * Task model class represents a task and its fields which can be manipulated.
 */
import { HistoryModel } from "./history.model.mjs";
import { toggleModal } from "../view/modal.mjs";
import { ButtonsHandler } from "../controller/buttons.controller.mjs";
import { Button } from "../view/components/button.component.mjs";

export class TaskModel {
  #id;
  #name;
  #createdAt;
  #updatedAt;
  #columnId;
  #boardId;
  #description;
  #deliveryDate;
  #history;

  constructor(task) {
    const {
      id,
      name,
      createdAt,
      updatedAt,
      columnId,
      boardId,
      description,
      deliveryDate,
      history,
    } = task;

    this.#id = id;
    this.#name = name;
    this.#createdAt = createdAt;
    this.#updatedAt = updatedAt;
    this.#columnId = columnId;
    this.#boardId = boardId;
    this.#description = description;
    this.#deliveryDate = deliveryDate;
    this.#history = [];

    history.forEach((row) => {
      this.#history.push(new HistoryModel(row));
    });
  }

  drawTask() {
    const container = document.createElement("div");
    const { editButton, deleteButton, backButton, nextButton } =
      this.getButtons();

    const taskHistory = document.createElement("div");
    const description = this.#description ? this.#description : "";
    const deliveryDate = this.#deliveryDate ? this.#deliveryDate : "";
    const buttonsContainer = document.createElement("div");
    buttonsContainer.classList.add("buttons-container");
    buttonsContainer.append(editButton, deleteButton, backButton, nextButton);

    container.classList.add("task");
    container.id = `task-${this.#id}`;
    taskHistory.classList.add("task-history");
    container.innerHTML = `
            <div class="task-name">${this.#name}</div>
            <div class="task-description">${description}</div>
            <div class="task-delivery-date">${deliveryDate}</div>
        `;

    container.setAttribute(
      "style",
      `background-color: ${this.getRandomColor()}`
    );

    this.#history.forEach((row) => {
      taskHistory.append(row.drawHistory());
    });

    container.append(taskHistory, buttonsContainer);

    return container;
  }

  getButtons() {
    const backButton = this.handleBackButton();
    const nextButton = this.handleNextButton();

    const editButton = new Button("📝", "info", ButtonsHandler.buttonHandler, [
      {
        name: "data-bs-toggle",
        value: "modal",
      },
      { name: "data-bs-target", value: "#update-board-modal" },
      { name: "id", value: "editTask" },
    ]).buildButton();

    const deleteButton = new Button(
      "🗑",
      "danger",
      ButtonsHandler.buttonHandler,
      [
        {
          name: "id",
          value: "deleteTask",
        },
      ]
    ).buildButton();

    return { editButton, deleteButton, backButton, nextButton };
  }

  handleNextButton() {
    let nextButton;
    const id = { name: "id", value: "nextTask" };
    if (this.#columnId === 3) {
      nextButton = new Button("🔜", "info", ButtonsHandler.buttonHandler, [
        {
          name: "disabled",
          value: "true",
        },
        { ...id },
      ]).buildButton();
    } else {
      nextButton = new Button("🔜", "info", ButtonsHandler.buttonHandler, [
        {
          name: "value",
          value: `${this.#columnId + 1}`,
        },
        { ...id },
      ]).buildButton();
    }

    return nextButton;
  }

  handleBackButton() {
    let backButton;
    const id = { name: "id", value: "backTask" };
    if (this.#columnId === 1) {
      backButton = new Button("🔙", "info", ButtonsHandler.buttonHandler, [
        {
          name: "disabled",
          value: "true",
        },
        { ...id },
      ]).buildButton();
    } else {
      backButton = new Button("🔙", "info", ButtonsHandler.buttonHandler, [
        {
          name: "value",
          value: `${this.#columnId - 1}`,
        },
        { ...id },
      ]).buildButton();
    }

    return backButton;
  }

  getName() {
    return this.#name;
  }

  getRandomColor() {
    const letters = "0123456789ABCDEF";
    let color = "#";
    for (let i = 0; i < 6; i++) {
      color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
  }
}
