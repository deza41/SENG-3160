<%--
  Created by IntelliJ IDEA.
  User: Simon
  Date: 17/08/2017
  Time: 11:23 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ShoppingCart.css"/>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:useBean id="PackageBean" class="NewcastleConnectionsPrototype.Group4.models.beans.PackageBean" scope="session"/>

<%--
    Shows the user all the items they have selected and what dates they have been set for.
    A user can then proceed to pay for their package.
--%>
<head>
    <title>Shopping Cart</title>
</head>
<div class="col order-progress-image-wrapper">
    <img src="${pageContext.request.contextPath}/resources/images/Progress3.png" class="rounded" alt="progress" width="1000" height="94">
</div>

<div class="container">
    <div class="section-header">
        <h1>Deals</h1>
    </div>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Deal Title</th>
                <th>Deal Description</th>
                <th>List Price</th>
                <th>Deal Cost</th>
                <th>Savings</th>
                <th>Quantity</th>
            </tr>
        </thead>
        <tbody>
            <s:iterator value="#session.PackageBean.dealCart" var="iterDeals" step="1">
            <tr>
                <s:form theme="simple" action="removeDealFromCart" method="post">
                    <s:hidden name="dealID"  value="%{dealID}"/>
                    <td><s:property value="dealTitle"/></td>
                    <td><s:property value="dealDescription"/></td>
                    <td>$<s:property value="oldPrice"/></td>
                    <td>$<s:property value="price"/></td>
                    <td>$<s:property value="savingsPercentage"/></td>
                    <td><s:property value="quantity"/></td>
                    <td style="background: white; width: 205px;" class="text-center p-0 align-middle">
                        <s:textfield type="number" min="0" max="%{quantity}" name="removeQ" value="1"></s:textfield>
                        <s:submit cssClass="btn btn-danger" value="Remove"/>
                    </td>
                </s:form>
            </tr>
            </s:iterator>
        </tbody>
    </table>
    <div class="section-header">
        <h1>Events</h1>
    </div>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Event Title</th>
                <th>Event Start Date</th>
                <th>List Price</th>
                <th>Deal Cost</th>
                <th>Savings</th>
                <th>Quantity</th>
            </tr>
        </thead>
        <tbody>
            <s:iterator value="#session.PackageBean.eventsCart" var="iterEvents" step="1">
            <tr>
                <s:form theme="simple" action="removeEventFromCart" method="post">
                    <s:hidden name="eventID"  value="%{eventID}"/>
                    <td><s:property value="eventTitle"/></td>
                    <td><s:property value="startDate"/></td>
                    <td>$<s:property value="oldPrice"/></td>
                    <td>$<s:property value="price"/></td>
                    <td>$<s:property value="savingsPercentage"/></td>
                    <td><s:property value="quantity"/></td>
                    <td style="background: white; width: 205px;" class="text-center p-0 align-middle">
                        <s:textfield type="number" min="0" max="%{quantity}" name="removeQ" value="1"></s:textfield>
                        <s:submit cssClass="btn btn-danger" value="Remove"/>
                    </td>
                </s:form>
            </tr>
            </s:iterator>
        </tbody>
    </table>

    <div class="section-header">
        <h1>Bookings</h1>
    </div>

    <table class="table table-striped">
        <thead>
            <tr>
                <td>Business</td>
                <td>Deal</td>
                <td>List Price</td>
                <td>Your Cost</td>
                <td>Savings</td>
                <td>Start Time</td>
                <td>Booking Date</td>
            </tr>
        </thead>
        <tbody>
        <s:iterator value="#session.PackageBean.bookings" var="iterBookings" step="1">
            <tr>
                <s:form theme="simple" action="removeBookingFromCart" method="post">
                    <s:hidden name="localBookingID"  value="%{localBookingID}"/>
                    <td><s:property value="deal.business.businessName"/></td>
                    <td><s:property value="deal.dealTitle"/></td>
                    <td>$<s:property value="deal.oldPrice"/></td>
                    <td>$<s:property value="deal.price"/></td>
                    <td>$<s:property value="deal.savingsPercentage"/></td>
                    <td><s:property value="startTime"/></td>
                    <td><s:property value="daysDate"/></td>
                    <td style="background: white; width: 205px;" class="text-center p-0 align-middle"><s:submit cssClass="btn btn-danger" value="Remove"/></td>
                </s:form>
            </tr>
        </s:iterator>
        </tbody>
    </table>

    <div class="mt-4 row text-left">
        <div class="col-2 ml-auto">
            <h5>Sub Total:</h5>
            <h5>Discounts:</h5>
            <h5>Price:</h5>
            <p><small><em>All prices include GST</em></small></p>
        </div>
        <div class="col-2">
            <h5>$<s:property value="#session.PackageBean.fullCost"/></h5>
            <h5>$<s:property value="#session.PackageBean.discountAmount"/></h5>
            <h5>$<s:property value="#session.PackageBean.totalCost"/></h5>
        </div>
    </div>
    <div class="submit-button-container mt-1 mb-4 col text-center">
        <a type="button" class="btn btn-success btn-lg" href="
        <s:url action='checkOut.action'></s:url>">Purchase</a>
    </div>

</div>