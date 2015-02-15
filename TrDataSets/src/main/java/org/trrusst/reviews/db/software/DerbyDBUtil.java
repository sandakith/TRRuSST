package org.trrusst.reviews.db.software;

import org.trrusst.reviews.db.DBConstants;
import org.trrusst.reviews.db.DerbyDBConnection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * DB Utility class for DerbyDB based operations
 * Created by lahiru.gallege
 */
public class DerbyDBUtil {

    private static List<AzSoftwareDAO> azSoftwareDAOs = new ArrayList<AzSoftwareDAO>();
    private static Statement stmt = null;
    private static Connection conn = null;


    /**
     * Utility file to load AzDataSet
     * @param fileLocation location of the data file
     * @return status
     */
    public static boolean loadAzDataSet(String fileLocation){
        boolean status = false;
        // Load the data from the dataset
        File textFile = new File(fileLocation);
        try {
            String currentLine = null; int counter = 0; int dataSize = 0;
            StringBuffer dataBuffer = new StringBuffer();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile));
            while ((currentLine = bufferedReader.readLine()) != null){
                //System.out.println(currentLine);
                if (currentLine.trim().length() == 0){
                    counter = 0;
                    loadData(dataBuffer.toString());
                    dataSize = dataSize + 1;
                    if ((dataSize % 1000) == 0 ) {
                        System.out.println("Current Review Number : " + dataSize);
                        // Populate the Darby DB
                        populateDerbyDB(DerbyDBConnection.getInstance(), azSoftwareDAOs);
                        azSoftwareDAOs.clear(); // Clear the date structure
                        System.gc(); // Release the created set of objects
                    }
                } else {
                    counter = counter + 1;
                    dataBuffer.append(prepareData(currentLine));
                    dataBuffer.append("__AA__");
                }
                status = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return status;
     }

    private static String prepareData(String currentLine){
        String[] resultArray = currentLine.split("/");
        return resultArray[1];
    }

    private static void loadData(String currentSet){
        // Local variable to store a review
        AzSoftwareDAO azSoftwareDAO = new AzSoftwareDAO();

        // Generate the data points
        String[] dataPoints = currentSet.split("__AA__");
        for (int i =0 ; i < dataPoints.length ; i++ ){
            String[] data = dataPoints[i].split(":");
            if (data[0].equals("productId")){
                azSoftwareDAO.setProductId(data[1]);
            }else if (data[0].equals("title")){
                azSoftwareDAO.setTitle(data[1]);
            }else if (data[0].equals("price")){
                azSoftwareDAO.setPrice(data[1]);
            }else if (data[0].equals("userId")){
                azSoftwareDAO.setUserId(data[1]);
            }else if (data[0].equals("profileName")){
                azSoftwareDAO.setProfileName(data[1]);
            }else if (data[0].equals("helpfulness")){
                azSoftwareDAO.setHelpfulness(data[1]);
            }else if (data[0].equals("score")){
                azSoftwareDAO.setScore(data[1]);
            }else if (data[0].equals("time")){
                azSoftwareDAO.setTime(data[1]);
            }else if (data[0].equals("summary")){
                azSoftwareDAO.setSummary(data[1]);
            }else if (data[0].equals("text")){
                azSoftwareDAO.setText(data[1]);
            } else{
                // Should not reached here
            }
        }
        // populate the list of reviews
        azSoftwareDAOs.add(azSoftwareDAO);
    }

    private static boolean populateDerbyDB(Connection conn, List azSoftwareDAOs){
        conn = createConnection();
        boolean result = false;
        for (int i = 0; i < azSoftwareDAOs.size(); i++) {
            AzSoftwareDAO dao = (AzSoftwareDAO) azSoftwareDAOs.get(i);
            if (dao != null) {

                String sql = "insert into APP.AzSoftware " +
                        "(id, " +
                        "productId, " +
                        "title, " +
                        "price, " +
                        "userId, " +
                        "profileName, " +
                        "helpfulness, " +
                        "score, " +
                        "time, " +
                        "summary, " +
                        "text)" +
                        "values " +
                        "(" + getNextID("APP.AzSoftware") + "," +
                        "'" + (dao.getProductId()).replaceAll("[^a-zA-Z0-9 ]+", "") + "', " +
                        "'" + (dao.getTitle()).replaceAll("[^a-zA-Z0-9 ]+", "") + "', " +
                        "'" + (dao.getPrice()).replaceAll("[^a-zA-Z0-9 ]+", "") + "', " +
                        "'" + (dao.getUserId()).replaceAll("[^a-zA-Z0-9 ]+", "") + "', " +
                        "'" + (dao.getProfileName()).replaceAll("[^a-zA-Z0-9 ]+", "") + "', " +
                        "'" + (dao.getHelpfulness()).replaceAll("[^a-zA-Z0-9 ]+", "") + "', " +
                        "'" + (dao.getScore()).replaceAll("[^a-zA-Z0-9 ]+", "") + "', " +
                        "'" + (dao.getTime()).replaceAll("[^a-zA-Z0-9 ]+", "") + "', " +
                        "'" + (dao.getSummary()).replaceAll("[^a-zA-Z0-9 ]+", "") + "', " +
                        "'" + (dao.getText()).replaceAll("[^a-zA-Z0-9 ]++", "") + "')";
                int numRows = executeUpdate(conn, sql);
                result = (numRows == 1) ? true : false;
            }
        }
        return result ;
    }

    private static int getNextID(String table){
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

    private static Connection createConnection() {
        try {
            Class.forName(DBConstants.DERBY_DB_DRIVER);
            Properties dbProps = new Properties();
            dbProps.put("user", DBConstants.DERBY_DB_USER);
            dbProps.put("password", DBConstants.DERBY_DB_PASSWORD);
            conn = DriverManager.getConnection(DBConstants.DERBY_DB_CON_URL, dbProps);
        } catch (Exception except){
            System.out.print("Could not connect to the database with username: " + DBConstants.DERBY_DB_USER);
            System.out.println("Check that the Derby Network Server is running on localhost.");
            except.printStackTrace();
        }
        return conn;
    }

}
