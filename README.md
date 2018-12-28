Pre Requisites:
---------------
1. Set up postgres database 
```
docker run -d -p 5432:5432 --name portgres -e POSTGRES_PASSWORD=admin postgres 
```


API Reference:
--------------
Run the application and navigate to http://localhost:8080/swagger-ui.html#


Sample Usage:
------------

```
curl -X POST -H 'Content-type: application/json' http://localhost:8080/register/user -d "{ \"userId\":\"019\", \"userName\":\"Karthick\" }"
curl -X GET http://localhost:8080/greet/user?userid=019
```

