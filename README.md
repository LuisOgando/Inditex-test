# Spring Boot Inditex Test Project

This is a test  Java / Gradle / Spring Boot (version 3) application.

The project is a product query REST Service. It uses an in-memory database (H2) to store the data.

Notes: All basic security is disabled.

The test requirements are in the file : `Prueba_Tecnica_Ecommerce_Inditex_002.pdf`

The test answers are in the file : `Prueba_Tecnica_Ecommerce_Respuestas_LO.txt`

## How to Run

### Clone the project

    git clone https://github.com/LuisOgando/inditex-test.git
    cd inditex-test

There are 3 different ways to run this project:

1- Using a docker container. You must have `docker` installed locally,
just run the  `startup` command form the Makefile:

	make statup

2- Using gradle command:

	gradle clean build
	gradle bootRun

3- You can also use the native java command after packaging the project with `gradle assemble`:

```
java -jar build/libs/logandotest.jar
```

## Run Tests

    make test
    or
    gradle test

##  REST API

# Get Products
### Request

    curl -i -H 'Accept: application/json' 'http://localhost:8080/product'

### Response

    HTTP/1.1 200
    Content-Type: application/json
    Transfer-Encoding: chunked
    Date: Sat, 11 Mar 2023 18:56:32 GMT

    [
        {
            "product_id": 5,
            "sequence": 6
        },
        {
            "product_id": 1,
            "sequence": 10
        },
        {
            "product_id": 3,
            "sequence": 15
        }
    ]


After the service is running you can visit the link to see the documentation:

`http://localhost:8080/swagger-ui/index.html#/`

![alt text](/api-doc.png)


    

