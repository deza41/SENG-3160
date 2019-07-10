<%--
  Created by IntelliJ IDEA.
  User: simon janmaat
  Date: 18/08/2017
  Time: 1:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ShoppingCart.css"/>




<div class="container">

<table>
    <tr>
        <td>User</td>
        <td>Review Title</td>
        <td>Review Details</td>
        <td>Rating</td>
        <td>Date Review Made</td>
    </tr>
        <s:iterator value="reviews" status="cnt" var="pendingBookings" step="1">
            <td><s:property value="listOfUsers[#cnt.index].username"/></td>
            <td><s:property value="title"/></td>
            <td><s:property value="details"/></td>
            <td><s:property value="rating"/>/10</td>
            <td><s:property value="date"/></td>

        </s:iterator>
</table>
</div>





