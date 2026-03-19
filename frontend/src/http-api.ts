import axios from "axios";

const http = axios.create({
  baseURL: "/", // Proxy handled by Vite
  headers: {
    "Content-type": "application/json",
  },
});

export default http;
