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
<jsp:useBean id="userBean" class="NewcastleConnectionsPrototype.Group4.models.beans.UserBean" scope="session" />
<jsp:useBean id="searchBean" class="NewcastleConnectionsPrototype.Group4.models.beans.PackageBean" scope="session" />

<%-- Pulls current business deal information and displays it.
Allows a business to update and edit a business deal --%>

<div class="container text-center">

    <html>
    <head>
        <sx:head />
        <title>Edit Deal</title>

    </head>
    <body>

    <div class="col-lg-2"></div>
    <div class="container col-lg-8 text-center">

    <h3>Edit Deal</h3>
    <s:actionerror/>

    <s:iterator value="model" var="Deals">
    <s:form name="dealForm" action="modifyDeal" method="post" enctype="multipart/form-data" theme="%{currentTheme}">
        <s:hidden name="rowindex"/>
        <s:hidden name="oldDealID" value="%{dealid}"/>


                <s:textfield class="form-control" name="dealTitle" label="Title" value="%{dealtitle}"/>

                <s:textfield class="form-control" name="Description" label="Description" value="%{dealdescription}"/>

                <s:file name="dealImageFile" label="Deal Image"/>

                <s:textfield class="form-control" name="oldPrice" label="Listing price" value="%{oldprice}"/>

                <s:textfield class="form-control" name="price" label="Discounted Price" value="%{price}"/>

                <sx:datetimepicker name="startDate" label="Format (dd-MMM-yyyy)"
                                   displayFormat="dd-MMM-yyyy" value="%{'today'}" />

                <sx:datetimepicker name="endDate" label="Format (dd-MMM-yyyy)"
                                   displayFormat="dd-MMM-yyyy" value="%{'today'}" />

                <s:textfield class="form-control" name="validDuration" label="Valid Duration" value="%{validduration}"/>

                    <s:submit value="Save Changes" align="center"/>

    </s:form>
    </s:iterator>
    </div>
    <div class="col-lg-2"></div>
    </body>
    </html>
</div>



</div><!-- /.navbar-collapse -->
</div><!-- /.container-fluid -->
</nav>

