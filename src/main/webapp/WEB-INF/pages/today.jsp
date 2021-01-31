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
                <div class="error">${updateError}</div>
                <c:forEach items="${todayMap}" var="today">
                    <div class = "doctor-frame">
                        <div class = "info-frame">
                            <div class = "doctor-name" >${today.key.time_app}</div>
                            <div class = "doctor-spec"><p class = "doctorSmallInfo left">Пациент:</p><p class = "doctorSmallInfo"><c:if test="${today.value.name == null}">Нет записи</c:if>${today.value.name} ${today.value.surname}</p>
                            <p class = "doctorSmallInfo">Дата рождения: ${today.value.birthday}</p></div>
                        </div>
                        <div class = "doctor-act">
                            <sec:authorize access="hasAuthority('DOCTOR')">
                                <c:if test="${today.key.visit == null}">
                                    <div class = "doctor-button"><a href="#" onclick="startRecord('${today.key.rec_id}')"><p>Начать прием</p></a></div>
                                    <div class = "doctor-button"><a href="/doctor/didNotCome/${today.key.rec_id}"><p>Неявка</p></a></div>
                                </c:if>
                                <c:if test="${today.key.visit == true}">
                                    <div class = "doctor-button"><a href="/admin/doctorTimeWork/${today.value.id}"><p>Редактировать</p></a></div>
                                </c:if>
                                <c:if test="${today.key.visit == false}">
                                    <p>Не явился</p>
                                </c:if>
                            </sec:authorize>
                            <sec:authorize access="hasAuthority('ADMIN')">
                                <div class = "doctor-button"><a href="/patient/doctor/${doctor.id}"><p>Запись на прием</p></a></div>
                            </sec:authorize>
                        </div>
                        <hr>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <sec:authorize access="hasAuthority('DOCTOR')">
    <div id="dark-background">

        <div class = "dark-background-form-holder" id = "dark-background-form-holder">
            <form:form action="/doctor/saveRecord" method="post" modelAttribute="recordForm">
                <p title="Edit form">
                Внесение изменений
                </p>
                <h3>Запись о приеме</h3>

                <div>
                   <form:hidden path="rec_id" id="rec_id" title="rec_id" />
                </div>
                <div>
                   <label title="Описание">Описание</label>
                   <form:textarea rows="7" cols="150" path="specification" id="specification" title="Specification" />
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



