#!/bin/bash

aws dynamodb create-table \
    --table-name customers \
    --attribute-definitions \
        AttributeName=id,AttributeType=S \
    --key-schema \
        AttributeName=id,KeyType=HASH \
    --provisioned-throughput \
        ReadCapacityUnits=5,WriteCapacityUnits=5 \
    --endpoint-url http://localhost:4566

aws dynamodb put-item \
    --table-name customers \
    --item '{"id": {"S": "123"}, "name": {"S": "Breno"}, "email": {"S": "breno@test.com"}}' \
    --endpoint-url http://localhost:4566

