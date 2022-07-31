import { Config, getBoardIdFromURL } from "../../config.mjs";
import { DataSender } from "./sendData.mjs";

export class TasksService {
  static BackendURL = Config.BackendURL;

  constructor() {}

  static create(task) {
    return DataSender.sendData(
      `${TasksService.BackendURL}/tasks`,
      { columnId: 1, ...task },
      "POST"
    );
  }

  static move(taskId, columnId) {
    return DataSender.sendData(
      `${TasksService.BackendURL}/tasks/move/${taskId}/${columnId}`,
      {},
      "PATCH"
    );
  }

  static update(id, task) {
    return DataSender.sendData(
      `${TasksService.BackendURL}/tasks/${id}`,
      task,
      "PUT"
    );
  }

  static async delete(id) {
    return await DataSender.sendData(
      `${TasksService.BackendURL}/tasks/${id}`,
      {},
      "DELETE"
    );
  }

  static async createTask() {
    const { name, description, deliveryDate } = this.getInputValues();

    if (name.trim() === "") {
      alert("Name is required");
      return;
    }

    const boardId = getBoardIdFromURL();
    const data = await this.create({
      name,
      description,
      deliveryDate,
      boardId,
    });

    if (!data.error) {
      window.location.reload();
      return;
    }

    alert(data.message);
  }

  static async updateTask(id) {
    const task = TasksService.getInputValues();

    const { name, description, deliveryDate } = task;

    if (name.trim() === "") {
      alert("Name or description is required");
      return;
    }

    const data = await this.update(id, { name, description, deliveryDate });

    if (!data.error) {
      window.location.reload();
      return;
    }

    alert(data.message);
  }

  static async moveTask(id, columnId) {
    const data = await this.move(id, columnId);

    if (!data.error) {
      window.location.reload();
      return;
    }

    alert(data.message);
  }

  static async deleteTask(id) {
    const data = await this.delete(id);

    if (!data.error) {
      window.location.reload();
      return;
    }

    alert(data.message);
  }

  static getTaskValues(id) {
    const task = document.querySelector(`#task-${id}`).childNodes;
    const nodes = Array.from(task);

    const name = nodes.filter((node) => node.className === "task-name")[0]
      .textContent;
    const description = nodes.filter(
      (node) => node.className === "task-description"
    )[0].textContent;
    const deliveryDate = nodes.filter(
      (node) => node.className === "task-delivery-date"
    )[0].textContent;

    return { name, description, deliveryDate };
  }

  static getInputValues() {
    return {
      name: document.querySelector("#boardNameToEdit").value,
      description: document.querySelector("#description").value,
      deliveryDate: document.querySelector("#deliveryDate").value,
    };
  }

  static fillFields(id) {
    const task = this.getTaskValues(id);

    document.querySelector("#boardNameToEdit").value = task.name;
    document.querySelector("#description").value = task.description;
    document.querySelector("#deliveryDate").value = task.deliveryDate;
  }
}
