<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: blake
  Date: 29/08/2017
  Time: 8:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Email Form</title>
</head>
<body>

<s:actionerror/>

<s:form name="emailForm" action="emailTest" method="post" theme="%{currentTheme}">
    <s:hidden name="rowindex"/>

    <table id="dealTable" width="350px" border="1">
        <tr>
            <s:textfield name="to" label="To"/>
        </tr>
        <tr>
            <s:textfield name="subject" label="Subject"/>
        </tr>
        <tr>
            <s:textfield name="body" label="Body"/>
        </tr>
    </table>

    <table>
        <tr>
            <td align="center">
                <s:submit value="Submit" align="center"/>
            </td>
        </tr>
    </table>
</s:form>

</body>
</html>
