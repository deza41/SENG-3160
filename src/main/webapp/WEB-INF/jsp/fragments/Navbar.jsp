<%--
  Created by IntelliJ IDEA.
  User: blake
  Date: 10/05/2017
  Time: 1:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:useBean id="userBean" class="NewcastleConnectionsPrototype.Group4.models.beans.UserBean" scope="session" />
<jsp:useBean id="searchBean" class="NewcastleConnectionsPrototype.Group4.models.beans.PackageBean" scope="session" />
<jsp:useBean id="dealBean" class="NewcastleConnectionsPrototype.Group4.models.beans.DealBean" scope="session" />


<!-- Navigation-->

<nav class="navbar navbar-toggleable-md navbar-light bg-faded newcon-nav">
    <div class="collapse navbar-collapse">
        <ul class="navbar-nav navbar-left mr-auto">
            <a class="navbar-brand"><jsp:include page="Banner.jsp"/></a>
            <li class="nav-item active">
                <a class="nav-link" href="<s:url value='/home.action'/>">Home<span class="sr-only">(current)</span></a>
            </li>
            <% if(userBean.getRole() != "business"){ %>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Search
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <h6 class="dropdown-header">Things to do</h6>

                    <a class="dropdown-item" href="<s:url value='searchEvent.action'/>">Events</a>
                    <a class="dropdown-item" href="<s:url value='search.action?category=experience'/>">Experiences</a>
                    <a class="dropdown-item" href="<s:url value='search.action?category=food'/>">Food</a>
                    <div class="dropdown-divider"></div>
                    <h6 class="dropdown-header">Staying in Newcastle</h6>
                    <a class="dropdown-item" href="<s:url value='search.action?category=accommodation'/>">Accommodation</a>
                    <a class="dropdown-item" href="<s:url value='search.action?category=transport'/>">Transport</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<s:url value='itinerary.action'/>">Itinerary</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<s:url value='shoppingCart.action'/>">Shopping Cart</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="<s:url value='navToTrackOrder.action'/>">Track Your Order</a>
            </li>

            <% } %>

            <% if(userBean.getRole() == "business" || userBean.getRole() == "admin"){ %>
            <li class="nav-item">
                <a class="nav-link" href="<s:url value='manageOrder.action'/>">View Orders</a>
            </li>
            <!-- Business User -->
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLinkBusiness" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Manage Deals
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLinkBusiness">
                    <a class="dropdown-item" href="<s:url value='createNewDeal.action'/>">Create Deal</a>
                    <a class="dropdown-item" href="<s:url value='viewDeals.action'/>">View Deals</a>
                </div>
            </li>
            <!-- Events -->
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLinkEvents" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Manage Events
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLinkEvents">
                    <a class="dropdown-item" href="<s:url value='createNewEvent.action'/>">Create Event</a>
                    <a class="dropdown-item" href="<s:url value='viewEvents.action'/>">View Events</a>
                </div>
            </li>
            <% } %>

            <% if(userBean.getRole() == "admin"){ %>

            <li class="nav-item">
                <a class="nav-link" href="<s:url value='enterEmailTest.action'/>">Test Email</a>
            </li>


            <li class="nav-item">
                <a class="nav-link" href="<s:url value='viewStatistics.action'/>">View Statistics</a>
            </li>

            <% } %>

            <% if(userBean.getRole() != "business" && userBean.isLoggedIn()){ %>
            <li class="nav-item">
                <a class="nav-link" href="<s:url value='selectQRCode.action'/>">Redeem QR Code</a>
            </li>
            <% } %>
        </ul>
        <ul class="navbar-nav navbar-left">
            <jsp:include page="Notifications.jsp"/>
            <jsp:include page="Login.jsp"/>
        </ul>
    </div>
</nav>