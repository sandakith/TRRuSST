package org.trusst.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Properties;



import org.trusst.market.MarketItem;
import org.trusst.market.item.AppItem;

public class DBUtils {

    private static Connection conn = null;
    private static Statement stmt = null;
    private static String dbLocation = "droidReviewDB";
    private static String dbURL = "jdbc:derby://localhost:1527/";
    private static String driverName = "org.apache.derby.jdbc.ClientDriver";

    private static Connection getConnInstance() {
        if (conn == null) {
            conn = createConnection("admin","admin");
        }
        return conn;
    }

    public static boolean populateMarketItemToDB(MarketItem marketItem) {

        boolean result = false;
        conn = getConnInstance();
        if (conn!= null){
            result =  incertMarketItem(conn, marketItem);
            result =  incertMarketItemReviews(conn, marketItem);
        }else {
            System.out.print("Could not connect to the database");
            System.out.println("Check that the Derby Network Server is running on localhost.");
        }
        return result;
    }

    private static boolean incertMarketItem(Connection conn, MarketItem item ){
        // Create Star ratings array

        String sql = "insert into APP.MarketItem " +
                "(id, " +
                "recordDate, " +
                "ItemId, " +
                "ItemName, " +
                "ItemDeveloper, " +
                "ItemDeveloperRating, " +
                "ItemAvgRating, " +
                "RatingCount, " +
                "LastUpdate, " +
                "NumOfInstalls, " +
                "ItemPrice, " +
                "ItemSize, " +
                "CurrentVersion, " +
                "ContentRating, " +
                "fiveStars, " +
                "fourStars, " +
                "threeStarts, " +
                "twoStars, " +
                "oneStar)" +
                "values " +
                "("+getNextID("APP.MarketItem")+"," +
                "'" + (getCurrentDateTime()) + "', " +
                "'" + (item.getItemId()).replaceAll("[^a-zA-Z0-9. ]+","") + "', " +
                "'" + (item.getItemName()).replaceAll("[^a-zA-Z0-9 ]+","") + "', " +
                "'" + (item.getItemDeveloper()).replaceAll("[^a-zA-Z0-9]+","") + "', " +
                "'" + (item.getItemDeveloperRating()).replaceAll("[^a-zA-Z0-9]+","") + "', " +
                "'" + (item.getItemAvgRating()).replaceAll("[^a-zA-Z0-9]+","") + "', " +
                "'" + (item.getRatingCount()).replaceAll("[^a-zA-Z0-9]+","") + "', " +
                "'" + (item.getLastUpdate()).replaceAll("[^a-zA-Z0-9 ]+","") + "', " +
                "'" + (item.getNumOfDownloads()).replaceAll("[^a-zA-Z0-9]+","") + "', " +
                "'" + (item.getItemPrice()).replaceAll("[^a-zA-Z0-9]+","") + "', " +
                "'" + (item.getItemSize()).replaceAll("[^a-zA-Z0-9]+","") + "', " +
                "'" + (item.getCurrentVersion()).replaceAll("[^a-zA-Z0-9]+","") + "', " +
                "'" + (item.getContentRating()).replaceAll("[^a-zA-Z0-9]+","") + "', " +
                "'" + (item.getFiveStarRatings()).replaceAll("[^a-zA-Z0-9]+","") + "', " +
                "'" + (item.getFourStarRatings()).replaceAll("[^a-zA-Z0-9]+","") + "', " +
                "'" + (item.getThreeStarRatings()).replaceAll("[^a-zA-Z0-9]+","") + "', " +
                "'" + (item.getTwoStarRatings()).replaceAll("[^a-zA-Z0-9]+","") + "', " +
                "'" + (item.getOneStarRatings()).replaceAll("[^a-zA-Z0-9]+","") + "')";
        int numRows = executeUpdate(conn,sql);
        return ((numRows == 1) ? true : false );
    }

