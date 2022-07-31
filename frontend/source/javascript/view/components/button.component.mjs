/**
 * Button class represents a button and its properties.
 */
export class Button {
  constructor(text, color, eventListenner = null, attributes = []) {
    this.text = text;
    this.color = color;
    this.eventListenner = eventListenner;
    this.attributes = attributes;
  }

  /**
   * @method drawButton - Creates a button with the given text and attributes
   * @returns {HTMLButtonElement} - The button
   */
  buildButton() {
    const button = document.createElement("button");
    button.classList.add("btn", "btn-" + this.color);
    button.innerHTML = this.text;
    button.setAttribute("type", "button");

    if (this.eventListenner) {
      button.addEventListener("click", this.eventListenner);
    }

    if (this.attributes.length > 0) {
      this.attributes.forEach((attribute) => {
        button.setAttribute(attribute.name, attribute.value);
      });
    }

    return button;
  }
}
