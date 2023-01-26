FROM bitnami/minideb:latest AS stagging
RUN apt-get update && apt-get upgrade -y
RUN apt-get install -y \
    git
RUN git clone --depth 1 https://git.ars-virtualis.org/yul/ce-cfg-external


FROM postgres:14.3-alpine
COPY --from=stagging /ce-cfg-external/db/01-init.sh /docker-entrypoint-initdb.d/
# COPY 01-init.sh /docker-entrypoint-initdb.d/01-init.sh
RUN chmod -R +x ./docker-entrypoint-initdb.d
COPY ./seed /seed
# COPY --from=stagging /ce-cfg-external/db/seed /seed
