<%--
  Created by IntelliJ IDEA.
  User: simon janmaat
  Date: 29/08/2017
  Time: 1:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<div class="container pt-4 pb-4 mb-4 mt-4">
    <div class="row mt-4 mb-4">
        <div class="col-md-5">
            <h2>Login:</h2>
            <div>
                <div>
                    <form class="form" role="form" method="post" action="Tlogin.action">
                        <div class="form-group">
                            <input type="text" class="form-control" id="username" name="username" placeholder="Username" required>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-block">Sign in</button>
                        </div>
                    </form>
                </div>

            </div>
        </div>
        <div class="col-md-2 trackorder-middle-column">
            <h2>OR</h2>
        </div>
        <div class="col-md-5">
            <h2>Tracking Number:</h2>
            <div>
                <div>
                    <form class="form" role="form" method="post" action="navToTrackOrder.action">
                        <div class="form-group">
                            <input type="text" class="form-control" id="trackingID" name="trackingID" placeholder="Tracking Number" required>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-block">Continue</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
