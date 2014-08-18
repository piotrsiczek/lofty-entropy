<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ page import="org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter" %>
<%@ page import="org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter" %>
<%@ page import="org.springframework.security.core.AuthenticationException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Login</title>
</head>

<body>
<h1>Login</h1>

    <form name="loginForm" action="<c:url value='j_spring_security_check'/>" method="POST">

        <input type='text' name='username' placeholder="Nazwa użytkownika" value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/>
        <br>
        <input type='password' name='password' placeholder="Hasło" value=""><br>
        <input type="checkbox" name="remember-box">Zapamiętaj mnie.<br>
        <input value="Zaloguj się" type="submit">
        <c:if test="${not empty param.login_error}">
            <font color="red">
                <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
            </font>
        </c:if>

    </form>

    <form name="registrationForm" action="/register" method="POST">

        <input type="text" name="name" placeholder="Imię i nazwisko" value=""/><br>
        <c:if test="${not empty nameError}">
            <font color="red">
                <c:out value="${nameError}"/>
            </font>
        </c:if><br>

        <input type="text" name="login" placeholder="Nazwa użytkownika" value=""/><br>
        <c:if test="${not empty loginError}">
            <font color="red">
                <c:out value="${loginError}"/>
            </font>
        </c:if>
        <c:if test="${not empty registerFail}">
            <font color="red">
                <c:out value="${registerFail}"/>
            </font>
        </c:if><br>
        <input type="text" name="password" placeholder="Hasło" value=""/><br>
        <c:if test="${not empty passError}">
            <font color="red">
                <c:out value="${passError}"/>
            </font>
        </c:if>

        <c:if test="${not empty registerSuccess}">
                <c:out value="${registerSuccess}"/>
        </c:if><br>

        <input value="Zarejestruj się" type="submit">
    </form>
</body>
</html>
