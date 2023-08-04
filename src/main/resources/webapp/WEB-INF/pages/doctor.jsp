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
    <link rel="stylesheet" href="/css/doctors.css">
    <link rel="stylesheet" href="/css/patient.css">
    <script src="/js/jquery-3.5.1.min.js"></script>
    <script src="/js/doctors.js"></script>
    <title>Doctor time</title>
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
                <div class = "doctor-frame">
                    <div class = "doctor-name" ><h3 id = "doctorName${doctor.id}">Запись на прием: ${doctor.name} ${doctor.surname}</h3></div>
                    <div class = "doctor-spec"><p class = "doctorSmallInfo left">Специальность:</p><p class = "doctorSmallInfo" id = "specialty${doctor.id}"> ${doctor.specialty}</p>
                    <p class = "doctorSmallInfo" id = "experience${doctor.id}">${doctor.experience}</p></div>
                    <div class= "doctor-info clear"><p id = "specification${doctor.id}">${doctor.specification}</p></div>
                    <hr>
                </div>
                <c:forEach items="${appointmentList}" var="day">
                    <div class = "day-frame">
                        <h3>${day.key}</h3>
                        <c:forEach items="${day.value}" var="time">
                            <div class= "time-frame" onClick="document.forms['${day.key}${time}'].submit();">
                                <form:form modelAttribute="timeForm" action="/patient/getAppointment" method="post" id="${day.key}${time}" class= "timeForm">
                                    <form:hidden path="date" id="date" title="date" value="${day.key}"/>
                                    <form:hidden path="time_app" id="time_app" title="time_app" value="${time}"/>
                                    <form:hidden path="doctorId" id="doctorId" title="doctorId" value="${doctor.id}"/>
                                    <form:hidden path="patient_id" id="patient_id" title="patient_id" value="${userId}"/>
                                    ${time}
                                </form:form>

                            </div>
                        </c:forEach>
                    </div>
                    <hr>
                </c:forEach>
            </div>
        </div>
    </div>
    <jsp:include page="modules/footer.jsp" />
  </body>
</html>



