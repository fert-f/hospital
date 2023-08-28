<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<div class = "ajaxContent">
    <div class="error">${updateError}</div>
    <h3>История приемов пациента: ${patient.name} ${patient.surname}</h3>
    <div class = "doctor-spec"><p class = "doctorSmallInfo">Дата рождения: ${patient.birthday}</p></div>
     <c:forEach items="${patientRecords}" var="record">
        <div class = "doctor-frame">
            <div class = "info-frame">
                <div class = "doctor-name" >${record.date_app} ${record.time_app}</div>
                <div class = "doctor-record">
                <c:if test="${record.visit == false}">Не явился</c:if>
                    ${record.record}
                </div>
            </div>
            <hr>
        </div>
    </c:forEach>
</div>