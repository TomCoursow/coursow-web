#!/bin/sh
if [ -f "$POSTGRES_PASSWORD_FILE" ]; then
  POSTGRES_PASSWORD=$(cat "$POSTGRES_PASSWORD_FILE")
  export POSTGRES_PASSWORD
fi
exec java -jar app.jar "$@"
