import { ButtonsHandler } from "../controller/buttons.controller.mjs";

export class Modal {
  constructor() {}

  render() {
    this.buildModal();
  }

  buildModal() {
    const modal = document.createElement("div");
    modal.setAttribute("class", "modal fade");
    modal.setAttribute("id", "update-board-modal");

    const modalDialog = document.createElement("div");
    modalDialog.setAttribute("class", "modal-dialog");

    const modalContent = document.createElement("div");
    modalContent.setAttribute("class", "modal-content");

    const modalBody = this.modalBody();
    const modalHeader = this.modalHeader("Edit Board");
    const modalFooter = this.modalFooter();

    modalContent.append(modalHeader, modalBody, modalFooter);
    modalDialog.append(modalContent);
    modal.append(modalDialog);

    const mainContainer = document.querySelector("#container");
    mainContainer.append(modal);
  }

  modalHeader(title) {
    const modalHeader = document.createElement("div");
    modalHeader.setAttribute("class", "modal-header");

    const modalTitle = document.createElement("h5");
    modalTitle.setAttribute("class", "modal-title text-dark");
    modalTitle.textContent = title;

    const closeButton = document.createElement("button");
    closeButton.setAttribute("type", "button");
    closeButton.setAttribute("class", "btn-close");
    closeButton.setAttribute("data-bs-dismiss", "modal");
    closeButton.setAttribute("aria-label", "Close");

    modalHeader.append(modalTitle, closeButton);

    return modalHeader;
  }

  modalBody() {
    const modalBody = document.createElement("div");
    modalBody.setAttribute("class", "modal-body");
    modalBody.setAttribute("id", "modal-body");

    const form = document.createElement("input");
    form.setAttribute("type", "text");
    form.setAttribute("placeHolder", "Name");
    form.id = "boardNameToEdit";

    modalBody.append(form);

    return modalBody;
  }

  modalFooter() {
    const modalFooter = document.createElement("div");
    modalFooter.setAttribute("class", "modal-footer");

    const button = document.createElement("button");
    button.setAttribute("type", "button");
    button.setAttribute("class", "btn btn-info");
    button.id = "modal-footer-button";
    button.textContent = "Edit";
    button.addEventListener("click", ButtonsHandler.buttonHandler);

    modalFooter.append(button);
    return modalFooter;
  }

  static cleanInput() {
    document.querySelector("#boardNameToEdit").value = "";
  }

  static cleanAllInputs() {
    document.querySelector("#boardNameToEdit").value = "";
    document.querySelector("#description").value = "";
    document.querySelector("#deliveryDate").value = "";
  }

  static getInputValue() {
    const input = document.querySelector("#boardNameToEdit");
    return input.value;
  }

  static async isEmpty() {
    const data = Modal.getInputValue();

    if (data.length > 0) {
      return false;
    }

    alert("Please type something!");
    return true;
  }
}

export function toggleModal() {
  const body = document.querySelector("body");
  const modal = document.querySelector("#update-board-modal");
  const backDrop = document.querySelector(".modal-backdrop");
  const display = modal.display;
  modal.classList.toggle("show");

  backDrop.classList.toggle("show");

  if (body.classList.contains("modal-open")) {
    body.classList.remove("modal-open");
    body.lastChild.remove();
  }

  if (display === "none") {
    modal.style.display = "block";
    modal.removeAttribute("aria-hidden");
    modal.setAttribute("aria-modal", "true");
    modal.setAttribute("role", "dialog");
  }

  modal.removeAttribute("aria-moda");
  modal.removeAttribute("role");
  modal.setAttribute("aria-hidden", "true");
  modal.style.display = "none";
}

const modal = new Modal();
modal.render();
