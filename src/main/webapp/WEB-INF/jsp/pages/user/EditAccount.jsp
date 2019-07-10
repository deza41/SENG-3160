<%--
  Created by IntelliJ IDEA.
  User: blake
  Date: 10/05/2017
  Time: 11:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ShoppingCart.css"/>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<jsp:useBean id="userBean" class="NewcastleConnectionsPrototype.Group4.models.beans.UserBean" scope="session" />


<div class="container text-center">

    <html>
    <head>
        <sx:head />
        <title>Edit User</title>
    </head>
    <h3>Edit User</h3>
        <s:form action="userImage" method="post" enctype="multipart/form-data">
        <s:textfield name="firstName" label="First Name" value="%{model[0].firstname}"/>
        <s:textfield name="lastName" label="Last Name" value="%{model[0].lastName}"/>
        <s:textfield name="phoneNumber" label="phone Number" value="%{model[0].phoneNumber}"/>
        <s:textfield name="email" label="Email Address" value="%{model[0].email}"/>
        <s:textfield name="postCode" label="Post Code" value="%{model[0].postCode}"/>
        <sx:datetimepicker name="dateOfBirth" label="Date of Birth"
                displayFormat="dd-MMM-yyyy" value="%{model[0].dateofbirth}" />
            <s:file name="userImage" label="Profile Image"/>
        <s:password name="password" label="Password" />
            <s:submit value="Save Changes" align="center"/>
        </s:form>

