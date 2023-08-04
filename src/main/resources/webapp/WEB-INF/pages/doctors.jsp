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
    <script src="/js/jquery-3.5.1.min.js"></script>
    <script src="/js/doctors.js"></script>
    <title>Doctors list</title>
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
                <c:forEach items="${doctors}" var="doctor">
                    <div class = "doctor-frame">
                        <div class = "doctor-name" ><h3 id = "doctorName${doctor.id}">${doctor.name} ${doctor.surname}</h3></div>
                        <div class = "doctor-spec"><p class = "doctorSmallInfo left">Специальность:</p><p class = "doctorSmallInfo" id = "specialty${doctor.id}"> ${doctor.specialty}</p>
                        <p class = "doctorSmallInfo" id = "experience${doctor.id}">${doctor.experience}</p></div>
                        <div class= "doctor-info clear"><p id = "specification${doctor.id}">${doctor.specification}</p></div>
                        <div class = "doctor-act">
                            <sec:authorize access="hasAuthority('ADMIN')">
                                <div class = "doctor-button"><a href="/admin/doctorTimeWork/${doctor.id}"><p>Расписание работы</p></a></div>
                                <div class = "doctor-button"><a href="#" onclick="editDoctor('${doctor.id}')"><p>Редактировать информацию</p></a></div>
                            </sec:authorize>
                            <sec:authorize access="hasAuthority('PATIENT')">
                                <div class = "doctor-button"><a href="/patient/doctor/${doctor.id}"><p>Запись на прием</p></a></div>
                            </sec:authorize>
                        </div>
                        <hr>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <sec:authorize access="hasAuthority('ADMIN')">
    <div id="dark-background">
        <a id = "close" onclick="">
        <div class = "dark-background-form-holder" id = "dark-background-form-holder">
            <form:form action="/admin/updateDoctor" method="post" modelAttribute="editDoctorForm">
                <p title="Edit form">
                Внесение изменений
                </p>
                <h3 id="doctorName"></h3>

                <div>
                   <form:hidden path="id" id="id" title="id" />
                </div>
                <div>
                   <label title="Специальность">Специальность</label>
                   <form:input path="specialty" id="specialty" title="Specialty" />
                </div>

                <div>
                   <label title="Описание">Описание</label>
                   <form:textarea rows="5" cols="30" path="specification" id="specification" title="Specification" />
                </div>

                <div>
                   <label title="Ученая степень">Ученая степень</label>
                   <form:input path="experience" id="experience" title="Experience" />
                </div>

                <div>
                <button type="submit" id="registrationButton" >Подтвердить</button>
                </div>
            </form:form>

        </div>
        </a>
    </div>
    </sec:authorize>
    <jsp:include page="modules/footer.jsp" />
  </body>
</html>



