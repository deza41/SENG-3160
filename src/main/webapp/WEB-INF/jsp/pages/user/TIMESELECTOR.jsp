<%--
  Created by IntelliJ IDEA.
  User: Tyrone
  Date: 27/08/2017
  Time: 10:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/jquery.datetimepicker.css"/>
<style type="text/css">

    .custom-date-style {
        background-color: red !important;
    }

    .input{
    }
    .input-wide{
        width: 500px;
    }

</style>


<h3>TimePicker</h3>
<input type="text" id="datetimepicker1"/><br><br>




<script src="${pageContext.request.contextPath}/resources/js/jquery.datetimepicker.full.js"></script>
<script>
    $('#datetimepicker1').datetimepicker({
        datepicker:false,
        format:'H:i',
        step:5
    });


</script>
