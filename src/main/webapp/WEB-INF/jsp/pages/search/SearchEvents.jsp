<%--
  Created by IntelliJ IDEA.
  User: simon janmaat
  Date: 17/08/2017
  Time: 9:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/Search.css"/>
<head>
    <title>Events</title>
</head>
<script>
    $( init );

    function DisplayPopup(el, message){
        $('<div class="card alert-popup"><div class="card-block">' + message + '</div></div>').insertAfter(el)
            .delay(30000)
            .fadeOut(function() {
                $(this).remove();
            });
    };

    function init() {

        $(".search-result-button-add-to-package").on("click",function(){
            $.ajax({
                type: 'POST',
                url:'addEventToCart.action',
                dataType: 'json',
                data: { eventID : $(this).attr("value")},
                success: function(data){
                    DisplayPopup($(this), "Event added.");
                    console.log(JSON.stringify(data));
                },
                error: function(data){
                    alert("Failed to add event");
                }
            });
        });
    }
</script>

<div class="col order-progress-image-wrapper">
    <img src="${pageContext.request.contextPath}/resources/images/Progress1.png" class="rounded" alt="progress" width="1000" height="94">
</div>

<div class="container">
    <div class="section-header">
        <h1>Experience Events</h1>
    </div>
</div>

<div class="container">
    <div class="card-columns">
        <s:iterator value="listOfEvents" status="cnt" var="EventsModel" step="1">
        <div class="card event-card">
            <div class="event-corner-tab">-32%</div>
            <img class="card-img-top event-card-img img-fluid" src="${pageContext.request.contextPath}/resources/images/experiences/SkydiveNewcastle.jpg" alt="Card image cap">
            <div class="card-block event-deal-card-block">
                <h4 class="card-title"><s:property value="title" /></h4>
                <h6 class="card-subtitle mb-2 text-muted"><s:property value="title"/></h6>
                <p class="card-text event-card-text"><s:property value="eventdescription"/></p>
                <div class="card-footer event-deal-card-footer">
                    <div class="row text-center mb-2">
                        <h4 class="card-text col-5 pt-1 deal-price-old text-danger">$<s:property value="oldprice" /></h4>
                        <a href="<s:url action='viewEventInformation.action'>
                                                <s:param name='eventID'><s:property value="eventid"/></s:param>
                                            </s:url>" class="btn btn-info">
                            View<i class="pl-2 fa fa-caret-right" aria-hidden="true"></i></a>
                    </div>
                    <div class="row text-center">
                        <h4 class="card-text col-5 mt-2 deal-price text-success">$<s:property value="price" /></h4>
                        <a href="#" value="<s:property value='eventid'/>" class="search-result-button-add-to-package btn btn-success">
                            Add to package<i class="pl-2 fa fa-caret-right" aria-hidden="true"></i>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        </s:iterator>
    </div>
</div>

