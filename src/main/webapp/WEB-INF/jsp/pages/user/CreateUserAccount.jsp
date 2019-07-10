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
<div class="container text-center">

    <sx:head/>
    <div class="section-header">
    <h2>Create A New User: </h2>
    </div>
    <div>
        <s:form  action="createUser" method="post" enctype="multipart/form-data" >
            <s:textfield name="username" label="UserName"/>
            <s:textfield name="firstName" label="First name" />
            <s:textfield name="lastName" label="Last name" />
            <s:textfield name="phoneNumber" label="Phone Number" />
            <s:textfield name="email" label="Email" />
            <s:textfield name="postCode" label="Post Code" />
            <sx:datetimepicker name="dateOfBirth" label="Date of Birth"
                               displayFormat="dd-MMM-yyyy" value="%{'today'}" />
            <s:password name="password" label="Password" />
            <s:file name="profileImageFile" label="Profile Image"/>

            <s:submit />
        </s:form>
    </div>







</div>

