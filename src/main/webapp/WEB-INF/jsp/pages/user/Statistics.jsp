<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Rhys
  Date: 29/08/2017
  Time: 2:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ShoppingCart.css"/>
<jsp:useBean id="userBean" class="NewcastleConnectionsPrototype.Group4.models.beans.UserBean" scope="session" />

<html>
<head>
    <title>Business Statistics</title>
</head>
<body>
    <div class="container text-center">
        <h3>Business Statistics</h3>
        <table>
            <tr>
                <th>Current Pending Deals:</th>
                <td><s:property value="dealRecordModel.dealTitle"></s:property></td>
            </tr>
            <tr>
                <th>Total Accepted Bookings</th>
                <td>50</td>
            </tr>
            <tr>
                <th>Deals Viewed</th>
                <td></td>
            </tr>
            <tr>
                <th>Deals Purchased</th>
                <td></td>
            </tr>
        </table>

        <style>
            .demo-container {
                width: 100%;
                max-width: 350px;
                margin: 50px auto;
            }
            form {
                margin: 30px;
            }
            input {
                width: 200px;
                margin: 10px auto;
                display: block;
            }
        </style>
        <div class="demo-container">
            <div class="card-wrapper"></div>

            <div class="form-container active">
                <form action="">
                    <input placeholder="Card number" type="tel" name="number">
                    <input placeholder="Full name" type="text" name="name">
                    <input placeholder="MM/YY" type="tel" name="expiry">
                    <input placeholder="CVC" type="number" name="cvc">
                </form>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/resources/js/jquery.card.js"></script>
        <script>
            $('form').card({
                form: document.querySelector('form'),
                container: '.card-wrapper'
            });
        </script>

    </div>
</body>
</html>
