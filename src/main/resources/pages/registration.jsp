<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Registration</title>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/registration.css">
    <script src="/js/jquery-3.5.1.min.js"></script>
    <script src="/js/registration.js"></script>
  </head>

  <body>
    <form:form action="registration/proceed" method="post" modelAttribute="registrationForm">
      <p title="Registration form">
        Registration
      </p>
      <div class="error">${updateError}</div>
      <div>
        <label title="Login">Login</label>
        <form:input path="login" id="check_login" title="Login" />
      </div>
      <div id="loginError"></div>
      <div id="loginOk"></div>

      <div>
              <label title="Name">Name</label>
              <form:input path="name" title="Name" />
            </div>

       <div>
            <label title="Surname">Surname</label>
            <form:input path="surname" title="Surname" />
      </div>

      <div>
           <label title="Birthday">Birthday</label>
           <form:input path="birthday" title="Birthday" type="date"/>
      </div>

      <div>
        <label title="Password">Password</label>
        <form:input class="password" path="password" id="check_password" type="password" title="Password" />
      </div>

      <div>
          <label title="Confirm password">Confirm password</label>
          <form:input  class="confirm" path="confirmPassword" id="check_password2" type="password" title="Confirm password" />
      </div>
      <div id="passMatchError"></div>

      <div>
        <button type="submit" id="registrationButton" disabled="disabled">Confirm</button>
      </div>
    </form:form>

  </body>
</html>
