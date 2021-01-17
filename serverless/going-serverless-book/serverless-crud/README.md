# serverless-crub

## Test

curl -X POST -d '{ "text": "Hello World" }' https://dzgowuq3xa.execute-api.eu-west-2.amazonaws.com/dev/snippets

sls invoke -f getSnippet --data '{ "pathParameters": { "id": "8fec4bca-d605-4823-88ad-f3d71a2dbadc" }}'  

sls invoke -f getSnippets

## deploy client

`sls client deploy`