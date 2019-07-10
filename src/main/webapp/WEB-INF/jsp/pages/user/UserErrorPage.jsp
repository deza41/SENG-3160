<%--
  Created by IntelliJ IDEA.
  User: simon janmaat
  Date: 3/08/2017
  Time: 10:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>

<body>

<h2>Error</h2>
<div>
    <s:form  action="register" >
        <s:textfield name="username" label="UserName" value="%{username}"/>
        <s:textfield name="firstName" label="First name" value="%{firstName}"/>
        <s:textfield name="lastName" label="Last name" value="%{lastName}"/>
        <s:textfield name="phoneNumber" label="Phone Nuumber" value="%{phoneNumber}"/>
        <s:textfield name="email" label="Email" value="%{email}" />
        <s:textfield name="postCode" label="Post Code" value="%{postCode}"/>
        <s:textfield name="DOB" label="Date of Birth" value="%{DOB}"/>
        <s:password name="password" label="Password" />
        <s:submit />
    </s:form>
</div>


// idk what this is for ^
//------------------------




</body>
</html>
