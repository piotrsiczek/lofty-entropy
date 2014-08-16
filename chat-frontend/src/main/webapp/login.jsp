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
    <table>
        <tr>
            <td>User:</td><td>
            <input type='text' name='username' value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/>
            </td>
        </tr>
        <tr>
            <td>Password:</td>
            <td>
                <input type='password' name='password'>
            </td>
        </tr>
        <tr>
            <td><input type="checkbox" name="_spring_security_remember_me"></td><td>Don't ask for my password for two weeks</td></tr>

        <tr><td colspan='2'><input value="Zaloguj się" type="submit"></td></tr>
    </table>
    <c:if test="${not empty param.login_error}">
        <font color="red">
            <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
        </font>
    </c:if>

</form>


<form name="registrationForm" action="/register" method="POST">
    <input type='text' name='data'/><br>


    <input value="Zarejestruj się" type="submit">
</form>
</body>
</html>
