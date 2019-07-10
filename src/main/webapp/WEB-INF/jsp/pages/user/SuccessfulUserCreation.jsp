<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: simon janmaat
  Date: 2/08/2017
  Time: 2:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

//prints the data that was just entered

<s:form  action="register" >
    <s:textfield name="username" label="UserName" value="%{username}"/>
    <s:textfield name="firstName" label="First name" value="%{firstName}"/>
    <s:textfield name="lastName" label="Last name" value="%{lastName}"/>
    <s:textfield name="phoneNumber" label="Phone Nuumber" value="%{phoneNumber}"/>
    <s:textfield name="email" label="Email" value="%{email}" />
    <s:textfield name="postCode" label="Post Code" value="%{postCode}"/>
    <s:password name="password" label="Password" />
</s:form>

</html>
