.DEFAULT_GOAL := default

.PHONY: .default .repl .run .build

repl:
	lein repl

run:
	SERVER_HOST=localhost SERVER_PORT=8080 lein run

build:
	lein uberjar

default: run
