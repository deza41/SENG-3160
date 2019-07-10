<%--
  Created by IntelliJ IDEA.
  User: Tyrone
  Date: 25/05/2017
  Time: 6:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%-- Form to be filled out by a customer who is not logged in or creating an account.
 User details from here need to be confirmed before processing payment.
 --%>
<script>
    $( function() {

        $("#dateOfBirth").datepicker({
            dateFormat: "dd/mm/yy"
        })
    });
</script>
<head>
    <title>Guest Checkout</title>
</head>
<div class="container text-center">
    <h1>Guest Checkout</h1>
</div>
<form action="Prototype/checkOut.action" method="post" class="form-horizontal container">
    <fieldset>
    <div class="form-group row">
        <label class="col-md-4 control-label text-right" for="firstName">First Name</label>
        <div class="col-md-4">
            <input type="text" id="firstName" name="firstName" label="First Name" class="form-control input-md" required="">
        </div>
    </div>
    <div class="form-group row">
        <label class="col-md-4 control-label text-right" for="lastName">Last Name</label>
        <div class="col-md-4">
            <input type="text" id="lastName" name="lastName" label="Last name" class="form-control input-md" required="">
        </div>
    </div>
        <div class="form-group row">
            <label class="col-md-4 control-label text-right" for="phoneNumber">Phone Number</label>
            <div class="col-md-4">
                <input type="tel" id="phoneNumber" name="phoneNumber" label="Phone Number" class="form-control input-md" required="">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-md-4 control-label text-right" for="email">E-Mail</label>
            <div class="col-md-4">
                <input type="text" id="email" name="email" label="email" class="form-control input-md" required="">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-md-4 control-label text-right" for="postCode">Post Code</label>
            <div class="col-md-4">
                <input type="text" id="postCode" name="postCode" label="Post Code" class="form-control input-md" required="">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-md-4 control-label text-right" for="dateOfBirth">Date of Birth</label>
            <div class="col-md-4">
                <input type="text" id="dateOfBirth" name="dateOfBirth" class="form-control"/>
            </div>
            <label for="dateOfBirth"><i class="fa fa-calendar pt-2" aria-hidden="true"></i></label>
        </div>
        <div class="form-group row">
            <label class="col-md-4 control-label text-right" for="button"></label>
            <div class="col-md-4 text-center">
                <input type="submit" id="button" name="button" value="Submit" class="btn btn-success">
            </div>
        </div>
    </fieldset>
</form>
