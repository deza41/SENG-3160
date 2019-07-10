<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: simon janmaat
  Date: 29/08/2017
  Time: 10:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>

<div class="container">
    <div class="section-header">
        <h1>Notifications</h1>
    </div>
    <s:iterator value="listOfNotifications" status="cnt" var="BusinessModel" step="1">
        <div class="card mt-4 mb-4">
            <div class="card-block">
                <h4 class="card-title">
                    <s:property value="title"/>
                </h4>
                <s:property value="description"/>
            </div>
        </div>
    </s:iterator>
</div>

