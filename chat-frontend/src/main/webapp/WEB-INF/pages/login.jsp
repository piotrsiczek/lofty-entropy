<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter" %>
<%@ page import="org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter" %>
<%@ page import="org.springframework.security.core.AuthenticationException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<sec:authorize ifAllGranted="ROLE_USER">
    <% response.sendRedirect("/"); %>
</sec:authorize>

<!DOCTYPE html>
<html>
<head lang="pl">
    <meta charset="UTF-8">
    <title>Chat App</title>
    <%--<script src="/js/login.js"></script>--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/login.css" type="text/css" />
</head>

<body>
<div id="header"></div>
<div id="content">
    <div class="body">
        <div class="mainContent">
            <header class="welcomeHeader">
                <h2>Witaj na czacie w chmurze.</h2>
            </header>
            <div class="welcomeMessage">
                Rozmawiaj ze znajomymi, odkryj nowe wspaniałe miejsca, zarejestruj się aby poznać niesamowite możliwości jakie
                daje chat w chmurze.
            </div>
        </div>

        <div class="rightSideBar">
            <div class="rightContent">
                <form name="loginForm" action="<c:url value='j_spring_security_check'/>" method="POST">

                    <input type='text' class="form-control" name='username' placeholder="Nazwa użytkownika" value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/>
                    <div class="loginDiv">
                        <input type='password' class="form-control" id="passInput" name='password' placeholder="Hasło" value="">
                        <input type="submit" class="btn btn-warning" id="loginButton" value="Zaloguj się">
                    </div>
                    <%--<div class="rememberDiv">--%>
                        <%--<input type="checkbox" name="remember-box"> Zapamiętaj mnie.--%>
                        <%--<button type="button" class="btn btn-link" id="forgotPassButton">Nie pamiętasz hasła?</button>--%>
                    <%--</div>--%>

                    <c:if test="${not empty param.login_error}">
                        <p class="error">
                            <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
                        </p>
                    </c:if>
                </form>
            </div>

            <div class="rightContent">
                <form name="registrationForm" action="/register" method="POST">

                    <header>
                        <h4>Nie masz konta? <small>Zarejestruj się</small></h4>
                    </header>
                    <input type="text" class="form-control" name="name" placeholder="Imię i nazwisko" value=""/><br>
                    <c:if test="${not empty nameError}">
                        <p class="error">
                            <c:out value="${nameError}"/>
                        </p>
                    </c:if>

                    <input type="text" class="form-control" name="login" placeholder="Nazwa użytkownika" value=""/><br>
                    <c:if test="${not empty loginError}">
                        <p class="error">
                            <c:out value="${loginError}"/>
                        </p>
                    </c:if>
                    <c:if test="${not empty registerFail}">
                        <p class="error">
                            <c:out value="${registerFail}"/>
                        </p>
                    </c:if>
                    <input type="password" class="form-control" name="password" placeholder="Hasło" value=""/><br>
                    <c:if test="${not empty passError}">
                        <p class="error">
                            <c:out value="${passError}"/>
                        </p>
                    </c:if>

                    <c:if test="${not empty registerSuccess}">
                        <p class="error">
                        <c:out value="${registerSuccess}"/>
                        </p>
                    </c:if>

                    <div class="rightAllign">
                        <input type="submit" class="btn btn-success" value="Zarejestruj się">
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>

<div id="footer">
    <div class="body">
        <footer class="mainFooter">
            <p>&copy; 2014 Aplikacja internetowa chat w chmurze z zastosowaniem biblioteki Google Web Toolkit oraz platformy Google App Engine.</p>
        </footer>
    </div>
</div>

</body>
</html>
