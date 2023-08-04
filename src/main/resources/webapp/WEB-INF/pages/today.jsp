<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/left-menu.css">
    <link rel="stylesheet" href="/css/doctorsDay.css">
    <script src="/js/jquery-3.5.1.min.js"></script>
    <script src="/js/doctorsDay.js"></script>
    <title>Doctors list</title>
  </head>

  <body>
    <jsp:include page="modules/header.jsp" />
    <div class = "warp">
        <div class = "left-menu-warp">
            <jsp:include page="modules/left_menu.jsp" />
        </div>
        <div class = "content-warp">
            <jsp:include page="modules/oneDay.jsp" />
        </div>
    </div>
    <sec:authorize access="hasAuthority('DOCTOR')">
    <div id="dark-background">

        <div class = "dark-background-form-holder" id = "dark-background-form-holder">
            <form:form action="/doctor/saveRecord" method="post" modelAttribute="recordForm" id = "recordForm">
                <h3>Запись о приеме</h3>
                <div>
                   <form:hidden path="rec_id" id="rec_id" title="rec_id" />
                </div>
                <div>
                   <form:textarea rows="11" cols="60" path="record" id="record" title="record" />
                </div>
                <div>
                <button type="submit" id="saveButton" >Сохранить</button>
                </div>
            </form:form>

        </div>

    </div>
    </sec:authorize>
    <jsp:include page="modules/footer.jsp" />
  </body>
</html>



