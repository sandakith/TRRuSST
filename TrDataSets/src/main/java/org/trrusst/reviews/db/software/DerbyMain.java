package org.trrusst.reviews.db.software;

import org.trrusst.reviews.db.DBConstants;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by LG-Admin on 11/17/2014.
 */
public class DerbyMain {

    public static void main(String[] args) {

        System.out.println("AzSoftware Start Time : "+  getCurrentDateTime());

        // Load the Test Dataset
        DerbyDBUtil.loadAzDataSet(DBConstants.FILE_AZ_DB_SOFTWARE_TEST);
        // Load the Training Dataset
        DerbyDBUtil.loadAzDataSet(DBConstants.FILE_AZ_DB_SOFTWARE);

        System.out.println("DDS End Time : "+  getCurrentDateTime());

    }

    public static String getCurrentDateTime(){
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter=  new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
        String dateNow = formatter.format(currentDate.getTime());
        return dateNow;
    }

}
