<%--
  Created by IntelliJ IDEA.
  User: simon janmaat
  Date: 18/08/2017
  Time: 1:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="container text-center">
<script>
    $(document).ready(function() {
    $("form#ratingForm").submit(function(e)
    {
        e.preventDefault(); // prevent the default click action from being performed
        if ($("#ratingForm :radio:checked").length == 0) {
        $('#status').html("nothing checked");
        return false;
        } else {
        $('#status').html( 'You picked ' + $('input:radio[name=rating]:checked').val() );
        }
    });
    });
</script>

    <h2>Create a Review: </h2>
    <div>
        <s:form action="addReview" >
            <s:hidden name="businessID" value='%{businessID}'/>
            <s:textfield name="reviewTitle" label="Review Title"/>
            <s:textfield name="reviewContent" label="Details: " placeholder="post your comments in here...."/>
            <s:select label="Select a rating"
                      headerKey="-1" headerValue="Select Rating"
                      list="#{ '1': '0.5 star', '2':'1 star', '3':'1.5 stars', '4':'2 stars', '5': '2.5 stars', '6':'3 stars', '7':'3.5 stars', '8':'4 stars', '9':'4.5 stars', '10': '5 stars'}"
                      name="userRating"
                      value="6" />
            <s:submit />
        </s:form>
    </div>

</div>



<!-- the star system for later.

<div class="rating-stars">
<span class="empty-stars">
<span class="star">
<i class="fa fa-star-o fa-3x" aria-hidden="true"></i>
</span>
<span class="star">
<i class="fa fa-star-o fa-3x" aria-hidden="true"></i>
</span>
<span class="star">
<i class="fa fa-star-o fa-3x" aria-hidden="true"></i>
</span>
<span class="star">
<i class="fa fa-star-o fa-3x" aria-hidden="true"></i>
</span>
<span class="star">
<i class="fa fa-star-o fa-3x" aria-hidden="true"></i>
</span>
</span>
<span class="filled-stars" style="width:0%;">
<i class="fa fa-star" aria-hidden="true"></i>
<i class="fa fa-star" aria-hidden="true"></i>
<i class="fa fa-star" aria-hidden="true"></i>
<i class="fa fa-star" aria-hidden="true"></i>
<i class="fa fa-star" aria-hidden="true"></i>
</span>
</div>-->
