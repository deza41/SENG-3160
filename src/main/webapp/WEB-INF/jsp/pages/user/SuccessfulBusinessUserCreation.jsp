<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Tyrone
  Date: 12/08/2017
  Time: 7:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

//prints the data that was just entered

    <s:form  action="register" >
        <s:textfield name="firstName" label="First name" value=" %{firstName}"/>
        <s:textfield name="lastName" label="Last name" value=" %{lastName}"/>
        <s:textfield name="phoneNumber" label="Phone Number" value="%{phoneNumber}"/>
        <s:textfield name="email" label="Email" value=" %{email}"/>
        <s:textfield name="postCode" label="Post Code" value=" %{postCode}"/>
        <s:textfield name="profileImageURL" label="dealImageURL" value=" %{profileImageURL}"/>
        <s:textfield name="businessID" label="Business ID" value="%{businessID}"/>
        <s:textfield name="businessName" label="Business Name" value="%{businessName}"/>
        <s:textfield name="street" label="street" value="%{street}"/>
        <s:textfield name="number" label="number" value="%{number}"/>
        <s:textfield name="unit" label="Unit" value="%{unit}"/>
        <s:textfield name="suburb" label="Suburb" value="%{suburb}"/>
        <s:textfield name="businessType" label="Business Type" value="%{businessType}"/>
        <s:textfield name="businessDescription" label="Business Description" value="%{businessDescription}"/>
        <s:textfield name="businessOpen" label="Business Open" value="%{businessOpen}"/>
        <s:textfield name="businessClose" label="Business Close" value="%{businessClose}" />
        <s:password name="password" label="Password" />
    </s:form>

</html>