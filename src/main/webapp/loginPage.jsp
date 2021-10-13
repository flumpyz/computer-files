<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <div>
        <h1>
            ВОЙТИ
        </h1>
    </div>
    <label>Логин :
        <input type="text" placeholder="Введите ваш логин..." id="come_in_login_field">
    </label>
    <label>Пароль :
        <input type="password" placeholder="Введите ваш пароль..." id="come_in_password_field">
    </label>
    <input type="submit" class="come-in-button" value="ВОЙТИ" onclick="comeIn(event)">
    <button class="registration-button" onclick="goToRegistration(event)">
        <img src="images/registration.svg">
        Регистрация
    </button>
</main>
<script rel="script" src="scripts/login.js"></script>
</body>
</html>
