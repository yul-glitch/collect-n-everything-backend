#!/bin/sh

init_db() {
  [ -z "$1" ] && return 1
  echo "Initializing $1"
  psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_USER" -c "CREATE DATABASE $1 template=template0 OWNER $POSTGRES_USER;"
  psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_USER" -c "GRANT ALL PRIVILEGES ON DATABASE $1 TO $POSTGRES_USER;"
  [ ! -f /seed/"$1".sql ] && return 1
  echo "Seeding $1"
  psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$1" -f /seed/"$1".sql
}

# init_db authentication 
init_db store 
# init_db customisation 
# init_db payment
# init_db subscription
# init_db ticket
init_db customer
init_db notification
init_db orders
init_db product
