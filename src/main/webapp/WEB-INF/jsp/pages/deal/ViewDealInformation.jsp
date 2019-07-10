<%--
  Created by IntelliJ IDEA.
  User: blake
  Date: 28/08/2017
  Time: 1:50 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:useBean id="userBean" class="NewcastleConnectionsPrototype.Group4.models.beans.UserBean" scope="session" />

<head>
    <script>
            $(".search-result-button-add-to-package").on("click",function(){
                $.ajax({
                    type: 'POST',
                    url:'addDealToCart.action',
                    dataType: 'json',
                    data: { dealId : $(this).attr("value")},
                    success: function(data){
                        alert("Deal added successfuly");
                        console.log(JSON.stringify(data));
                    },
                    error: function(data){
                        alert("Failed to add deal");
                    }
                });
            });

    </script>
</head>

<div class="container-fluid">

    <div class="col-md-2"></div>
    <div class="container col-md-8 navbar navbar-default">
        <h1>Deal Information</h1>

        <img src="F:\Programs\apache-tomcat-8.5.4\webapps\DMS\sarcasm.jpg"/>

        <s:iterator value="dealsRecordResult" var="Deals">
        <table class="table table-hover responsive">
            <tr>
                <td>Title</td>
                <td><s:property value="dealtitle"/></td>
            </tr>
            <tr>
                <td>Description</td>
                <td><s:property value="dealdescription"/></td>
            </tr>
            <tr>
                <td>Start Date</td>
                <td><s:property value="startdate"/></td>
            </tr>
            <tr>
                <td>End Date</td>
                <td><s:property value="enddate"/></td>
            </tr>
            <tr>
                <td>Valid Duration</td>
                <td><s:property value="validduration"/> days</td>
            </tr>
            <tr><td><img src='<s:property value="dealimageurl"/>' alt="Deal Image"/></td></tr>
            <tr>
                <td>Price</td>
                <td><s:property value="price"/></td>
            </tr>
            <tr>
                <td>Value</td>
                <td><s:property value="oldprice"/></td>
            </tr>
            <tr>
                <td>Savings</td>
                <td><s:property value="savings"/> (<s:property value="savingsPercentage"/>%)</td>
            </tr>
            <tr>
                <a value="<s:property value='dealid'/>" class="search-result-button-add-to-package btn btn-success">Add to package</a>
            </tr>
        </table>

        </s:iterator>
    </div>
    <div class="col-md-2"></div>

    <div class="col-md-2"></div>
    <div class="container col-md-8 navbar navbar-default">
        <h1>Business Information</h1>
        <s:iterator value="businessuserRecordResult" var="Businessuser">
        <table class="table table-hover responsive">
            <tr>
                <td>Business Name</td>
                <td><s:property value="businessname"/></td>
            </tr>
            <tr>
                <td>Rating</td>
                <td><s:property value="rating"/></td>
            </tr>
            <tr>
                <td>Location</td>
                <td>Suburb: <s:property value="suburb"/></td>
                <td>Street: <s:property value="street"/></td>
                <td>Number: <s:property value="number"/></td>
                <td>Unit: <s:property value="unit"/></td>
                <td>Post Code: <s:property value="postcode"/></td>
                <td>Latitude: <s:property value="latitude"/></td>
                <td>Longitude: <s:property value="longitude"/></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><s:property value="phonenumber"/></td>
            </tr>
            <tr>
                <td>Rating</td>
                <td><s:property value="rating"/></td>
            </tr>
            <tr>
                <td><img src='<s:property value="profileimageurl"/>' alt="Profile Image"/></td>
            </tr>

        </table>

        </s:iterator>
    </div>
    <div class="col-md-2"></div>




</div><!-- /.navbar-collapse -->
</div><!-- /.container-fluid -->
</nav>

