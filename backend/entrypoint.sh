#!/bin/bash
set -e

echo "Fixing permissions for /var/lib/pdl..."
chown -R appuser:appgroup /var/lib/pdl

echo "Starting application as appuser..."
exec gosu appuser "$@"
