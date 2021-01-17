# day-service

## Run artillery

`npm install -g artillery`

Update target in yaml file.

`artillery run artillery/day-service.yaml`

## Test request

`sls invoke --function calculateDay --data '{ "body": "{\"date\": \"22 May 2017\"}" }'``
