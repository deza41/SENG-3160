<%--
  Created by IntelliJ IDEA.
  User: Tyrone
  Date: 25/05/2017
  Time: 5:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:useBean id="userBean" class="NewcastleConnectionsPrototype.Group4.models.beans.UserBean" scope="session" />
<jsp:useBean id="PackageBean" class="NewcastleConnectionsPrototype.Group4.models.beans.PackageBean" scope="session"/>

<head>
    <title>Checkout</title>
    <style>
        .form-group {
        }

        .jp-card-container {
            margin-bottom: 1.5rem !important;
        }
    </style>
</head>

<%--<script>

    $('#payment-card').card({
        // a selector or DOM element for the container
        // where you want the card to appear
        container: '.card-wrapper' // *required*

        // all of the other options from above
    });
</script>
--%>

<div class="col order-progress-image-wrapper">
    <img src="${pageContext.request.contextPath}/resources/images/Progress4.png" class="rounded" alt="progress" width="1000" height="94">
</div>

<div class="container text-center">
    <div class="section-header">
    <h1>You're just one step away...</h1>
    </div>
        <div id="payment-card" class="card">
            <style>
                .panel-title {display: inline;font-weight: bold;  }
                .btn {width: 30%;}
            </style>

            <div class="form-container active">
                <div class="panel panel-default">
                    <div class="card-header">
                        <h3 class="panel-title">
                            Payment Details
                        </h3>
                    </div>
                </div>
            </div>

            <div class="demo-container ">
            <div class="card-wrapper mt-4"></div>

                <form id="card-form" class="form-group" action="Prototype/payment.action" method="post">
                <div>
                    <div class="row">
                        <div class="col-sm-4"></div>
                        <div class="col-sm-6 col-md-4">
                                <div class="panel-body">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <input type="tel" class="form-control" name="number" placeholder="Card Number" required autofocus />
                                            <span class="input-group-addon"><i class="fa fa-lock" aria-hidden="true"></i></span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="controls">
                                            <input type="text" class="form-control" name="name" placeholder="Full Name" required autofocus />
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-5 col-md-5">
                                            <div class="form-group">
                                                <div class="col-xs-6 col-lg-12">
                                                    <input type="tel" class="form-control" name="expiry" placeholder="MM/YY" required /></div>
                                                </div>
                                        </div>
                                        <div class="col-xs-5 col-md-5 pull-right">
                                            <div class="form-group">
                                                <input type="text" class="form-control" name="cvc" placeholder="CVC" required />
                                            </div>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                            <div class="col-sm-3"></div>
                            </div>
                    <div class="section-header">
                        <h4>Final Cost: $<s:property value="#session.PackageBean.totalCost"/></h4>
                            </div>
                            <div class = "mt-1">
                                <input type="submit" value="Pay" class="btn btn-success btn-lg">
                            </div>
                        </div>
                </form>
            </div>
            </div>
    </div>

            <script src="${pageContext.request.contextPath}/resources/js/jquery.card.js"></script>
            <script>
                $('#card-form').card({
                    container: '.card-wrapper'
                });
            </script>


