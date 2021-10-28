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
    <div class="main-title">
        <h1>
            ВОЙТИ
        </h1>
    </div>
    <form action="login" method="post">
        <div class="field">
            <label for="login">Логин :</label>
            <input type="text" placeholder="Введите ваш логин..." id="login" name="login">
        </div>

        <div class="field">
            <label for="password">Пароль :</label>
            <input type="text" placeholder="Введите ваш пароль..." id="password" name="password">
        </div>

        <div class="form-submit-button">
            <button type="submit" class="come-in-button">
                ВОЙТИ
            </button>
        </div>
    </form>
    <button class="registration-button" onclick="goToRegistration(event)">
        <img src="images/registration.svg">
        Регистрация
    </button>
</main>
<script rel="script" src="scripts/login.js"></script>
</body>
</html>
