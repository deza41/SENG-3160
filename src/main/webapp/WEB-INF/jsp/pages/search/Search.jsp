<%--
  Created by IntelliJ IDEA.
  User: Simon
  Date: 14/08/2017
  Time: 21:58 PM
  To change this template use File | Settings | File Templates.
--%>

<%-- General search function that shows available businesses and deals that fit a users specified categories and
 filters. Search results are expandable with further information and can be added to cart directly or deals selected. --%>



<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/Search.css"/>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
        $('.search-button-read-more').click(function () {
            $(this).parent('.search-result').find('.search-result-expand-area').toggle('blind', {}, 500, null);
        });

        $('.tablinks').click(function () {
            $(this).parent('.search-result').find('.tabcontent').toggle('blind', {}, 500, null);
        });

        $(".search-result-button-add-to-package").on("click",function(){


            DisplayPopup($(this), "Deal added.");

            console.log("Hey");
            $.ajax({
                type: 'POST',
                url:'addDealToCart.action',
                dataType: 'json',
                data: { dealID : $(this).attr("value")},
                success: function(data){
                    console.log(JSON.stringify(data));
                },
                error: function(data){
                    alert("Failed to add deal");
                }
            });
        });

        $('.search-like-business').each(function(){
            if($(this).data("like-status") == 1) {
                $(this).find('.fa').toggleClass('fa-thumbs-up');
                $(this).find('.fa').toggleClass('fa-thumbs-o-up');
            }
        });


        $('.search-like-business').on("click",function(){
            console.log($(this).data("like-status"));
            var likeBit = $(this).data("like-status") == 1 ? 0 : 1;

            var dataString = '{"likedBusiness":' + $(this).data("business-id") + "," + '"liked":' + likeBit + '}';

            $.ajax({
                type : "POST",
                url : "likeBusiness.action",
                dataType : "json",
                data: dataString,
                contentType: "application/json; charset=utf-8",
                async: true,
                success : function(result) {
                },
                complete: function (msg,a,b) {
                },
                error : function(msg,a,b){
                    console.log('error:'+msg);
                }
            });

            $(this).data("like-status", likeBit);
            $(this).find('.fa').toggleClass('fa-thumbs-up');
            $(this).find('.fa').toggleClass('fa-thumbs-o-up');
        });
    }
    $( function() {
        $( ".tabs" ).tabs();
    } );

</script>
<title>Search</title>

</head>
<div class="col order-progress-image-wrapper">
    <img src="${pageContext.request.contextPath}/resources/images/Progress1.png" class="rounded" alt="progress" width="1000" height="94">
</div>
<div class="container">
    <div class="section-header">
        <h1><s:property value="pageHeading"/></h1>
    </div>
