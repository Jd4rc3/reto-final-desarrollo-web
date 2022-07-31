export class Config {
  static BackendURL = "http://localhost:9091/api/v1";
}

export function getBoardIdFromURL() {
  const URL = new URLSearchParams(window.location.search);
  return parseInt(URL.get("id"));
}
