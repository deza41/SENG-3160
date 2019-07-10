<%--
  Created by IntelliJ IDEA.
  User: Tyrone
  Date: 12/08/2017
  Time: 7:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ShoppingCart.css"/>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<div class="container text-center">
<head>
    <sx:head/>
</head>

    <h2>Edit  Business Account: </h2>
    <div>

        <s:form  action="editBusinessUser" method="post" enctype="multipart/form-data" >
            <s:textfield name="firstName"  label="First name" value="%{model[0].firstName}"/>
            <s:textfield name="lastName" label="Last name" value="%{model[0].lastName}"/>
            <s:textfield name="phoneNumber" label="Phone Number" value="%{model[0].phoneNumber}"/>
            <s:textfield name="email" label="Email" value="%{model[0].email}"/>
            <s:textfield name="postCode" label="Postcode" value="%{model[0].postCode}"/>
            <s:file name="userImage" label="Profile Image" />
            <s:file name="files" multiple="multiple" label="Business Images" />
            <s:textfield name="businessName" label="Business Name" value="%{model[0].businessName}"/>
            <s:textfield name="street" label="street name" value="%{model[0].street}"/>
            <s:textfield name="number" label="Street number" value="%{model[0].number}"/>
            <s:textfield name="unit" label="Unit Number (optional)" value="%{model[0].unit}"/>
            <s:textfield name="suburb" label="Suburb Name" value="%{model[0].suburb}"/>
            <s:textfield name="businessDescription" label="Business Description" value="%{model[0].businessDescription}"/>
            <sx:datetimepicker label="Open Time (HH:mm)" name="businessOpen" type="time" displayFormat="HH:mm" />
            <sx:datetimepicker label="Close Time (HH:mm)" name="businessClose" type="time" displayFormat="HH:mm:ss"/>
            <s:password name="password" label="Password" />
            <s:submit />
        </s:form>
    </div>

</div>


