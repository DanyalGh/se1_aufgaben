<%--
  Created by IntelliJ IDEA.
  User: Micha
  Date: 09.05.2020
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta charset="utf-8">
    <link rel="icon" href="favicon.ico">
    <title>Parkhaus</title>
    <script src='https://ccmjs.github.io/mkaul-components/parkhaus/versions/ccm.parkhaus-9.0.0.js'></script>
  </head>
  <body>
    <ccm-parkhaus-9-0-0 server_url="http://localhost:8080/A5_3_Charts_war_exploded/Parkhaus",
                        extra_buttons='["Summe","Durchschnitt","Steuer"]'
                        extra_charts='["chart"]'>
    </ccm-parkhaus-9-0-0>
  </body>
</html>

