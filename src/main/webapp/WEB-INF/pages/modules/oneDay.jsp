<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<div class = "content">
    <div class="error">${updateError}</div>
    <c:forEach items="${todayMap}" var="today">
        <div class = "doctor-frame">
            <div class = "info-frame">
                <div class = "doctor-name" >${today.key.time_app}</div>
                <div class = "doctor-spec"><p class = "doctorSmallInfo left">Пациент:</p><p class = "doctorSmallInfo"><c:if test="${today.value.name == null}">Нет записи</c:if>${today.value.name} ${today.value.surname}</p>
                <p class = "doctorSmallInfo">Дата рождения: ${today.value.birthday}</p></div>
                <div class = "hide" id = "Record${today.key.rec_id}">${today.key.record}</div>
                <div class = "hide" id = ""></div>
            </div>
            <div class = "doctor-act">
            <c:if test="${today.value.name != null}">
                <sec:authorize access="hasAuthority('DOCTOR')">
                    <c:if test="${today.key.visit == null}">
                        <div class = "doctor-button"><a href="#" onclick="startRecord('${today.key.rec_id}')"><p>Начать прием</p></a></div>
                        <div class = "doctor-button"><a href="/doctor/didNotCome/${today.key.rec_id}"><p>Неявка</p></a></div>
                    </c:if>
                    <c:if test="${today.key.visit == true}">
                        <div class = "doctor-button"><a href="#" onclick="editRecord('${today.key.rec_id}')"><p>Редактировать</p></a></div>
                    </c:if>
                    <c:if test="${today.key.visit == false}">
                        <p>Не явился</p>
                    </c:if>
                </sec:authorize>
                <sec:authorize access="hasAuthority('ADMIN')">
                    <c:if test="${today.key.visit == true}">
                        <div class = "doctor-record">${today.key.record}</div>
                    </c:if>
                    <c:if test="${today.key.visit == false}">
                        <div class = "doctor-record">Не явился</div>
                    </c:if>
                    <c:if test="${today.key.visit == null}">
                        <div class = "doctor-record">Прием не открывался</div>
                    </c:if>
                </sec:authorize>
            </c:if>
            </div>
            <hr>
        </div>
    </c:forEach>
</div>