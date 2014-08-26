package org.trrusst.reviews.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DB based String Constants are encoded here.
 * Created by lahiru.gallege
 */
public class DBConstants {

    // Derby database connection details
    public static final String DERBY_DB_DRIVER              = "org.apache.derby.jdbc.ClientDrive" ;
    public static final String DERBY_DB_CON_URL             = "jdbc:derby://localhost:1527/AmazonDB" ;
    public static final String DERBY_DB_USER                = "admin" ;
    public static final String DERBY_DB_PASSWORD            = "admin" ;

    // Derby DB configuration flags
    public static final boolean FLAG_AMAZON_DB_POPULATION   = false ;

    // Amazon Dataset File Location Details
    public static final String FILE_AZ_DB_SOFTWARE          = "AzSoftware.txt" ;
    public static final String FILE_AZ_DB_SOFTWARE_TEST     = "AzSoftwareTest.txt" ;

}
