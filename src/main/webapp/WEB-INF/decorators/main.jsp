<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<!-- makes sure the beans are intialised anywhere when loading a page-->
<jsp:useBean id="PackageBean" class="NewcastleConnectionsPrototype.Group4.models.beans.PackageBean" scope="session" />
<jsp:useBean id="userBean" class="NewcastleConnectionsPrototype.Group4.models.beans.UserBean" scope="session" />

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"> <%-- tells IE to use latest rendering engine --%>
    <meta name="viewport" content="width=device-width, initial-scale=1"> <%-- set page width to size of device, set zoom level to 1 --%>
    <title><decorator:title default="Newcastle Connections"/></title>

    <!--icon for the page-->

    <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/resources/images/favicon-16x16.png">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery-ui.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery-ui-timepicker-addon.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/SiteTheme.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/Main.css"/>

    <!--Font Awesome style sheet link -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/font-awesome-4.7.0/css/font-awesome.min.css">

    <!-- jQuery and jQueryUI library -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui-timepicker-addon.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.card.js"></script>


    <!-- Latest compiled JavaScript -->
    <script src="https://npmcdn.com/tether@1.2.4/dist/js/tether.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>

    <decorator:head/>
</head>

<%-- To get a url without prepending the context path use --%>
<%-- <s:url value='/path/to/resource'/> --%>
</div>
<body id="page-base">
    <div class="container-fluid" id="page-base-container-fluid">

        <%--<jsp:include page="../jsp/fragments/Banner.jsp"/>--%>
        <!-- Header container -->
        <jsp:include page="../jsp/fragments/Navbar.jsp"/>

        <!-- Page content (gets 100% width of page) -->
        <decorator:body/>
    </div>

    <div id="footer" class="row no-gutters">
        <div class="col p-4">
            © Newcastle Connections 2017. All rights reserved.

            <div id="scroll-top">
                <a href="#"><i class="fa fa-arrow-up" aria-hidden="true"></i></a>
            </div>
        </div>
    </div>
</body>
</html>
