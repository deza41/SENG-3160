<%--
  Created by IntelliJ IDEA.
  User: Tyrone
  Date: 15/08/2017
  Time: 12:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>File Upload</title>
</head>
<body>
<form action="uploadFile.action" method="post" enctype="multipart/form-data">
    <label for="myFile">Upload your file</label>
    <input type="file" name="myFile" />
    <input type="submit" value="Upload"/>
</form>
</body>
</html>