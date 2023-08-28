<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<link rel="stylesheet" href="/css/searchMenu.css">
<link rel="stylesheet" href="/css/jquery-ui.css">

<script>let enabledDays = [ <c:forEach items="${doctorDaysWorked}" var="workDay"> "${workDay}", </c:forEach> ] </script>
<script src="/js/searchMenu.js"></script>
<div class = "ajaxContentMenu">
    <div class="error">${updateError}</div>
    <div class = "searchMenu">
    <form:form modelAttribute="searchForm">
    <h3>История приемов врача: ${doctor.name} ${doctor.surname}</h3>
    <div class = "searchFrame">
        <div class = "searchLabel">Поиск по пациенту:</div>
        <div>
            <form:select path="patient_id" name="patient_id" id ="patientId" onchange="getPatientHistoryForAdmin()">
                <form:option value="0" disabled="true" selected="true" label="Выберете пациента" />
                 <c:forEach items="${patientsList}" var="patient">
                    <form:option value = "${patient.id}" label="${patient.name} ${patient.surname}, ${patient.birthday}" />
                </c:forEach>
            </form:select>
        </div>
    </div>
    <div class = "searchFrame">
        <div class = "searchLabel">Поиск дате:</div>
        <div>
            <form:input  path="date" name="date" id ="date" onchange="getDayHistory()" disabled="disabled" />
        </div>
    </div>
    </form:form>
    <div id="result2"></div>
</div>