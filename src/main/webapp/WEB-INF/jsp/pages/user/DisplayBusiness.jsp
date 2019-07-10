<%--
  Created by IntelliJ IDEA.
  User: Tyrone
  Date: 25/08/2017
  Time: 12:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ShoppingCart.css"/>
<%@ taglib prefix="s" uri="/struts-tags" %>


<s:actionerror/>
<div class="container">


<div class="section-header">
    <h2>Business Accounts:</h2>
</div>
<table>
    <tr>
        <td>User Name</td>
        <td>First Name</td>
        <td>Last Name</td>
        <td>Phone Number</td>
        <td>Email</td>
    </tr>

<s:iterator value="model" var="Business">
    <tr>
        <td><s:property value="username"/></td>
        <td><s:property value="firstname" /></td>
        <td><s:property value="lastname" /></td>
        <td><s:property value="phonenumber" /></td>
        <td><s:property value="email"/></td>
    </tr>
</s:iterator>
</table>
</div>