package org.trrusst.reviews.db.software;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * DB Utility class for DerbyDB based operations
 * Created by lahiru.gallege
 */
public class DerbyDBUtil {

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
            String currentLine = null; int counter = 0;
            StringBuffer dataBuffer = new StringBuffer();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile));
            while ((currentLine = bufferedReader.readLine()) != null){
                System.out.println(currentLine);
                if (currentLine.trim().length() == 0){
                    counter = 0;
                    loadData(dataBuffer.toString());
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
        //azSoftwareDAOs.add(azSoftwareDAO);
    }

}
