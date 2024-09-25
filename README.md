
# Cognizant Spring Boot REST API task

To run the program, create a `program.properties`

```
spring.application.name=demo
spring.datasource.url=jdbc:mysql://{IP_ADDRESS}/{YOUR_DB}?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username={USER}
spring.datasource.password={PASSWORD}
spring.docker.compose.enabled=false

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# spring.datasource.driverClassName=org.h2.Driver
# spring.jpa.hibernate.ddl-auto=update
# spring.jpa.show-sql=true
```


## Endpoints

### `POST /api/ratings`

#### Description

Adds a new rating for a book. If a rating from the user already exists for the specified book, it updates the existing rating.

#### Example request body:
```json
{
    "rating": 4,
    "book": {
        "id": 1
    },
    "userId": "1"
}
```

## Overview
The `BookController` handles HTTP requests related to book retrieval. It allows users to fetch books based on various filtering criteria, including genre, release year, and author.
## Endpoints ###
`GET /api/books`
#### Description
Retrieves a list of books from the database. The response can be filtered based on genre, release year, or author. If no parameters are provided, all books are returned.
#### Request
| Parameter | Type | Required | Description |
|---------------|----------|----------|-------------------------------------------------|
| `genre` | String | No | The genre of the books to filter by. |
| `releaseYear` | Integer | No | The release year of the books to filter by. |
| `author` | String | No | The author of the books to filter by. |

#### Responses
| HTTP Code | Content-Type | Response |
|--------------|-------------------------------------|--------------------------------------------------------------------|
| `200 OK` | `application/json;charset=UTF-8` | A list of books, each with an average rating. |

#### Response Example
**200 OK**
```json
[
	{ "id": 1,
	"title": "Sample Book",
	"author": "Author Name",
	"genre": "Fiction",
	"releaseYear": 2020,
	"averageRating": 4.5 },
	{ "id": 2,
	"title": "Another Book",
	"author": "Another Author",
	"genre": "Non-Fiction",
	"releaseYear": 2018,
	"averageRating": 3.8 }
]
```