</div>
<div class="container">
    <s:iterator value="listOfBusinesses" status="cnt" var="BusinessModel" step="1">
        <div class="card search-result">
            <s:set var="businessLiked" value="listOfLikedBusiness[#cnt.index]" />
            <s:if test="businessLiked">
                <a class="search-like-business" href="#" data-business-id='<s:property value="businessid"/>' data-like-status='1'>
            </s:if><s:else>
                <a class="search-like-business" href="#" data-business-id='<s:property value="businessid"/>' data-like-status='0'>
            </s:else>
                <s:if test="foo">
                    <i class="fa fa-thumbs-up" aria-hidden="true"></i>
                </s:if><s:else>
                    <i class="fa fa-thumbs-o-up" aria-hidden="true"></i>
                </s:else>
            </a>

            <div class="row no-gutters search-result-content">
                <div class="col-3 search-result-left">
                        <%--<img class="businessImage" src="${pageContext.request.contextPath}<s:property value="profileimageurl"/>" alt="<s:property value="businessname"/> image"/>--%>
                    <img class="businessImage" src="${pageContext.request.contextPath}/resources/images/testdealimage.jpg" alt="<s:property value="businessname"/> image"/>
                </div>
                <div class="col ml-3 mr-auto">
                    <h5><s:property value="businessname"/></h5>
                    <s:if test="%{rating > 0}">
                        <h5><span class="stars-container stars-<s:property value="rating"/>"></span></h5>
                    </s:if>
                    <s:elseif test="%{rating == 0}">
                        <a href="<s:url value='createReview.action?businessID='/><s:property value="businessid"/>" class="btn btn-sm btn-secondary">Leave a review</a>
                    </s:elseif>
                    <p>
                    <s:property value="businessdescription"/>
                    </p>
                </div>
                <div class="col-3">
                    <s:iterator value="listOfListOfDeals[#cnt.index]" var="DealsModel" status="dealStatus">
                        <s:if test="#dealStatus.count == 1">
                        <div class="search-result-deal row h-100">
                                <div class="col align-self-center text-center">
                                    <h4 ><s:property value="dealtitle"/></h4>
                                    <h4 class="deal-price-old text-danger">$<s:property value="oldprice"/></h4>
                                    <h4 class="deal-price text-success"><s:property value="price"/></h4>
                                    <a href="#" value="<s:property value='dealid'/>" class="search-result-button-add-to-package btn btn-success">Add to package<i class="pl-2 fa fa-caret-right" aria-hidden="true"></i></a>
                                </div>
                        </div>
                        </s:if>
                    </s:iterator>
                </div>
            </div>
            <button type="button" class="search-button-read-more btn btn-primary">Read more</button>
            <div class="search-result-expand-area">
                <div class="tabs">
                    <ul>
                        <%--<li><a href="#tabs-<s:property value="%{#cnt.index}" />-images">Images</a></li>--%>
                        <li><a href="#tabs-<s:property value="%{#cnt.index}" />-businessdets">Business Details</a></li>
                        <li><a href="#tabs-<s:property value="%{#cnt.index}" />-reviews">Reviews</a></li>
                        <li><a href="#tabs-<s:property value="%{#cnt.index}" />-deals">Deals</a></li>
                    </ul>
                    <div id="tabs-<s:property value="%{#cnt.index}" />-images">
                        <p><s:property value="businessname" /></p>
                         </div>
                    <div id="tabs-<s:property value="%{#cnt.index}" />-businessdets">
                        <div class="row">
                            <div class="card col">
                                <div class="card-block">
                                    <h4 class="card-title">About <s:property value="businessname" /></h4>
                                    <h6 class="card-subtitle mb-2 text-muted">Hours: <s:property value="businessopen" /> - <s:property value="businessclose" /></h6>
                                    <p class="card-text"><s:property value="businessdescription" /></p>
                                </div>
                            </div>
                            <div class="card col">
                                <div class="card-block">
                                    <h4 class="card-title">Details</h4>
                                    <h6 class="card-subtitle m-2">Address</h6>
                                        <div class="address-listing pl-2 m-2">
                                            <s:property value="number" /> <s:property value="street" />
                                            <br />
                                            <s:property value="suburb" />
                                            <br />
                                            <s:property value="postcode" />
                                        </div>
                                    <h6 class="card-subtitle m-2">Contact</h6>
                                    <div class="contact-listing pl-2 m-2">
                                        <s:property value="phonenumber" />
                                        <br />
                                        <s:property value="email" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="tabs-<s:property value="%{#cnt.index}" />-reviews">
                        <s:iterator value="listOfListOfReviews[#cnt.index]" var="Reviews" step="1" status="reviewCount">
                            <div class="card">
                                <div class="card-block">
                                    <h4 class="card-title">
                                        <span class="stars-container stars-<s:property value="rating"/>"></span>
                                        <span class="review-title-user pl-4"><s:property value="title"/></span>

                                    </h4>
                                    <h6 class="card-subtitle mb-2 text-muted"><s:property value="date"/> - <s:property value="firstname"/></h6>
                                    <p class="card-text"><s:property value="details"/></p>
                                </div>
                            </div>
                        </s:iterator>
                        <a href="<s:url value='createReview.action?businessID='/><s:property value="businessid"/>" class="btn btn-secondary mt-2">Leave a review</a>
                    </div>
                    <div id="tabs-<s:property value="%{#cnt.index}" />-deals">
                        <div class="row">
                            <s:iterator value="listOfListOfDeals[#cnt.index]" var="DealsModel" step="1">
                                <div class="search-result-deal col-3 mt-4 h-100">
                                    <div class="col align-self-center text-center">
                                        <h4 ><s:property value="dealtitle"/></h4>
                                        <h4 class="deal-price-old text-danger">$<s:property value="oldprice"/></h4>
                                        <h4 class="deal-price text-success">$<s:property value="price"/></h4>
                                        <a href="#" value="<s:property value='dealid'/>" class="search-result-button-add-to-package btn btn-success">Add to package<i class="pl-2 fa fa-caret-right" aria-hidden="true"></i></a>
                                        <a href="<s:url action='viewDealInformation.action'>
                                                <s:param name='dealID'><s:property value="dealid"/></s:param>
                                            </s:url>" class="btn btn-secondary mt-2">
                                            View More Information<i class="pl-2 fa fa-caret-right" aria-hidden="true"></i></a>
                                    </div>
                                </div>
                            </s:iterator>
                        </div>
                     </div>
                </div>
            </div>
        </div>
    </s:iterator>
</div>