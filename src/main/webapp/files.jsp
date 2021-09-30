<%@ page import="java.util.Date" %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Files</title>
</head>
<body>
<h2>
    <%
        String today = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date());
        out.println (today);
    %>
</h2>
</body>
</html>
