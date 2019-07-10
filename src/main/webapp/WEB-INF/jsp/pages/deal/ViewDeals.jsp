<%--
  Created by IntelliJ IDEA.
  User: Tyrone
  Date: 25/05/2017
  Time: 4:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:useBean id="userBean" class="NewcastleConnectionsPrototype.Group4.models.beans.UserBean" scope="session" />
<jsp:useBean id="searchBean" class="NewcastleConnectionsPrototype.Group4.models.beans.PackageBean" scope="session" />

<%-- Shows deals a user has selected. --%>

<style>
    table {
        font-family: arial, sans-serif;
        border-collapse: collapse;
        width: 100%;
    }

    td, th {
        border: 1px solid #dddddd;
        text-align: left;
        padding: 8px;
    }

    tr:nth-child(even) {
        background-color: #dddddd;
    }
</style>

<div class="container-fluid">

    <div class="col-md-2"></div>
    <div class="container col-md-8 navbar navbar-default">
        <h1>Active Deals</h1>
        <table class="table table-hover responsive">
            <tr>
                <th>Tittle</th>
                <th>Description</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            <s:iterator value="model" var="Deals">
                <s:if test="%{activedeal == 1}">
                <tr>
                    <td><s:property value="dealtitle"/></td>
                    <td><s:property value="dealdescription"/></td>
                    <td>Active</td>
                    <td>
                        <%-- button navigating to a page for editing the deal matching dealid --%>
                        <a type="button" class="btn btn-warning" href="
                        <s:url action='editDeal.action'>
                            <s:param name='targetDealID'><s:property value="dealid"/></s:param>
                        </s:url>">Edit</a>
                        <%-- button navigating to a page for discontinuing the deal matching dealid --%>
                        <a type="button" class="btn btn-danger" href="
                        <s:url action='discontinueDeal.action'>
                            <s:param name='targetDealID'><s:property value="dealid"/></s:param>
                        </s:url>">Discontinue</a>
                    </td>
                </tr>
                </s:if>
            </s:iterator>
        </table>
    </div>
    <div class="col-md-2"></div>

    <div class="col-md-2"></div>
    <div class="container col-md-8 navbar navbar-default">
        <h1>Inactive Deals</h1>
        <table class="table table-hover responsive">
            <tr>
                <th>Tittle</th>
                <th>Description</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            <s:iterator value="model" var="Deals">
                <s:if test="%{activedeal == 0}">
                    <tr>
                        <td><s:property value="dealtitle"/></td>
                        <td><s:property value="dealdescription"/></td>
                        <td>Inactive</td>
                        <td>
                                <%-- button navigating to a page for editing the deal matching dealid --%>
                            <a type="button" class="btn btn-success" href="
                        <s:url action='editDeal.action'>
                            <s:param name='targetDealID'><s:property value="dealid"/></s:param>
                        </s:url>">Reactivate</a>
                        </td>
                    </tr>
                </s:if>
            </s:iterator>
        </table>
    </div>
    <div class="col-md-2"></div>




</div><!-- /.navbar-collapse -->
</div><!-- /.container-fluid -->
</nav>
