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
    <link rel="stylesheet" href="/css/visits.css">
    <link rel="stylesheet" href="/css/patient.css">
    <script src="/js/jquery-3.5.1.min.js"></script>
    <script src="/js/visits.js"></script>
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
                <div class="error">${updateError}</div>
                <h3>Ваши записи на прием:</h3>

                    <c:forEach items="${timePatientRecord}" var="timePatientRecordOne">
                    <div class = "day-frame">
                        <h4>${timePatientRecordOne.date_app} ${timePatientRecordOne.time_app}</h4>
                        <div class="doctor-name">${timePatientRecordOne.doctorName} ${timePatientRecordOne.doctorSurname}</div>
                         <div class = "doctor-spec"><p class = "doctorSmallInfo left">Специальность:</p><p class = "doctorSmallInfo"> ${timePatientRecordOne.specialty}</p>
                        <p class = "doctorSmallInfo">${timePatientRecordOne.experience}</p></div>
                        <div class= "doctor-info"><p>${timePatientRecordOne.specification}</p></div>
                    </div>
                    <div class = "unRecButton" onClick="dropRecord('${timePatientRecordOne.rec_id}')"><p>Отменить запись</p></div>
                    <hr>
                    </c:forEach>

            </div>
        </div>
    </div>
    <jsp:include page="modules/footer.jsp" />
  </body>
</html>



