<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<div id="ajaxContent">
    <form:form modelAttribute="dayTimeTableForm" method = "POST" action = "/admin/doctorTimeWork/${doctorId}/setDate">
        <div class = "timeTableSetLabel">
            Первая смена
        </div>
        <div class = "timeTableSetBox">
            <input type="checkbox" path="am" name = "am" value="am" <c:if test="${firstChange == true}"> checked </c:if> />
        </div>
        <div class = "timeTableSetLabel">
            Вторая смена
        </div>
        <div class = "timeTableSetBox">
            <input type="checkbox" path="pm" name = "pm" value="pm"  <c:if test="${secondChange == true}"> checked </c:if> />
        </div>
        <form:hidden path="date" id="dateForQuery" title="id" value = "${dateForQuery}"/>
            <button type="submit" id="saveDayTimeTable" >Сохранить</button>
        </div>
    </form:form>
</div>