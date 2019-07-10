<%--
  Created by IntelliJ IDEA.
  User: Tyrone
  Date: 25/05/2017
  Time: 4:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<div class="container text-center">
<html>
    <head>
        <sx:head />
        <title>Create Deal</title>

        <style>
            table {
                margin: auto;
            }
            td {
                padding: 0.25rem;
            }
        </style>
    </head>
    <body>
    <div class="col-lg-2"></div>
    <div class="container col-lg-8 text-center">

    <div class="section-header">
        <h2>Create Deal</h2>
    </div>
    <s:actionerror/>

    <div class="form-group text-center">
    <s:form name="dealForm" action="createDeal" method="post" enctype="multipart/form-data" theme="%{currentTheme}">
        <s:hidden name="rowindex"/>
            <s:textfield class="form-control" name="dealTitle" label="Title"/>

            <s:textfield class="form-control" name="Description" label="Description"/>

            <s:file name="dealImageFile" label="Deal Image"/>

            <s:textfield class="form-control" name="oldPrice" label="Listing price"/>

            <s:textfield class="form-control" name="price" label="Discounted Price"/>

            <sx:datetimepicker name="startDate" label="Format (dd-MMM-yyyy)"
                               displayFormat="dd-MMM-yyyy" value="%{'today'}" />

            <sx:datetimepicker name="endDate" label="Format (dd-MMM-yyyy)"
                               displayFormat="dd-MMM-yyyy" value="%{'today'}" />

            <s:textfield class="form-control" name="validDuration" label="Valid Duration"/>

            <s:submit value="Submit" align="center"/>
    </s:form>
    </div>
    </div>
    <div class="col-lg-2"></div>
    </body>
</html>
</div>
