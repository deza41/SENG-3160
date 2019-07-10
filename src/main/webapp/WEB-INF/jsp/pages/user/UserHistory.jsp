<%--
  Created by IntelliJ IDEA.
  User: blake
  Date: 10/05/2017
  Time: 11:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- Displays past orders by a user so they can track their dealings. --%>

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




<div class="container-fluid">


    <div class="col-md-2"></div>
    <div class="container col-md-8 navbar navbar-default">
        <h1>Deal Status</h1>
        <table class="table table-hover" style="padding:0;margin:0;">
            <thead>
            <th>PackageID</th>
            <th>Date</th>
            <th></th>
            </thead>
            <tbody>
            <tr data-toggle="collapse" data-target="#accordion1" class="clickable">
                <td><a href="url">49305039</a></td>
                <td>26-05-17 to 28-05-17</td>
                <td><span class="glyphicon glyphicon-menu-down" aria-hidden="true"></span></td>
            </tr>
            <tr>
                <td colspan="3" style="padding:0;margin:0;">
                    <div id="accordion1" class="collapse">
                        <div class="container navbar navbar-default">
                            <table class="table table-hover responsive">
                                <tr>
                                    <th>Deal</th>
                                    <th>Date</th>
                                    <th>Description</th>
                                    <th>Quantity</th>
                                    <th>Price</th>
                                </tr>
                                <tr>
                                    <td>V8 Supercars weekend</td>
                                    <td>26-05-17 to 28-05-17</td>
                                    <td>Weekend away at the V8 supercars!</td>
                                    <td>N/A</td>
                                    <th>N/A</th>
                                </tr>
                                <tr>
                                    <td>Breakfast: Crown Plaza </td>
                                    <td>26-05-17 to 28-05-17</td>
                                    <td>This voucher is usable during the duration of your stay </td>
                                    <td>2</td>
                                    <th>$50</th>
                                </tr>
                                <tr>
                                    <td>Dinner: criniti's </td>
                                    <td>26-05-17 to 28-05-17</td>
                                    <td>This voucher is usable during the duration of your stay </td>
                                    <td>2</td>
                                    <th>$60</th>
                                </tr>
                                <tr>
                                    <td>Shuttle bus transfers: Airport Transfer</td>
                                    <td>26-05-17 and 28-05-17</td>
                                    <td>This voucher is usable during the duration of your stay </td>
                                    <td>2</td>
                                    <th>$59.95</th>
                                </tr>
                                <tr>
                                    <td>Crown Hotel: Dulux Suit </td>
                                    <td>26-05-17 to 28-05-17</td>
                                    <td>Two nights stay at the crown hotel in the Dulux Suit </td>
                                    <td>2</td>
                                    <th>$270.95</th>
                                </tr>
                            </table>
                            <p> </p>
                            <h2 align="Right">Total: $440.90</h2>
                            <p> </p>
                            <div align="Right">
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="col-md-2"></div>
</div>
