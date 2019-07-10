<%--
  Created by IntelliJ IDEA.
  User: Simon
  Date: 29/08/2017
  Time: 2:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/TrackYourOrder.css"/>

<%-- Displays list of orders for a user and shows whether they are pending, confirmed or denied.
--%>

<style>
    table {
        font-family: arial, sans-serif;
        border-collapse: collapse;
        width: 100%;
    }

    td, th {
        border: 1px solid #dddddd;
        text-align: left;
        padding: 8px;
    }

    tr:nth-child(even) {
        background-color: #dddddd;
    }
</style>
<div class="container">
    <div class="section-header">
        <h1>Your Packages</h1>
    </div>
    <s:iterator value="listOfPackages" status="cnt" var="BusinessModel" step="1">
            <s:if test="%{ListOfListOfDealBookings[#cnt.index].size!=0}">
                <div class="section-header">
                    <h3>Package</h3>
                </div>
                <table>
                <tr>
                    <td>Business Name</td>
                    <td>Business Phone Number</td>
                    <td>Business Email</td>
                    <td>Booking Date</td>
                    <td>Booking Time</td>
                    <td>Booking Status</td>
                </tr>
            <s:iterator value="ListOfListOfbusiness[#cnt.index]" status="dealscnt" step="1">
                <tr>
                    <td><s:property value="businessname"/></td>
                    <td><s:property value="phonenumber"/></td>
                    <td><s:property value="email"/></td>
                <s:iterator value="ListOfListOfDealBookings[#cnt.index]" status="bookingcnt" step="1">
                    <s:if test="#dealscnt.index == #bookingcnt.index">
                        <s:set var="bstatus" value="bookingstatus" />

                        <td><s:property value="date"/></td>
                        <td><s:property value="starttime"/></td>

                            <s:if test="%{#bstatus==-1}">
                                <td class="table-danger">Declined
                            </s:if>
                            <s:if test="%{#bstatus==0}">
                                <td class="table-warning">Pending
                            </s:if>
                            <s:if test="%{#bstatus==1}">
                                <td class="table-success">Confirmed
                            </s:if>
                            <s:if test="%{#bstatus==-2}">
                                <td class="table-success">Voucher only
                            </s:if>
                        </td>
                    </s:if>
                    </s:iterator>
                </tr>
            </s:iterator>
                </table>
            </s:if>
            <s:if test="%{ListOfListOfEvents[#cnt.index].size !=0}">
                <div class="section-header">
                    <h2>Events</h2>
                </div>
                <table>
                <tr>
                    <td>Event Title</td>
                    <td>Start Date</td>
                    <td>End Date</td>
                </tr>
                <s:iterator value="ListOfListOfEvents[#cnt.index]" status="eventcnt" step="1">
                    <tr class ="table-success">
                        <td><s:property value="title"/></td>
                        <td><s:property value="startdate"/></td>
                        <td><s:property value="enddate"/></td>
                    </tr>
                </s:iterator>
                </table>
            </s:if>
    </s:iterator>
</div>

