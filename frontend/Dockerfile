# Stage 1: Build with cache optimization
FROM node:alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm ci

COPY . .
RUN npm run build

# Stage 2: Secure Nginx Serving
FROM nginx:alpine
COPY --from=build /app/dist /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Remove the default 'user' directive to silence the super-user warning
RUN sed -i 's/^user .*//' /etc/nginx/nginx.conf

RUN chown -R nginx:nginx /usr/share/nginx/html \
  && chown -R nginx:nginx /var/cache/nginx \
  && chown -R nginx:nginx /var/log/nginx \
  && chown -R nginx:nginx /etc/nginx/conf.d \
  && touch /var/run/nginx.pid \
  && chown -R nginx:nginx /var/run/nginx.pid
USER nginx
EXPOSE 8080
CMD ["nginx", "-g", "daemon off;"]
