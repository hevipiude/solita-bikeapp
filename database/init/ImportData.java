package init;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import static java.lang.Integer.parseInt;

public class ImportData {

    // connection stuff
    static String jdbcUrl = "jdbc:mysql://localhost:3306/";
    static String username = "user";
    static String password = "password";

    static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
    static int notLine = 0;
    static int i = 0;
    static int j = 0;

    public static void main(String[] args) throws SQLException {

        String dbName = "citybike";
        Connection c = CityBikeDB.openConnection();
        CityBikeDB.createDatabase(c, dbName);
        CityBikeDB.createStationList(c, dbName);
        CityBikeDB.createS_StationList(c, dbName);


        String csv1 = "database/seed/2021-05.csv";
        String csv2 = "database/seed/2021-06.csv";
        String csv3 = "database/seed/2021-07.csv";
        String csv4 = "database/seed/stations.csv";

        insertJourneyData(1, csv1, dbName);
        insertJourneyData(1, csv2, dbName);
        insertJourneyData(1, csv3, dbName);
        insertStationData(2, csv4, dbName);
        insertS_StationData(dbName);

        CityBikeDB.closeConnection(c);
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

        try {
            Connection c = CityBikeDB.openConnection();
            c.setAutoCommit(false);

            String sql = "INSERT INTO `" + db
                    + "`.`journeys`(`departure_time`,`return_time`,`departure_station_id`,`departure_station_name`,`return_station_id`,`return_station_name`,`distance`,`duration`) values (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = c.prepareStatement(sql);

            BufferedReader lineReader = new BufferedReader(new FileReader(path));

            String lineText = "";
            int count = 0;

            lineReader.readLine();
            while ((lineText = lineReader.readLine()) != null) {

                String[] data = lineText.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

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
                                i++;

                                if (count % batchSize == 0) {
                                    statement.executeBatch();
                                }
                                

                            }
                        }
                    }
                }
            }
            lineReader.close();
            statement.executeBatch();
            c.commit();
            c.close();
            System.out.println("file " + num + " had " + i + " valid lines");
            System.out.println("file " + num + " had " + notLine + " invalid lines");
            System.out.println("Data for journeys has been inserted successfully.");

        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println(i);
        }
    }

    public static void insertStationData(int num, String path, String db) {

        int batchSize = 100;
        notLine = 0;

        try {
            Connection c = CityBikeDB.openConnection();
            c.setAutoCommit(false);

            String sql = "INSERT INTO `citybike`.`stations`(`fid`,`station_id`,`name_fin`,`name_swe`,`name_eng`,`address_fin`,`address_swe`,`city_fin`,`city_swe`,`operator`,`capacity`,`x`,`y`) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = c.prepareStatement(sql);

            BufferedReader lineReader = new BufferedReader(new FileReader(path));

            String lineText = "";
            int count = 0;

            lineReader.readLine();
            while ((lineText = lineReader.readLine()) != null) {

                String[] data = lineText.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

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
                        j++;

                        if (count % batchSize == 0) {
                            statement.executeBatch();
                        }

                    }

                }

            }

            lineReader.close();
            statement.executeBatch();
            c.commit();
            c.close();
            System.out.println("file " + num + " had " + j + " valid lines");
            System.out.println("file " + num + " had " + notLine + " invalid lines");
            System.out.println("Data for stations has been inserted successfully.");

        } catch (

        Exception exception) {
            exception.printStackTrace();
            System.out.println(i);
        }
    }

    public static void insertS_StationData(String db) {
        try {
            Connection c = CityBikeDB.openConnection();
            c.setAutoCommit(false);
            String use = "USE citybike;";

            String sql = "INSERT INTO `citybike`.`s_station`(`id`,`name_fin`,`address_fin`,`departure_count`,`return_count`) with dep as (select journeys.departure_station_id,count(departure_station_id) departure_count from journeys group by departure_station_id), ret as (select journeys.return_station_id,count(return_station_id) return_count from journeys group by return_station_id) select distinct station_id, name_fin, address_fin, departure_count, return_count from stations left join dep on stations.station_id=dep.departure_station_id left join ret on stations.station_id=ret.return_station_id;";

            PreparedStatement useStmt = c.prepareStatement(use);
            PreparedStatement createStmt = c.prepareStatement(sql);

            useStmt.execute();
            createStmt.execute();
            c.commit();
            c.close();
            System.out.println("Data for single stations has been inserted successfully.");

        } catch (

        Exception exception) {
            exception.printStackTrace();
            System.out.println(i);
        }
    }
}
