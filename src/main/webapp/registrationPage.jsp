<%--@elvariable id="message" type=""--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" href="styles/loginStyle.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@200&display=swap" rel="stylesheet">
</head>
<body>
<main>
    <div class="main-title">
        <h1>
            ЗАРЕГИСТРИРОВАТЬСЯ
        </h1>
    </div>
    <form action="registration" method="post">
        <div class="field">
            <label for="login">Логин :</label>
            <input type="text" placeholder="Введите ваш логин..." id="login" name="login">
        </div>

        <div class="field">
            <label for="password">Пароль :</label>
            <input type="password" placeholder="Введите ваш пароль..." id="password" name="password">
        </div>

        <div class="field">
            <label for="email">E-mail :</label>
            <input type="email" placeholder="Введите ваш email..." id="email" name="email">
        </div>

        <div class="form-submit-button">
            <button type="submit" class="register-button">
                ЗАРЕГИСТРИРОВАТЬСЯ
            </button>
        </div>
    </form>
    <button class="move-back-button" onclick="goToComeIn(event)">
        У меня уже есть аккаунт
    </button>
    <div>
        <h2>
            <c:if test="${message != null}">
                <p>
                        ${message}
                </p>
            </c:if>
        </h2>
    </div>
</main>
<script rel="script" src="scripts/login.js"></script>
</body>
</html>
