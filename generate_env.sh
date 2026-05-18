#!/bin/bash

# Database and Admin passwords (alphanumeric only to prevent parsing errors)
PG_PASS=$(openssl rand -base64 33 | tr -dc 'a-zA-Z0-9' | head -c 32)
ADMIN_PASS=$(openssl rand -base64 33 | tr -dc 'a-zA-Z0-9' | head -c 32)

# RustFS S3 Keys
S3_ACCESS=$(openssl rand -hex 16)
S3_SECRET=$(openssl rand -hex 32)

# Spring Boot JWT Secret (Hex encoding guarantees no special character issues, 32 bytes / 256 bits)
JWT=$(openssl rand -hex 32)

echo "POSTGRES_PASSWORD=${PG_PASS}"
echo "DATABASE_PASSWORD=${PG_PASS}"
echo "ADMIN_USERNAME=admin"
echo "ADMIN_PASSWORD=${ADMIN_PASS}"
echo "S3_ACCESS_KEY=${S3_ACCESS}"
echo "S3_SECRET_KEY=${S3_SECRET}"
echo "JWT_SECRET=${JWT}"
