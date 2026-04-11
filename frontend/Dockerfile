FROM node:20-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY . .
RUN npm run build

# Your existing Nginx setup
FROM nginx:alpine

# Fix Nginx user warning
RUN sed -i 's/user  .*;//' /etc/nginx/nginx.conf

# This will now successfully pull the compiled files from the stage above
COPY --from=build /app/dist /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Your custom non-root user setup
RUN addgroup -S appgroup && adduser -S -G appgroup appuser \
  && chown -R appuser:appgroup /usr/share/nginx/html \
  && chown -R appuser:appgroup /var/cache/nginx \
  && chown -R appuser:appgroup /var/log/nginx \
  && chown -R appuser:appgroup /etc/nginx/conf.d \
  && touch /var/run/nginx.pid \
  && chown -R appuser:appgroup /var/run/nginx.pid

USER appuser

# Healthcheck pointing to 127.0.0.1 to avoid Alpine IPv6 issues
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD wget -qO- http://127.0.0.1:8080/ || exit 1

EXPOSE 8080
CMD ["nginx", "-g", "daemon off;"]
