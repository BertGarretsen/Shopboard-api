# ShopBoard API

A cool little backend service for storing minecraft SMP shop data in a postgresql database. And serving the data over
http.

## Getting Started

### Running in Development

To run the application in development, make sure you have Docker installed and **running**  
If you are not using an IDE also make sure you have Gradle installed, then simply run:

```bash
./gradlew bootRun
```

Spring will automatically handle the rest.

### Running in Production

Clone the project from GitHub and compile the application using Gradle.  
Place the compiled JAR in the project root called `app.jar`.  
Then you can run the application using Docker Compose:

```bash
sudo docker compose --profile production up -d --build
```

---

## Configuration

The application requires the following environment variables to be set for database connectivity and data sourcing:

### Database Settings (PostgreSQL)

| Variable      | Description                                                   |
|:--------------|:--------------------------------------------------------------|
| `DB_USERNAME` | The username for your PostgreSQL instance.                    |
| `DB_PASSWORD` | The password for your PostgreSQL instance.                    |
| `DB_URL`      | The JDBC connection URL (e.g., `jdbc:postgresql://db:5432/`). |
| `DB_NAME`     | The name of the specific database to use.                     |

---

## Endpoints:

| Endpoint                            | Description                                    |
|-------------------------------------|------------------------------------------------|
| /api/v1/orders/all/{serverHash}     | Retrieve all shops & their available orders    |
| /api/v1/servers/dynmap/{serverHash} | Retrieve the format of dynmap this server uses |

