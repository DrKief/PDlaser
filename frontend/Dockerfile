# syntax=docker/dockerfile:1

# Stage 1: Build with cache optimization
FROM node:24-alpine AS build
WORKDIR /app
COPY package*.json ./
# Cache NPM registry
RUN --mount=type=cache,target=/root/.npm npm ci

COPY . .
RUN npm run build

# Stage 2: Secure Nginx Serving
FROM nginx:alpine
COPY --from=build /app/dist /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Remove the default 'user' directive to silence the super-user warning
RUN sed -i 's/^user .*//' /etc/nginx/nginx.conf

# Create non-root user dynamically
RUN addgroup -S appgroup && adduser -S -G appgroup appuser \
  && chown -R appuser:appgroup /usr/share/nginx/html \
  && chown -R appuser:appgroup /var/cache/nginx \
  && chown -R appuser:appgroup /var/log/nginx \
  && chown -R appuser:appgroup /etc/nginx/conf.d \
  && touch /var/run/nginx.pid \
  && chown -R appuser:appgroup /var/run/nginx.pid

USER appuser

HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD wget -qO- http://localhost:8080/ || exit 1 

EXPOSE 8080
CMD ["nginx", "-g", "daemon off;"]
