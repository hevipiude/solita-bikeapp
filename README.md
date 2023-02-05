# BikeApp

This project is a [pre-assignment for Solita's Dev Academy](https://github.com/solita/dev-academy-2023-exercise). This web application allows you to see data from journeys taken with Helsinki City Bikes in the summer of 2021. You can also scroll through all the stations and also view details of a single station.

This application is split between React frontend and Java Spring backend. React application can be found from `./frontend` and Java Spring server from `./backend`, and a Spring project from `./database` for initializing the database.

## Features:

### Data import:

Validated data is imported from CSV to a MySQL database.

#### Validation:

- Data is validated by correct formats for dates, integers, doubles and strings.
- Journeys that last under 10 seconds, and cover distances shorter than 10 meters were not imported.

### Journey list view:

- Paginated data of journeys is listed in a table component, in which you can choose the amount of rows shown on a page.

- For every journey, table component shows departure and return stations, covered distance in kilometers and duration in minutes.

- Paginated data can be ordered by a single column in ascending or descending order.

### Station list view:

- Paginated data of stations is listed in a table component, in which you can choose the amount of rows shown on a page.

- For every station, table component shows the stations name and address, the city and operator (if provided in the original CSV files, otherwise these table cells are left blank), and the capacity for the station.

- By clicking the "Näytä"-button, the app shows details of the station, such as stations name, id, address, a map with a marker pointing at the location of the station, and total numbers of journeys starting from and ending at on the selected station.

## Instructions for local running:

### For running the backend and database files you should have:

These versions have been tested, other versions of the following ones could work too.

- **Java JDK version: 17.0.6**
- **Apache Maven 3.8.7**
- **MySQL 8.0.25**

### Database

Initializing the database will take a while, so before proceeding to running the backend and the frontend make sure that the database has finished adding the data, the program will tell you when it's done on your command line.

Creating the database requires a running mySQL server with an username as `user` and a password as `password`, with all grants to access the database. If you don't want to create a new user and preferably do this step with your `root@localhost` user, you have to change your root username and password to the connection string in to the `database/src/main/java/com/solita/database/CityBikeDB.java` file in the database folder (line 12).

`public static String connString = "jdbc:mysql://localhost:3306?user=user&password=password";`

`ImportData.java` reads the .csv URLs which have been hard coded to the file. These URLs can also be found in [dev-academy-2023-exercise](https://github.com/solita/dev-academy-2023-exercise).

To initialize the citybike database:

1. `cd database`
2. `./mvnw spring-boot:run`.

### Backend

1. `cd backend`
2. `./mvnw spring-boot:run`

### Frontend

1. `cd frontend`
2. `npm install`
3. `npm run dev`

#### Frontend runs on `localhost:5173` by default

#### Backend runs on `localhost:8080` by default
