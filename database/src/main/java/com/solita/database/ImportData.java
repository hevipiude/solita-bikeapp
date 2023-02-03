package src.main.java.com.solita.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import static java.lang.Integer.parseInt;

/**
 *
 * @author veera
 */
public class ImportData {

    static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
    static int notLine = 0;
    static int validLine = 0;
    static int rowCount = 0;
    static int totalCount = 0;

    static String journey1 = "https://dev.hsl.fi/citybikes/od-trips-2021/2021-05.csv";
    static String journey2 = "https://dev.hsl.fi/citybikes/od-trips-2021/2021-06.csv";
    static String journey3 = "https://dev.hsl.fi/citybikes/od-trips-2021/2021-07.csv";
    static String url4 = "https://opendata.arcgis.com/datasets/726277c507ef4914b0aec3cbcfcbfafc_0.csv";

    public static void main(String[] args) throws SQLException, IOException {

        String dbName = "citybike";
        Connection c = CityBikeDB.openConnection();
        CityBikeDB.createDatabase(c, dbName);
        CityBikeDB.createStationList(c, dbName);
        CityBikeDB.createS_StationList(c, dbName);

        insertJourneyData(1, journey1, dbName);
        insertJourneyData(2, journey2, dbName);
        insertJourneyData(3, journey3, dbName);
        insertStationData(4, url4, dbName);
        insertS_StationData(dbName);

        System.out.println("\t>> " + totalCount + " rows of valid data added to " + dbName);
        CityBikeDB.closeConnection(c);
        System.out.println("\t>> --------------------------------------------------------");
        System.out.println("\t>> Database initialization finished!");
        System.out.println("\t>> --------------------------------------------------------");
    }

    public static boolean isValidDate(String dateStr) {
        try {
            LocalDateTime.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            notLine++;
            return false;
        }
        return true;
    }

    public static boolean isValidInt(String intStr) {
        boolean isValid = false;
        try {
            int integer = parseInt(intStr);
            if (integer <= 0) {
                isValid = false;
                notLine++;
            } else {
                isValid = true;
            }

        } catch (NumberFormatException e) {
            notLine++;
        }
        return isValid;
    }

    public static boolean isValidDouble(String intStr) {
        boolean isValid = false;
        try {
            double dble = Double.parseDouble(intStr);
            if (dble <= 0) {
                isValid = false;
                notLine++;
            } else {
                isValid = true;
            }

        } catch (NumberFormatException e) {
            notLine++;
        }
        return isValid;
    }

