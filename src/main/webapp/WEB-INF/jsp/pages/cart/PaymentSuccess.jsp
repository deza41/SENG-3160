<%--
  Created by IntelliJ IDEA.
  User: simon janmaat
  Date: 28/08/2017
  Time: 12:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="userBean" class="NewcastleConnectionsPrototype.Group4.models.beans.UserBean" scope="session" />
<jsp:useBean id="PackageBean" class="NewcastleConnectionsPrototype.Group4.models.beans.PackageBean" scope="session"/>
<html>
<head>

    <title>Package Complete</title>

</head>
<div class="col-md-2"></div>
<div class="container col-md-8 navbar navbar-default text-center">
    <h1>Your Package Has Been Completed </h1>

<% if(!userBean.isLoggedIn()){ %>
<h2>Your Tracking ID is: </h2> <h2><jsp:getProperty name = "userBean" property = "username"/></h2>
<% } %>

<p>Thank you for booking your package with Newcastle Connections. We hope you have a wonderful time in Newcastle.</p>

</div>
<div class="col-md-2"></div>

