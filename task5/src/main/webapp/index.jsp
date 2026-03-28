<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="com.example.model.Student" %>
<%@ page import="com.example.model.Faculty" %>
<%@ page import="com.example.model.Institute" %>

<!DOCTYPE html>
<html>
<head>
    <title>Лабораторна 4 - Web JSP</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: lightgray; margin: 20px; }
        .container { background-color: white; padding: 20px; border-radius: 8px; margin-bottom: 20px; }
        .calc-grid { display: grid; grid-template-columns: 200px 200px; gap: 10px; margin-bottom: 15px; }
        .calc-grid label { text-align: right; font-weight: bold; }
        input[type="text"] { width: 100%; padding: 5px; }
        .btn-solve { background-color: green; color: black; font-weight: bold; padding: 10px; cursor: pointer; border: none; }
        .btn-clear { background-color: #ddd; padding: 10px; cursor: pointer; border: none; }
    </style>
</head>
<body>


    <div class="container">
        <h2>Distance Calculator (Haversine Formula)</h2>
        <form method="POST">
            <div class="calc-grid">
                <label>Lat 1 (Latitude, deg):</label>
                <input type="text" name="txtLat1" value="<%= request.getParameter("txtLat1") != null ? request.getParameter("txtLat1") : "" %>">
                
                <label>Lon 1 (Longitude, deg):</label>
                <input type="text" name="txtLon1" value="<%= request.getParameter("txtLon1") != null ? request.getParameter("txtLon1") : "" %>">
                
                <label>Lat 2 (Latitude, deg):</label>
                <input type="text" name="txtLat2" value="<%= request.getParameter("txtLat2") != null ? request.getParameter("txtLat2") : "" %>">
                
                <label>Lon 2 (Longitude, deg):</label>
                <input type="text" name="txtLon2" value="<%= request.getParameter("txtLon2") != null ? request.getParameter("txtLon2") : "" %>">
                
                <button type="submit" class="btn-solve">Solve</button>
                <a href="index.jsp"><button type="button" class="btn-clear">Clear</button></a>
                
                <label>Distance (m):</label>
                <%
                    String result = "";
                    if ("POST".equalsIgnoreCase(request.getMethod())) {
                        try {
                            double lat1 = Double.parseDouble(request.getParameter("txtLat1"));
                            double lon1 = Double.parseDouble(request.getParameter("txtLon1"));
                            double lat2 = Double.parseDouble(request.getParameter("txtLat2"));
                            double lon2 = Double.parseDouble(request.getParameter("txtLon2"));

                            // Твоя логіка з 1-ї лаби 1 в 1
                            final double R = 6371e3;
                            double phi1 = Math.toRadians(lat1);
                            double phi2 = Math.toRadians(lat2);
                            double deltaPhi = Math.toRadians(lat2 - lat1);
                            double deltaLambda = Math.toRadians(lon2 - lon1);

                            double a = Math.pow(Math.sin(deltaPhi / 2), 2) +
                                       Math.cos(phi1) * Math.cos(phi2) *
                                       Math.pow(Math.sin(deltaLambda / 2), 2);
                            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
                            double distance = R * c;

                            result = String.format(java.util.Locale.US, "%.2f m", distance);
                        } catch (Exception e) {
                            result = "Error: Invalid values";
                        }
                    }
                %>
                <input type="text" readonly value="<%= result %>" style="background-color: #eee; font-weight: bold;">
            </div>
        </form>
    </div>

</body>
</html>