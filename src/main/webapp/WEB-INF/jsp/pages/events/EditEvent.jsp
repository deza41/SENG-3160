<%--
  Created by IntelliJ IDEA.
  User: Tyrone
  Date: 19/07/2017
  Time: 3:03 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<jsp:useBean id="userBean" class="NewcastleConnectionsPrototype.Group4.models.beans.UserBean" scope="session" />

<%-- Pulls current business event information and displays it.
Allows a business to update and edit a business event --%>


<div class="container text-center">

    <html>
    <head>
        <sx:head />
        <title>Edit Event</title>

    </head>
    <body>

    <h3>Edit Event</h3>
    <s:actionerror/>

    <s:iterator value="model" var="Event">
        <s:form name="eventForm" action="modifyEvent" method="post" enctype="multipart/form-data" theme="%{currentTheme}">
            <s:hidden name="rowindex"/>
            <s:hidden name="oldEventID" value="%{eventid}"/>

            <table id="eventTable" width="350px" border="1">
                <tr>
                    <s:textfield name="title" label="Title" value="%{title}"/>
                </tr>
                <tr>
                    <s:select class="custom-select" label="Select a category"
                              headerKey="-1" headerValue="Select Category"
                              list="#{ 'food': 'food', 'experience':'experience', 'accommodation':'accommodation'}"
                              name="category"
                              value="%{oldCategory}" />
                </tr>
                <tr>
                    <s:textfield name="eventDescription" label="Description" value="%{eventdescription}"/>
                </tr>
                <tr>
                    <s:file name="eventImageFile" label="Event Image"/>
                </tr>
                <tr>
                    <s:textfield name="oldPrice" label="Listing price" value="%{oldprice}"/>
                </tr>
                <tr>
                    <s:textfield name="price" label="Discounted Price" value="%{price}"/>
                </tr>
                <tr>
                    <sx:datetimepicker name="startDate" label="Format (dd-MMM-yyyy)"
                                       displayFormat="dd-MMM-yyyy" value="%{'today'}" />
                </tr>
                <tr>
                    <sx:datetimepicker name="endDate" label="Format (dd-MMM-yyyy)"
                                       displayFormat="dd-MMM-yyyy" value="%{'today'}" />
                </tr>

            </table>

            <table>
                <tr>
                    <td align="center">
                        <s:submit value="Save Changes" align="center"/>
                    </td>
                </tr>
            </table>
        </s:form>
    </s:iterator>
    </body>
    </html>
</div>

</div><!-- /.navbar-collapse -->
</div><!-- /.container-fluid -->
</nav>