    public static void insertJourneyData(int num, String path, String db) {

        int batchSize = 100;
        notLine = 0;
        validLine = 0;

        try {
            Connection c = CityBikeDB.openConnection();
            System.out.println(
                    "\t>> Starting to insert journey data from .csv file: " + num + "/4 (this might take a while)...");
            c.setAutoCommit(false);

            String sql = "INSERT INTO `" + db
                    + "`.`journeys`(`departure_time`,`return_time`,`departure_station_id`,`departure_station_name`,`return_station_id`,`return_station_name`,`distance`,`duration`) values (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = c.prepareStatement(sql);

            int count = 0;
            URL oracle = new URL(path);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(oracle.openStream()));

            String inputLine;

            in.readLine();
            while ((inputLine = in.readLine()) != null) {

                String[] data = inputLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                String departure_time = data[0];
                String return_time = data[1];
                String departure_station_id = data[2];
                String departure_station_name = data[3];
                String return_station_id = data[4];
                String return_station_name = data[5];
                String distance = data[6];
                String duration = data[7];

                String correctName1 = departure_station_name.replaceAll("\"", "");
                String correctName2 = return_station_name.replaceAll("\"", "");

                if (isValidInt(departure_station_id) && isValidInt(return_station_id) && isValidInt(distance)
                        && isValidInt(duration)) {

                    int D_s_id = parseInt(departure_station_id);
                    int R_s_id = parseInt(return_station_id);
                    int dist = parseInt(distance);
                    int dur = parseInt(duration);

                    if (isValidDate(departure_time) && isValidDate(return_time)) {

                        LocalDateTime dep = LocalDateTime.parse(departure_time, formatter);
                        LocalDateTime ret = LocalDateTime.parse(return_time, formatter);

                        // comparator for departure time and return time
                        int comparison = dep.compareTo(ret);

                        if (comparison < 0) {

                            if (dur >= 10 && dist >= 10) {
                                statement.setTimestamp(1, Timestamp.valueOf(dep));
                                statement.setTimestamp(2, Timestamp.valueOf(ret));
                                statement.setInt(3, D_s_id);
                                statement.setString(4, correctName1);
                                statement.setInt(5, R_s_id);
                                statement.setString(6, correctName2);
                                statement.setInt(7, dist);
                                statement.setInt(8, dur);

                                statement.addBatch();
                                validLine++;
                                rowCount++;

                                if (count % batchSize == 0) {
                                    statement.executeBatch();
                                    totalCount++;
                                }

                                if (rowCount == 100000) {
                                    System.out
                                            .println(
                                                    "\t\t>> File " + num + "/4: " + rowCount + " new valid rows added");
                                    rowCount = 0;
                                }

                            }
                        }
                    }
                }
            }
            in.close();
            statement.executeBatch();
            c.commit();
            c.close();
            System.out.println("\t>> --------------------------------------------------------");
            System.out.println("\t>> File " + num + " had " + validLine + " valid lines");
            System.out.println("\t>> File " + num + " had " + notLine + " invalid lines");
            System.out.println("\t>> Data for file " + num + "/4 has been inserted successfully.");
            System.out.println("\t>> --------------------------------------------------------");

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void insertStationData(int num, String path, String db) {

        int batchSize = 100;
        notLine = 0;
        validLine = 0;

        try {
            Connection c = CityBikeDB.openConnection();
            System.out.println(
                    "\t>> Starting to insert station data from .csv file: " + num + "/4 (this might take a while)...");
            c.setAutoCommit(false);

            String sql = "INSERT INTO `citybike`.`stations`(`fid`,`station_id`,`name_fin`,`name_swe`,`name_eng`,`address_fin`,`address_swe`,`city_fin`,`city_swe`,`operator`,`capacity`,`x`,`y`) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = c.prepareStatement(sql);

            URL oracle = new URL(path);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(oracle.openStream()));

            String inputLine;

            int count = 0;

            in.readLine();
            while ((inputLine = in.readLine()) != null) {

                String[] data = inputLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                String fid = data[0];
                String station_id = data[1];
                String name_fin = data[2];
                String name_swe = data[3];
                String name_eng = data[4];
                String address_fin = data[5];
                String address_swe = data[6];
                String city_fin = data[7];
                String city_swe = data[8];
                String operator = data[9];
                String capacity = data[10];
                String x = data[11];
                String y = data[12];

                String n_name_fin = name_fin.replaceAll("\"", "");
                String n_name_swe = name_swe.replaceAll("\"", "");
                String n_name_eng = name_eng.replaceAll("\"", "");
                String n_address_fin = address_fin.replaceAll("\"", "");
                String n_address_swe = address_swe.replaceAll("\"", "");
                String n_city_fin = city_fin.replaceAll("\"", "");
                String n_city_swe = city_swe.replaceAll("\"", "");
                String n_operator = operator.replaceAll("\"", "");

                if (isValidInt(fid) && isValidInt(station_id) && isValidInt(capacity)) {

                    int n_fid = parseInt(fid);
                    int n_id = parseInt(station_id);
                    int n_capacity = parseInt(capacity);

                    if (isValidDouble(x) && isValidDouble(y)) {
                        double n_x = Double.parseDouble(x);
                        double n_y = Double.parseDouble(y);

                        statement.setInt(1, n_fid);
                        statement.setInt(2, n_id);
                        statement.setString(3, n_name_fin);
                        statement.setString(4, n_name_swe);
                        statement.setString(5, n_name_eng);
                        statement.setString(6, n_address_fin);
                        statement.setString(7, n_address_swe);
                        statement.setString(8, n_city_fin);
                        statement.setString(9, n_city_swe);
                        statement.setString(10, n_operator);
                        statement.setInt(11, n_capacity);
                        statement.setDouble(12, n_x);
                        statement.setDouble(13, n_y);

                        statement.addBatch();
                        validLine++;

                        if (count % batchSize == 0) {
                            statement.executeBatch();
                            totalCount++;
                        }

                        if (rowCount == 100000) {
                            System.out.println("\t\t>> File " + num + "/4: " + rowCount + " new valid rows added");
                            rowCount = 0;
                        }

                    }

                }

            }

            in.close();
            statement.executeBatch();
            c.commit();
            c.close();

            System.out.println("\t>> --------------------------------------------------------");
            System.out.println("\t>> File " + num + " had " + validLine + " valid lines");
            System.out.println("\t>> File " + num + " had " + notLine + " invalid lines");
            System.out.println("\t>> Data for file " + num + "/4 has been inserted successfully.");
            System.out.println("\t>> --------------------------------------------------------");

        } catch (

        Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void insertS_StationData(String db) {

        try {
            Connection c = CityBikeDB.openConnection();
            System.out.println("\t>> Starting to add single station data...");
            c.setAutoCommit(false);
            String use = "USE citybike;";

            String sql = "INSERT INTO `citybike`.`s_station`(`id`,`name_fin`,`address_fin`,`departure_count`,`return_count`) with dep as (select journeys.departure_station_id,count(departure_station_id) departure_count from journeys group by departure_station_id), ret as (select journeys.return_station_id,count(return_station_id) return_count from journeys group by return_station_id) select distinct station_id, name_fin, address_fin, departure_count, return_count from stations left join dep on stations.station_id=dep.departure_station_id left join ret on stations.station_id=ret.return_station_id;";

            PreparedStatement useStmt = c.prepareStatement(use);
            PreparedStatement createStmt = c.prepareStatement(sql);

            useStmt.execute();
            createStmt.execute();
            c.commit();
            c.close();
            System.out.println("\t>> Data for single stations has been inserted successfully.");

        } catch (

        Exception exception) {
            exception.printStackTrace();
        }
    }
}
