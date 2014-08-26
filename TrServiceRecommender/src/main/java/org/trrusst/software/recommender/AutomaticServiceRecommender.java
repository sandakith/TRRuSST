package org.trrusst.software.recommender;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by lahiru.gallege
 */
public class AutomaticServiceRecommender extends HttpServlet implements Servlet {

    // Logger for the servlet
    private static final Logger log = Logger.getLogger(AutomaticServiceRecommender.class.getName());

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get the form parameters
        String spList = (String)request.getParameter("spList");

        // Set Request Attributes
        request.setAttribute("recommendedSPList", spList);

        // Log the request status
        log.log(Level.INFO, "Tagging Automatic Recommendations : "
                + "| Description : " + "None");

        // Forward the request back
        request.getRequestDispatcher("/recommendServiceProviders.jsp").forward(request, response);

    }

}
