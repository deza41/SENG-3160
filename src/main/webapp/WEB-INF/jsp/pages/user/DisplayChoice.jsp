<%--
  Created by IntelliJ IDEA.
  User: Tyrone
  Date: 29/08/2017
  Time: 3:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="container text-center">

    <h2>View Contact Details</h2>
    <div class="row justify-content-center">
        <div class="col-6  text-center mt-4">
            <s:form action="DisplayBusinessAccount" >
                <input class="btn btn-block btn-primary" type="submit" value="Business">
            </s:form>
        </div>
    </div>
    <div class="row justify-content-center mb-4">
        <div class="col-6 text-center mt-4">
            <s:form  action="DisplayUserAccount" >
                <input class="btn btn-block btn-xl btn-primary" type="submit" value="User">

            </s:form>
        </div>
    </div>

</div>