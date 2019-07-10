<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Struts 2 Upload Multiple File</title>
</head>

<body>

<s:form action="doUpload" enctype="multipart/form-data" >
    <s:file name="files" multiple="multiple" />
    <s:submit value="Upload files" />
</s:form>
</body>
</html>