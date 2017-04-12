# Movie Library Interact.io

The project is a Micro Service for a movie Library with basics CRUD operation.
The Application exposes a RESTful API.

## Requirement

Install maven
Install Java JDK 1.8

## Run the project

```
mvn spring-boot:run
```

##Test the Micro Service

If you run the micro service on your localhost :

#Add a movie

`̀ `
POST http://localhost:8080/movies

Json body :
{
    "title": "movie1",
    "releaseYear": 1234
}
```

#Select All movies

`̀ `
GET http://localhost:8080/movies
```

#Select a specific movie

`̀ `
GET http://localhost:8080/movies/{id}
```

For a movie with the id : 02451bd3-9c03-4626-87dc-69cf5c267a79
`̀ `
GET http://localhost:8080/movies/02451bd3-9c03-4626-87dc-69cf5c267a79
```

#Select all the movies from a release year

`̀ `
GET http://localhost:8080/movies/releaseyear/{year}
```

For the year : 1980
`̀ `
GET http://localhost:8080/movies/releaseyear/1980
```

#Update a movie

`̀ `
 PUT http://localhost:8080/movies/{id}

 Json body :
 {
     "title": "newTitle",
     "releaseYear": 1235
 }
```

#Delete a movie

`̀ `
 DELETE http://localhost:8080/movies/{id}
```