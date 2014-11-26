<%--
    Author     : lahiru.gallege
--%>
<%@ page import="java.util.Map" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>TRRuSST Results</title>
</head>
<body>
<h3>TRRuSST Results</h3>

<c:if test="${selection == 'Prevalent'}">
Top Software Ranked Based On the Content :<br/>
<br/>
<table align="left" border="1">
    <tr>
        <th>Software</th>
        <th>Service Provider Name</th>
        <th>Service Rating</th>
    </tr>
    <c:forEach var="entry" items="${recommendedSPList}" varStatus="loop">
        <tr>
            <td>
                    ${entry.id}
            </td>
            <td>
                    ${entry.name}
            </td>
            <td align="center">
                    ${entry.rating}
            </td>
        </tr>
    </c:forEach>
</table>
</c:if>

<c:if test="${selection == 'TrustBased'}">
    Top Software Ranked Based On the Trust :<br/>
    <br/>
    <table align="left" border="1">
        <tr>
            <th>Software</th>
            <th>Service Provider Name</th>
            <th>Service Rating</th>
        </tr>
        <c:forEach var="entry" items="${recommendedTrustList}" varStatus="loop">
            <tr>
                <td>
                        ${entry.id}
                </td>
                <td>
                        ${entry.name}
                </td>
                <td align="center">
                        ${entry.rating}
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>
