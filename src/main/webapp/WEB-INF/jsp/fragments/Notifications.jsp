<%--
  Created by IntelliJ IDEA.
  User: Tyrone
  Date: 24/05/2017
  Time: 7:52 PM
  To change this template use File | Settings | File Templates.
--%>

<%-- Handles notifications, it will use an interceptor to grab and push notifications that are relevant to the user.

 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:useBean id="userBean" class="NewcastleConnectionsPrototype.Group4.models.beans.UserBean" scope="session" />
<jsp:useBean id="searchBean" class="NewcastleConnectionsPrototype.Group4.models.beans.PackageBean" scope="session" />
<jsp:useBean id="dealBean" class="NewcastleConnectionsPrototype.Group4.models.beans.DealBean" scope="session" />
<jsp:useBean id="notificationBean" class="NewcastleConnectionsPrototype.Group4.models.beans.NotificationBean" scope="session"/>

<% if(userBean.isLoggedIn()&& userBean.getRole().equals("user"))  { %>
<div class="dropdown mr-3 nav-item">
    <a class="dropdown-toggle " data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="badge">6</span> Notifications<span class="caret"></span></a>
    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton">
        <a class="dropdown-item" href="<s:url value='navToListOfNotifications.action'/>">View All Notifications</a>
        <h6 class="dropdown-header">New Events</h6>
        <a class="dropdown-item" href="<s:url value='notification.action?notificationID=Bitter'/>">Bitter & Twisted - Maitland</a>
        <a class="dropdown-item" href="<s:url value='notification.action?notificationID=ArjBarker'/>">Arj Barker: Organic</a>
        <div class="dropdown-divider"></div>
        <h6 class="dropdown-header">New Experiences</h6>
        <a class="dropdown-item" href="<s:url value='notification.action?notificationID=Whale'/>">Whale Watching Cruise</a>
        <div class="dropdown-divider"></div>
        <h6 class="dropdown-header">Pending Reviews</h6>
        <a class="dropdown-item" href="<s:url value='notification.action?notificationID=MerewetherMotel'/>">List Of reviews to be completed</a>
        <h6 class="dropdown-header">Booking Confirmations</h6>
        <a class="dropdown-item" href="<s:url value='navToListOfNotifications.action'/>"></a>
    </div>
</div>
<% };%>

<% if(userBean.isLoggedIn()&& userBean.getRole().equals("business"))  { %>
<div class="dropdown mr-3 nav-item">
    <a class="dropdown-toggle " data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="badge">6</span>Notifications<span class="caret"></span></a>
    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton">
        <a class="dropdown-item" href="<s:url value='manageOrder.action'/>">Pending orders</a>
        <a class="dropdown-item" href="<s:url value='manageReview.action'/>">New Review</a>
        <div class="dropdown-divider"></div>
        <a class="dropdown-item" href="<s:url value='navToListOfNotifications.action'/>">View all</a>
    </div>
</div>
<% };%>
