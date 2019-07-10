<%--
  Created by IntelliJ IDEA.
  User: Tyrone
  Date: 16/08/2017
  Time: 1:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>File Upload Success</title>
    <style>
        #map {
            height: 400px;
            width: 100%;
        }
    </style>
</head>
<body>
You have successfully uploaded <s:property value="myFileFileName"/>

<h3>My Google Maps Demo</h3>
<div id="map"></div>
<script>
    function initMap() {
        var uluru = {lat: <s:property value="latitude"/> , lng: <s:property value="longitude"/>};
        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 4,
            center: uluru
        });
        var marker = new google.maps.Marker({
            position: uluru,
            map: map
        });
    }
</script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCg3btXv0nqnh4ROTT1lOKVeQ9dk7fHXP8&callback=initMap">
</script>

<s:property value="latitude"/>
</body>
</html>


