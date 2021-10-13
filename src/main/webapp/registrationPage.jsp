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
            ЗАРЕГИСТРИРОВАТЬСЯ
        </h1>
    </div>
    <label>Логин :
        <input type="text" placeholder="Введите ваш логин..." id="login_field">
    </label>
    <label>Пароль :
        <input type="password" placeholder="Введите ваш пароль..." id="password_field">
    </label>
    <label>E-mail :
        <input type="email" placeholder="Введите ваш email..." id="email_field">
    </label>
    <input type="submit" class="register-button" value="ЗАРЕГИСТРИРОВАТЬСЯ" onclick="register(event)">
    <button class="move-back-button" onclick="goToComeIn(event)">
        У меня уже есть аккаунт
    </button>
</main>
<script rel="script" src="scripts/login.js"></script>
</body>
</html>
