<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<div id="ajaxContent">
<sec:authorize access="hasAuthority('ADMIN')">

</sec:authorize>
<sec:authorize access="hasAuthority('PATIENT')">
    <form:form action="/patient/saveReview" method="post" modelAttribute="reviewForm">
        <div>
           <form:hidden path="rec_id" id="rec_id" title="rec_id" value = "${recId}"/>
        </div>
        <div>
           <label title="Отзыв">Оставте свой отзыв</label>
           <form:textarea rows="7" cols="120" path="review" id="review" title="review" />
        </div>
        <div>
        <button type="submit" id="sendButton" >Подтвердить</button>
        </div>
    </form:form>
</sec:authorize>
</div>