# Stage 1: Build the Vue.js application
FROM node:20-alpine AS build
WORKDIR /app

# Copy package files and install dependencies
COPY package*.json ./
RUN npm ci

# Copy the rest of the frontend code and build
COPY . .
RUN npm run build

# Stage 2: Serve securely with Unprivileged Nginx
FROM nginxinc/nginx-unprivileged:alpine

# Copy the built Vue application from the build stage
COPY --from=build /app/dist /usr/share/nginx/html

# Overwrite the default Nginx configuration
COPY nginx.conf /etc/nginx/conf.d/default.conf

# 8080 is the default port for the unprivileged image
EXPOSE 8080

CMD ["nginx", "-g", "daemon off;"]