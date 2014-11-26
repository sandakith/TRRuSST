package org.trrusst.software.recommender;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by lahiru.gallege
 */
public class TrustBasedServiceRecommender extends HttpServlet implements Servlet {

    // Logger for the servlet
    private static final Logger log = Logger.getLogger(TrustBasedServiceRecommender.class.getName());

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get the form parameters
        //String spList = (String)request.getParameter("spList");
        String query = (String)request.getParameter("TrustBased");
        String qos = (String)request.getParameter("QoS");

        //System.out.println("Inside A : " + query);
        String sqlQuery = "select id,title,score from App.AzSoftware where title like '%" + query + "%' and text like '%" + qos +"%'";

        String[] resultSet = DerbyDBUtil.getResults(sqlQuery);

        List<ServiceDAO> spList = new ArrayList<ServiceDAO>();

        for (int i = 0 ; i < resultSet.length ; i++){
            String[] resultArray = resultSet[i].split(",");
            ServiceDAO serviceDAO = new ServiceDAO();
            serviceDAO.setId(resultArray[0]);
            serviceDAO.setName(resultArray[1]);
            serviceDAO.setRating(resultArray[2]);
            spList.add(serviceDAO);
        }

        // Set Request Attributes
        request.setAttribute("recommendedTrustList", spList);
        request.setAttribute("selection", "TrustBased");

        // Log the request status
        log.log(Level.INFO, "Tagging Automatic Recommendations : "
                + "| Description : " + "None");

        // Forward the request back
        request.getRequestDispatcher("/recommendServiceProviders.jsp").forward(request, response);

    }

}
