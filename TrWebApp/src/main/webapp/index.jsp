<%-- Author     : lahiru.gallege --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>(TRRuSST)Trust and Review-based Recommender Util for Software Selection Testing</title>
</head>
<body>
<h2 align="center">(TRRuSST)Trust-based Ranker and Recommender Util for Software Selection Testing</h2>
<br/>
<table width="90%" align="center" border="1">
    <tr>
        <th>Prevalent Approach</th>
        <th>Trust-based Approach</th>
    </tr>
        <tr>
            <td>
                <table width="90%" align="center" border="0">
                    <form action="PrevalentServiceRecommender" method="get">
                        <tr>
                            <td>Search Query : </td>
                            <td> <input type="text" name="Prevalent" width="90%"></td>
                        </tr>
                        <tr>
                            <td><br/></td><td></td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="Search For Software"></td>
                            <td></td>
                        </tr>
                    </form>
                </table>
            </td>
            <td>
                <table width="90%" align="center" border="0">
                    <form action="TrustBasedServiceRecommender" method="get">
                        <tr>
                            <td>Search Query : </td>
                            <td> <input type="text" name="TrustBased" width="90%"></td>
                        </tr>
                        <tr>
                            <td>Quality of Service : </td>
                            <td> <input type="text" name="QoS" width="90%"></td>
                        </tr>
                        <tr>
                            <td><br/></td><td></td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="Search For Software"></td>
                            <td></td>
                        </tr>
                    </form>
                </table>
            </td>
        </tr>
</table>
</body>
</html>