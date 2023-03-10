package src.main.java.com.solita.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CityBikeDB {

    // change the username and password on the next line if you want to use
    // root instead
    public static String connString = "jdbc:mysql://localhost:3306?user=user&password=password";

    public static Connection openConnection() throws SQLException {

        Connection c = DriverManager.getConnection(connString);
        System.out.println("\t>> Database connection opened");

        return c;
    }

    public static void closeConnection(Connection c) throws SQLException {
        c = DriverManager.getConnection(connString);
        Statement stmt = c.createStatement();
        stmt.execute("USE citybike;");

        if (c != null) {
            c.close();
        }
        System.out.println("\t>> Database connection closed");
    }

    public static void createDatabase(Connection c, String db) throws SQLException {
        c = DriverManager.getConnection(connString);
        Statement stmt = c.createStatement();

        stmt.execute("DROP DATABASE IF EXISTS " + db);

        System.out.println("\t>> Database " + db + " dropped");

        stmt.execute("CREATE DATABASE " + db);

        stmt.execute("USE " + db);

        System.out.println("\t>> Database " + db + " created");

        stmt.execute("USE " + db);

        System.out.println("\t>> Using database " + db);

        stmt.execute(
                "CREATE TABLE `journeys` (`id` int NOT NULL AUTO_INCREMENT,`departure_time` datetime NOT NULL,`return_time` datetime NOT NULL,`departure_station_id` int NOT NULL,`departure_station_name` varchar(45) NOT NULL,`return_station_id` int NOT NULL,`return_station_name` varchar(45) NOT NULL,`distance` int NOT NULL,`duration` int NOT NULL, PRIMARY KEY (`id`)) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;");

        System.out.println("\t>> Table to " + db + " created");
    }

    public static void createStationList(Connection c, String db) throws SQLException {
        c = DriverManager.getConnection(connString);
        Statement stmt = c.createStatement();
        stmt.execute("USE " + db);

        System.out.println("\t>> Using database " + db);

        stmt.execute(
                "CREATE TABLE `stations` (`fid` int NOT NULL, `station_id` int NOT NULL, `name_fin` varchar(45) DEFAULT NULL, `name_swe` varchar(45) DEFAULT NULL, `name_eng` varchar(45) DEFAULT NULL, `address_fin` varchar(45) DEFAULT NULL, `address_swe` varchar(45) DEFAULT NULL, `city_fin` varchar(45) DEFAULT NULL, `city_swe` varchar(45) DEFAULT NULL, `operator` varchar(45) DEFAULT NULL, `capacity` int DEFAULT NULL, `x` double NOT NULL, `y` double NOT NULL, PRIMARY KEY (`fid`)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;");

        System.out.println("\t>> Table to " + db + " created");
    }
}