    private static boolean incertMarketItemReviews(Connection conn, MarketItem item ){

        boolean result = false;
        // Create Star ratings array
        String splittableString = item.getItemUserReviews();
        String[] reviewList = splittableString.split(" :::: ");

        for (int i = 0; i < reviewList.length; i++) {

            String[] review = reviewList[i].split(" :: ");

            if (review.length <= 1){
                review = new String[]{"none","none","none","none","none","none"};
            }

            String sql = "insert into APP.MarketItemReviews " +
                    "(id, " +
                    "recordDate, " +
                    "reviewItemId, " +
                    "reviewUserId, " +
                    "reviewUser, " +
                    "reviewDate, " +
                    "reviewStarValue, " +
                    "reviewHeading, " +
                    "reviewBody)" +
                    "values " +
                    "("+getNextID("APP.MarketItemReviews")+"," +
                    "'" + (getCurrentDateTime()) + "', " +
                    +(getNextID("APP.MarketItem")-1)+"," +
                    "'" + (review[0]).replaceAll("[^a-zA-Z0-9 ]+","") + "', " +
                    "'" + (review[1]).replaceAll("[^a-zA-Z0-9 ]+","") + "', " +
                    "'" + (review[2]).replaceAll("[^a-zA-Z0-9 ]+","") + "', " +
                    "'" + (review[3]).replaceAll("[^a-zA-Z0-9 ]+","") + "', " +
                    "'" + (review[4]).replaceAll("[^a-zA-Z0-9 ]+","") + "', " +
                    "'" + (review[5]).replaceAll("[^a-zA-Z0-9 ]++","") + "')";
            int numRows = executeUpdate(conn,sql);
            result = (numRows == 1) ? true : false ;
        }

        return result ;
    }

    private static Connection createConnection(String userName, String password) {
        try {
            Class.forName(driverName);
            Properties dbProps = new Properties();
            dbProps.put("user", userName);
            dbProps.put("password", password);
            conn = DriverManager.getConnection(dbURL + dbLocation, dbProps);
        } catch (Exception except){
            System.out.print("Could not connect to the database with username: " + userName);
            System.out.println(" password " + password);
            System.out.println("Check that the Derby Network Server is running on localhost.");
            except.printStackTrace();
        }
        return conn;
    }

    private static int executeUpdate(Connection conn, String sql) {
        // the number of rows affected by the update or insert
        int numRows = 0;

        try {
            stmt = conn.createStatement();
            numRows = stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
        return numRows;
    }

    private static String[] runQuery(Connection conn, String sql) {
        List<String> list = Collections.synchronizedList(new ArrayList<String>(10));
        try  {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();

            while(results.next()) {
                StringBuffer sbuf = new StringBuffer(200);
                for (int i = 1; i <= numberCols; i++){
                    sbuf.append(results.getString(i));
                    sbuf.append(", ");
                }
                list.add(sbuf.toString());
            }
            results.close();
            stmt.close();
        }
        catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
        return list.toArray(new String[list.size()]);
    }

    private static boolean dropAllReceordsFromTable(Connection conn, String table) {
        String sql = "delete from " + table ;
        int numRows = executeUpdate(conn,sql);
        if (numRows >= 0){
            return true;
        }
        return false;
    }

    private static String getCurrentDateTime(){
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter=  new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
        String dateNow = formatter.format(currentDate.getTime());
        return dateNow;
    }

    private static int getNextID(String table){
        Connection conn = getConnInstance();
        StringBuffer sqlStringBuffer = new StringBuffer();
        sqlStringBuffer.append("select max(id) from ");
        sqlStringBuffer.append(table);
        String[] runQueryResult = runQuery(conn, sqlStringBuffer.toString());
        if (runQueryResult.length == 0){
            return 0;
        } else if (runQueryResult[0].equals("null, ")) {
            return 1;
        }else{
            Integer result = Integer.parseInt(runQueryResult[0].split(",")[0]);
            return result.intValue()+1;
        }
    }

}
