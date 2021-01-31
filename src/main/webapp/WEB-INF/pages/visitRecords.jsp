<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/left-menu.css">
    <link rel="stylesheet" href="/css/doctors.css">
    <script src="/js/jquery-3.5.1.min.js"></script>
    <script src="/js/doctorTimeWork.js"></script>
    <title>Doctor record archive</title>
  </head>

  <body>
    <jsp:include page="modules/header.jsp" />
    <div class = "warp">
        <div class = "left-menu-warp">
            <jsp:include page="modules/left_menu.jsp" />
        </div>
        <div class = "content-warp">
            <div class = "content">
                <div class="error">${updateDoctorError}</div>
                <div>
                    <form:form modelAttribute="docForm">
                        <h3>
                            Просмотр архива
                        </h3>
                        <div>
                            <form:select path="doctorId" name="doctorId" onchange="">
                                <form:option value="0" disabled="true" selected="true" label="Выберете врача" />
                                 <c:forEach items="${doctorsLists}" var="doctor">
                                    <form:option value = "${doctor.id}" label="${doctor.name} ${doctor.surname}" />
                                </c:forEach>
                            </form:select>
                        </div>
                    </form:form>
                </div>
                <div id="result"></div>
            </div>
        </div>
    </div>

    <jsp:include page="modules/footer.jsp" />
  </body>
</html>