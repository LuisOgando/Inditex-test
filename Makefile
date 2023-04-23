startup:
	gradle clean
	gradle build
	docker build . -t logandotest
	docker run -p 8080:8080 logandotest

test:
	gradle test

.PHONY: startup test