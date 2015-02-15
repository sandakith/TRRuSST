package org.trrusst.software.recommender;

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

    private static Statement stmt = null;
    private static Connection conn = null;

    public static String[] getResults(String query){
        conn = createConnection();
        String[] runQueryResult = runQuery(conn, query.toString());
        return runQueryResult;
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
