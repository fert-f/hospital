<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<div class = "content">
    <div class="error">${updateError}</div>
    <c:forEach items="${manyMap}" var="todayMap">
    <div class = "one-frame">
        <h3>${todayMap.key}</h3>
        <c:forEach items="${todayMap.value}" var="today">
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
                    <sec:authorize access="hasAuthority('ADMIN')">
                        <div class = "doctor-record">${today.key.record}</div>
                    </sec:authorize>
                </c:if>
                </div>
                <hr>
            </div>
        </c:forEach>
    </div>
    </c:forEach>
</div>