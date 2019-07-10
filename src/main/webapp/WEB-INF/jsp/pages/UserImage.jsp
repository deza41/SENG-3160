<%--
  Created by IntelliJ IDEA.
  User: simon janmaat
  Date: 27/08/2017
  Time: 11:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <title>Upload User Image</title>
</head>

<body>
<h2>Struts2 File Upload & Save Example</h2>
<s:actionerror />
<s:form action="editUser" method="post" enctype="multipart/form-data">
    <s:textfield name="firstName" value="%{model[0].firstName}"/>

    <s:file name="userImage" label="Profile Picture" />
    <s:submit value="Upload" align="center" />
</s:form>
</body>
</html>