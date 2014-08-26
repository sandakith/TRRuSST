<%--
    Author     : lahiru.gallege
--%>
<%@ page import="java.util.Map" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>SnapFix Automatic SP Recommender</title>
</head>
<body>
<h3>SnapFix Automatic Service Provider Recommendations</h3>
Top Service Provider Recommendations Based On Reviews :<br/>
<br/>
<table align="left" border="1">
    <tr>
        <th>SP ID</th>
        <th>Service Provider Name</th>
        <th>Provided Service</th>
        <th>Review Count</th>
    </tr>
    <c:forEach var="entry" items="${recommendedSPList}" varStatus="loop">
        <tr>
            <td>
                    ${entry.spID}
            </td>
            <td>
                    ${entry.spName}
            </td>
            <td>
                <a href="#" title="${entry.spFullService}">${entry.spService} </a>
            </td>
            <td align="center">
                    ${entry.spReviewCount}
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
