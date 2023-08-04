<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/left-menu.css">
    <link rel="stylesheet" href="/css/registration.css">
    <script src="/js/jquery-3.5.1.min.js"></script>
    <script src="/js/registration.js"></script>
    <title>Home page</title>
  </head>

  <body>
    <jsp:include page="modules/header.jsp" />
    <div class = "warp">
        <div class = "left-menu-warp">
            <jsp:include page="modules/left_menu.jsp" />
        </div>
        <div class = "content-warp">
        <div class="error">${updateError}</div>
            <div class = "content">
                <form:form action="/admin/createDoctor/proceed" method="post" modelAttribute="registrationDoctorForm">
                      <p title="Registration form">
                        Регистрация врача
                      </p>

                      <div>
                        <label title="Login">Login</label>
                        <form:input path="login" id="check_login" title="Login" />
                      </div>
                      <div id="loginError"></div>
                      <div id="loginOk"></div>

                      <div>
                              <label title="Name">Имя</label>
                              <form:input path="name" title="Name" />
                            </div>

                       <div>
                            <label title="Surname">Фамилия</label>
                            <form:input path="surname" title="Surname" />
                      </div>

                      <div>
                           <label title="Birthday">Дата рождения</label>
                           <form:input path="birthday" title="Birthday" type="date"/>
                      </div>

                      <div>
                           <label title="Специальность">Специальность</label>
                           <form:input path="specialty" title="Specialty" />
                      </div>

                      <div>
                           <label title="Описание">Описание</label>
                           <form:textarea rows="5" cols="30" path="specification" title="Specification" />
                      </div>

                      <div>
                           <label title="Ученая степень">Ученая степень</label>
                           <form:input path="experience" title="Experience" />
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
                        <button type="submit" id="registrationButton" disabled="disabled">Подтвердить</button>
                      </div>
                    </form:form>
            </div>
        </div>
    </div>
    <jsp:include page="modules/footer.jsp" />
  </body>
</html>



