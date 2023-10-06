#!/bin/zsh

docker-compose down --remove-orphans
docker-compose pull
docker-compose up --detach
