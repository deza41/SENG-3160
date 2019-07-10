<%--
  Created by IntelliJ IDEA.
  User: Tyrone
  Date: 25/05/2017
  Time: 7:32 PM
  To change this template use File | Settings | File Templates.
--%>

<%-- Lists all orders for a business user. Allows them to approve or deny an order.--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ShoppingCart.css"/>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="container text-center">



<s:if test="%{ListOfPendingBookings.size!=0}">
<div class="section-header">
    <h1>Bookings Awaiting Confirmation</h1>
</div>

<div class="section-header">
    <h2 class="text-left">Booking details: </h2>
</div>
<table class="table">
    <tr>
        <th>Customer Name</th>
        <th>Last Name</th>
        <th>Phone Number</th>
        <th>Email</th>
        <th>D.O.B</th>
        <th>Adults #</th>
        <th>Children #</th>
        <th>Booking Time</th>
        <th>Booking Date</th>
    </tr>
    <s:iterator value="listOfPendingUsers" status="dealscnt" step="1">
        <s:form theme="simple" action="acceptBooking" method="post">
        <tr>
            <td><s:property value="firstName" /></td>
            <td><s:property value="lastName" /></td>
            <td><s:property value="phoneNumber"/></td>
            <td><s:property value="email" /></td>
            <td><s:property value="dateOfBirth" /></td>

            <s:iterator value="listOfPendingBookings" status="bookingcnt" step="1">
                <s:if test="#dealscnt.index == #bookingcnt.index">
                    <td><s:property value="numberofadults"/></td>
                    <td><s:property value="numberofchildren"/></td>
                    <td><s:property value="starttime"/></td>
                    <td><s:property value="date"/></td>
                    <s:hidden name="bookingID" value='%{bookingid}'/>
                    <td><s:submit cssClass="btn btn-success" value="Accept"/>
                        <s:submit cssClass="btn btn-danger" value="Decline" action="declineBooking"/></td>
                </s:if>
            </s:iterator>

        </tr>
        </s:form>
    </s:iterator>


</table>
    </s:if>
    <s:else>
        <div class="section-header">
            <h2>No Bookings Awaiting Confirmation</h2>
        </div>
    </s:else>

    <s:if test="%{ListOfConfirmedBookings.size!=0}">
    <div class="section-header">
        <h1>Confirmed Booking Details</h1>
    </div>
    <table class="table">
        <tr>
            <th>Customer Name</th>
            <th>Last Name</th>
            <th>Phone Number</th>
            <th>Email</th>
            <th>D.O.B</th>
            <th>Adults #</th>
            <th>Children #</th>
            <th>Booking Time</th>
            <th>Booking Date</th>
        </tr>

        <s:iterator value="listOfConfirmedUsers" status="count" var="confirmedBookings" step="1">
                <tr>
                    <td><s:property value="firstName" /></td>
                    <td><s:property value="lastName" /></td>
                    <td><s:property value="phoneNumber"/></td>
                    <td><s:property value="email" /></td>
                    <td><s:property value="dateOfBirth" /></td>
                    <td><s:property value="listOfConfirmedBookings[#count.index].numberofadults "/></td>
                    <td><s:property value="listOfConfirmedBookings[#count.index].numberofchildren "/></td>
                    <td><s:property value="listOfConfirmedBookings[#count.index].starttime "/></td>
                    <td><s:property value="listOfConfirmedBookings[#count.index].date "/></td>

                </tr>

        </s:iterator>


</table>
    </s:if>
    <s:else>
    <div class="section-header">
        <h2>No Confirmed Bookings</h2>
    </div>
    </s:else>
</div>