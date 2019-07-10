<%--
  Created by IntelliJ IDEA.
  User: Rhys
  Date: 24/05/2017
  Time: 9:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/monthly.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/monthly.js"></script>
    <script type="text/javascript">
        $(window).load( function() {
            $('#mycalendar').monthly({
                mode: 'event',
                dataType: 'json',
                jsonUrl: 'events.json'
            });
        });
    </script>

</head>

<body>
            <div class="monthly" id="mycalendar"></div>

</body>
</html>
