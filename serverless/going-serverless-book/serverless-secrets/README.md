# serverless-secrets

## Setup keys

sls encrypt -n MY_SECRETS:TEST1 --value test1_value -k <keyArn>
sls encrypt -n MY_SECRETS:TEST2 --value test2_value -k <keyArn>

sls decrypt --n MY_SECRETS:TEST1

sls decrypt --n MY_SECRETS:TEST1

sls invoke -f helloWorld
