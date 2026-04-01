import axios from "axios";

const http = axios.create({
  baseURL: "/", // Proxy handled by Vite
  headers: {
    "Content-type": "application/json",
  },
});

// Automatically attach the JWT token to every outgoing request
http.interceptors.request.use((config) => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export default http;