<%--
  Created by IntelliJ IDEA.
  User: blake
  Date: 9/05/2017
  Time: 8:05 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/Home.css"/>

<!-- Link Swiper's CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/swiper.min.css">
<jsp:useBean id="PackageBean" class="NewcastleConnectionsPrototype.Group4.models.beans.PackageBean" scope="session" />
<jsp:useBean id="userBean" class="NewcastleConnectionsPrototype.Group4.models.beans.UserBean" scope="session" />
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%-- The main home page for the newcastleconnections website.
    Has the following elements:
    1. search to start building a package.
    2. image scroller to show upcoming events and important deals.
    3. calendar of the upcoming month showing events that will be available soon
 --%>
<head>
    <sx:head />
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">

    <script>

        $( function() {
            var today = new Date();

            $( "#startDate" ).datepicker({
                minDate: today,
                defaultDate: today,
                dateFormat:"dd-mm-yy"

            });
            $( "#endDate" ).datepicker({
                minDate: today,
                defaultDate: today,
                dateFormat:"dd-mm-yy"
            });
        } );
    </script>
</head>

<div id="home-background-image">
    <div class="container home-search-box-container">
        <div class="card home-search-box">
            <div class="card-header card-title home-search-box-title">
                <h3>Experience Newcastle</h3>
            </div>
            <div class="card-block home-search-box-body">
                <form id="buildPackage"  name="buildPackage" action="/Prototype/buildPackage.action" method="post">
                    <%--<s:form action="buildPackage">
                            <sx:datetimepicker name="startDate" label="Event End Date"
                                               displayFormat="dd-MM-yyyy" value="%{'today'}" />
                            <sx:datetimepicker name="endDate" label="Holiday End Date"
                                               displayFormat="dd-MM-yyyy" value="%{'today'}" />--%>
                    <div class="form-group row">
                        <label for="startDate" class="col-sm-4 col-form-label">Start Date:</label>
                        <div class="col-sm-7">
                            <input type="text" name="startDate" id="startDate" class="form-control" value="<jsp:getProperty name = "PackageBean" property = "date1"/>">
                        </div>
                        <label for="startDate"><i class="fa fa-calendar pt-2" aria-hidden="true"></i></label>
                    </div>

                    <div class="form-group row">
                        <label for="endDate" class="col-sm-4 col-form-label">End Date:</label>
                        <div class="col-sm-7">
                            <input type="text" id="endDate" name="endDate" class="form-control" value="<jsp:getProperty name = "PackageBean" property = "date2"/>">
                        </div>
                        <label for="endDate"><i class="fa fa-calendar pt-2" aria-hidden="true"></i></label>
                    </div>

                    <div class="form-group row">
                        <label for="numberOfAdults" class="col-sm-4 col-form-label">Adults (18+):</label>
                        <div class="col-sm-7">
                            <input type="number" name="numberOfAdults" class="form-control" min="0" id="numberOfAdults" value="2">
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="numberOfChildren" class="col-sm-4 col-form-label">Children:</label>
                        <div class="col-sm-7">
                            <input type="number" name="numberOfChildren" class="form-control" min="0" id="numberOfChildren" value="0">
                        </div>
                    </div>

                    <%--<s:textfield type="number" label="Adults (18+)" min="0" name="numberOfAdults" value="1"></s:textfield>
                        <s:textfield type="number" label="Children" min="0" name="numberOfChildren" value="0"></s:textfield>--%>

                    <div class="form-group row mb-0">
                        <div class="offset-6">
                            <button type="submit" class="btn btn-success">Build Your Package</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <div class="section-header">
        <h1>What's on in Newcastle</h1>
    </div>
</div>

    <div class="container">
        <div class="card-deck mb-4">
<s:iterator value="dealModel" status="deals" step="1" begin="0" end="2">
            <div class="card home-deal-card">
    <s:iterator value="percentage" status="percentageCnt" begin="0" end="2" step="1">
        <s:if test="#deals.count == #percentageCnt.count">
                <div class="deal-corner-tab"><s:property value="percentage[#percentageCnt.index]" />%</div>
        </s:if>
    </s:iterator>
                <img class="card-img-top" src="${pageContext.request.contextPath}/resources/images/experiences/SkydiveNewcastle.jpg" alt="Card image cap">
                <div class="card-block home-deal-card-block">
                    <h4 class="card-title"><s:property value="dealtitle"/></h4>
        <s:iterator value="listOfUsers" status="bookingcnt" begin="0" end="2" step="1">
            <s:if test="#deals.count == #bookingcnt.count">
                    <h6 class="card-subtitle mb-2 text-muted"><s:property value="listOfUsers[#bookingcnt.index].businessname"/></h6>
            </s:if>
        </s:iterator>
                    <p class="card-text"><s:property value="dealdescription"/></p>
                    <div class="card-footer home-deal-card-footer">
                        <div class="row text-center mb-2">
                            <h4 class="card-text col-6 deal-price-old text-danger">$<s:property value="oldprice"/></h4>
                        </div>
                        <div class="row text-center">
                            <h4 class="card-text col-6 mt-2 deal-price text-success">$<s:property value="price"/></h4>
                            <a href="<s:url action='viewDealInformation.action'>
                                                <s:param name='dealID'><s:property value="dealid"/></s:param>
                                            </s:url>" class="btn btn-secondary mt-2">
                                View More Information<i class="pl-2 fa fa-caret-right" aria-hidden="true"></i></a></div>
                    </div>
                </div>
            </div>
</s:iterator>
        </div>
    </div>


<div class="container">
    <jsp:include page="../../fragments/Events.jsp"/>
</div>

</body>

