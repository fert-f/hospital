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
    <title>Doctor Time Set</title>
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
                    <h3>Расписание для ${doctor.name} ${doctor.surname}</h3>
                    <form:form modelAttribute="dayForm">
                        <div>
                            Выбирете дату
                        </div>
                        <div>
                            <form:input path="date_app" title="date_app" type="date" min="${tomorrow}" id = "date_app" onchange="getDateWorkTime('${doctorId}')"/>
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



