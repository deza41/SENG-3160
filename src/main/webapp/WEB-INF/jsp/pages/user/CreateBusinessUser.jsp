<%--
  Created by IntelliJ IDEA.
  User: Tyrone
  Date: 9/08/2017
  Time: 1:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ShoppingCart.css"/>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/jquery.datetimepicker.css"/>
<style type="text/css">

    .custom-date-style {
        background-color: red !important;
    }

    .input{
    }
    .input-wide{
        width: 500px;
    }

    label {
        color: #737774;
        font-weight: bold;
    }


</style>
<head>
    <sx:head/>
</head>
<div class="container text-center">


    <div class="section-header">

    <h2>Create A Business User: </h2>
    </div>

        <s:form  action="createBusinessUser" method="post" enctype="multipart/form-data" >
            <s:textfield name="userName" label="Username" />
            <s:password name="password" label="Password" />
            <s:textfield name="firstName" label="First name" />
            <s:textfield name="lastName" label="Last name" />
            <s:textfield name="phoneNumber" label="Phone Number" />
            <s:textfield name="email" label="Email" />
            <s:textfield name="postCode" label="Postcode" />
            <s:file name="profileImageFile" label="Profile Image"/>
            <s:textfield name="businessName" label="Business Name"/>
            <s:textfield name="street" label="street name"/>
            <s:textfield name="number" label="Street number"/>
            <s:textfield name="unit" label="Unit Number (optional)"/>
            <s:textfield name="suburb" label="Suburb Name"/>
            <s:select label="Select a category"
                      headerKey="-1" headerValue="Select Category"
                      list="#{ 'food': 'food', 'experience':'experience', 'accommodation':'accommodation', 'transport':'transport'}"
                      name="categoryName"
                      value="6" />
            <s:textfield name="businessDescription" label="Business Description"/>
            <sx:datetimepicker label="Open Time (HH:mm)" name="businessOpen" type="time" displayFormat="HH:mm" />
            <sx:datetimepicker label="Close Time (HH:mm)" name="businessClose" type="time" displayFormat="HH:mm:ss"/>

            
            <s:submit />
        </s:form>

        <script src="${pageContext.request.contextPath}/resources/js/jquery.datetimepicker.full.js"></script>
        <script>
            $('#datetimepicker1').datetimepicker({
                datepicker:false,
                format:'H:i',
                step:10
            });
            $('#datetimepicker2').datetimepicker({
                datepicker:false,
                format:'H:i',
                step:10
            });
        </script>
</div>