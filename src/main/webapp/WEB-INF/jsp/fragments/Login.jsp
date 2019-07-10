<%--
  Created by IntelliJ IDEA.
  User: Tyrone
  Date: 24/05/2017
  Time: 1:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:useBean id="userBean" class="NewcastleConnectionsPrototype.Group4.models.beans.UserBean" scope="session" />

<% if(!userBean.isLoggedIn()) { %>
<div class="dropdown mr-3">
    <a class="dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Login</a>
    <div class="dropdown-menu dropdown-menu-right p-2 login-dropdown-box" aria-labelledby="navbarDropdownMenuLink">
        <div class="row no-gutters">
            <form class="form" role="form" method="post" action="login.action">
                <div class="form-group">
                    <input type="text" class="form-control" id="usernames" name="username" placeholder="Username" required>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block">Sign in</button>
                </div>
            </form>
        </div>
        <div class="row no-gutters">
            <div class="bottom text-center">
                New User? <a href="ChoiceUserOrBusiness.action">Register</a>
            </div>
        </div>
    </div>
</div>

<%}else{ %>

<div class="dropdown nav-item mr-3">
    <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Account<span class="caret"></span></a>
    </button>
    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton">
        <% if(userBean.isLoggedIn() && userBean.getRole().equals("user")){ %>
        <a class="dropdown-item" href="<s:url value='userHistory.action'/>">History</a>
        <% };%>

        <% if(userBean.isLoggedIn() && userBean.getRole().equals("admin")){ %>
        <a class="dropdown-item" href="<s:url value='DisplayChoice.action'/>">Contact Details</a>
        <% };%>

        <% if(userBean.isLoggedIn()&& userBean.getRole().equals("user"))  { %>
        <a class="dropdown-item" href="<s:url value='DisplayEditUser.action'/>">Edit your account</a>
        <% };%>
        <% if(userBean.isLoggedIn()&& userBean.getRole().equals("business"))  { %>
        <a class="dropdown-item" href="<s:url value='DisplayEditBusiness.action'/>">Edit your account</a>
        <% };%>

        <% if(userBean.isLoggedIn() && userBean.getRole().equals("user")){ %>
        <a class="dropdown-item" href="<s:url value='trackYourOrder.action'/>">Track your order</a>
        <% };%>

        <% if(userBean.isLoggedIn() && userBean.getRole().equals("business")){ %>
        <div class="dropdown-divider"></div>
        <a class="dropdown-item" href="<s:url value='manageOrder.action'/>">View orders</a>
        <div class="dropdown-divider"></div>
        <a class="dropdown-item" href="<s:url value='createDeal.action'/>">Create deal</a>
        <a class="dropdown-item" href="<s:url value='editDeal.action'/>">Edit deal</a>
        <a class="dropdown-item" href="<s:url value='viewDeals.action'/>">View deals</a>
        <% };%>
    </div>
</div>
<div>
<a class="" href="<s:url value='logout.action'/>">Log Out</a>
</div>
<% };%>