package org.trrusst.software.recommender;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton class for a Derby DB connection
 * Created by lahiru.gallege
 */
public class DerbyDBConnection {

    // DB Connection for the Derby Server
    private static Connection conn = null;

    // Exists only to defeat instantiation.
    public DerbyDBConnection () {}

    /**
     * Create derby database connection
     * @return DB Connection
     */
    public static Connection getInstance() {
        if(conn == null) {
            try {
                Class.forName(DBConstants.DERBY_DB_DRIVER);
                conn = DriverManager.getConnection(DBConstants.DERBY_DB_CON_URL,
                        DBConstants.DERBY_DB_USER, DBConstants.DERBY_DB_PASSWORD);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * Close derby database connection
     * @return DB Connection
     */
    public static boolean forceClose() {
        if(conn != null) {
            try {
                conn.close(); return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
