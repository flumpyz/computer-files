<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Files</title>
    <link rel="stylesheet" href="styles/filesStyle.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@200&display=swap" rel="stylesheet">
</head>
<body>
<div class="top-line">
    <div>
        <h2>
            <%
                String today = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date());
                out.println(today);
            %>
        </h2>
    </div>
    <div>
        <button onclick="logOut(event)">
            <img src="images/logout.svg">
        </button>
    </div>
</div>
<h2>
    ${directory}
</h2>
<hr>
<table>
    <tr>
        <td>
            <img src="images/up.png" alt="" style="width: 20px; height: 20px;">
        </td>
        <td>
            <a href="" style="text-decoration: none;" onclick="comeBack(event)">Вверх</a>
        </td>
        <td></td>
        <td></td>
    </tr>
</table>
<table>
    <tr>
        <th></th>
        <th>Файл</th>
        <th>Размер</th>
        <th>Дата</th>
    </tr>
    <c:forEach var="file" items="${files}">
        <tr>
            <td>
                <c:if test="${file.isFile}">
                    <img src="images/file.png" alt="" style="width: 20px; height: 20px;">
                </c:if>
                <c:if test="${!file.isFile}">
                    <img src="images/dir.png" alt="" style="width: 20px; height: 20px;">
                </c:if>
            </td>
            <td style="width: 250px;">
                <c:if test="${file.isFile}">
                    <a style="text-decoration: none;" href="" onclick="saveFile(event)">${file.name}</a>
                </c:if>
                <c:if test="${!file.isFile}">
                    <a style="text-decoration: none;" href="" onclick="moveOn(event)">${file.name}/</a>
                </c:if>
            </td>
            <td style="width: 250px;">${file.size} B</td>
            <td style="width: 350px;">${file.formatLastModifiedDate}</td>
        </tr>
    </c:forEach>
</table>
<script rel="script" src="scripts/script.js"></script>
</body>
</html>
