<%--
  Created by IntelliJ IDEA.
  User: blake
  Date: 29/08/2017
  Time: 10:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="s" uri="/struts-tags" %>

<head>
    <title>Statistics</title>
    <script>
        $( function() {
            $( "#tabs" ).tabs();
        } );
    </script>
</head>

<div class="container-fluid">

    <div class="col-md-2"></div>
    <div class="container col-md-8 navbar navbar-default">
        <div class="section-header mb-0">
            <h1>Statistics</h1>
        </div>

        <div id="tabs">
            <ul>
                <li><a href="#tabs-1">Deals</a></li>
                <li><a href="#tabs-2">Customer Engagement</a></li>
            </ul>
            <div id="tabs-1">

                <div class="section-header">
                    <h2>Most Viewed Deals</h2>
                </div>

                <table class="table table-hover responsive">
                    <tr>
                        <th>Image</th>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Valid Duration</th>
                        <th>Price</th>
                        <th>Value</th>
                        <th>Total Views</th>
                    </tr>


                    <s:iterator value="mostViewedDeals" var="Deals">
                        <tr>
                            <td><img src='<s:property value="dealimageurl"/>' alt="Deal Image"/></td>

                            <td><s:property value="dealtitle"/></td>

                            <td><s:property value="dealdescription"/></td>

                            <td><s:property value="startdate"/></td>

                            <td><s:property value="enddate"/></td>


                            <td><s:property value="validduration"/> days</td>

                            <td><s:property value="price"/></td>

                            <td><s:property value="oldprice"/></td>

                            <td><s:property value="totalviews"/></td>
                        </tr>

                    </s:iterator>
                </table>


                <div class="section-header">
                    <h2>Most Purchased Deals</h2>
                </div>

                <table class="table table-hover responsive">
                    <tr>
                        <th>Image</th>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Valid Duration</th>
                        <th>Price</th>
                        <th>Value</th>
                        <th>Total Purchased</th>
                    </tr>

                    <s:iterator value="mostPurchasedDeals" var="Deals">
                        <tr>
                            <td><img src='<s:property value="dealimageurl"/>' alt="Deal Image"/></td>

                            <td><s:property value="dealtitle"/></td>


                            <td><s:property value="dealdescription"/></td>


                            <td><s:property value="startdate"/></td>


                            <td><s:property value="enddate"/></td>


                            <td><s:property value="validduration"/> days</td>

                            <td><s:property value="price"/></td>

                            <td><s:property value="oldprice"/></td>

                            <td><s:property value="totalpurchased"/></td>

                        </tr>
                    </s:iterator>
                </table>
            </div>
            <div id="tabs-2">
                <div class="section-header">
                    <h2>Most Liked Businesses</h2>
                </div>

                <table class="table table-hover responsive">
                    <tr>
                        <th>Business Name</th>
                        <th>Number of Likes</th>
                    </tr>

                    <s:iterator value="mostLikedBusinessPairModelList" status="cnt" var="mostLikedBusiness" step="1">
                        <tr>
                            <td><s:property value="name"/></td>
                            <td><s:property value="numLikes"/></td>
                        </tr>

                    </s:iterator>

                </table>
            </div>
        </div>
    </div>
    <div class="col-md-2"></div>

</div><!-- /.navbar-collapse -->
</div><!-- /.container-fluid -->
</nav>

