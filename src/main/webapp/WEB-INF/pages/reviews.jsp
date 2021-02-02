<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/left-menu.css">
    <link rel="stylesheet" href="/css/reviews.css">
    <script src="/js/jquery-3.5.1.min.js"></script>
    <script src="/js/review.js"></script>
    <title>Doctor record archive</title>
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
                <div class = "selectorFrame">

                <sec:authorize access="hasAuthority('ADMIN')">

                        <h3>
                            Просмотр архива отзовов
                        </h3>
                        <div>
                        <form:form id = "doctorSelectForm" modelAttribute="docReviewsForm">
                            <form:select path="doctorId" name="doctorId" id ="doctorId" onchange="getReviews()">
                                <form:option value="0" disabled="true" selected="true" label="Выберете врача" />
                                 <c:forEach items="${doctorsLists}" var="doctor">
                                    <form:option value = "${doctor.id}" label="${doctor.name} ${doctor.surname}" />
                                </c:forEach>
                            </form:select>
                        </form:form>
                        </div>

                </sec:authorize>
                <sec:authorize access="hasAuthority('PATIENT')">

                        <h3>
                            Оставить отзыв на прием
                        </h3>
                        <div>
                        <form:form id = "doctorSelectForm" modelAttribute="docReviewsForm">
                            <div class = "info">Оставить отзыв на прием возможно в течении 14 дней</div>
                            <form:select path="rec_id" id = "rec_id" name="recId" onchange="getReviewForm()">
                                <form:option value="0" disabled="true" selected="true" label="Выберете примем на который хотите оставить отзыв" />
                                 <c:forEach items="${appointments}" var="appointment">
                                    <form:option value = "${appointment.rec_id}" label="${appointment.date_app} ${appointment.time_app}, ${appointment.doctorName} ${appointment.doctorSurname}, ${appointment.specialty}" />
                                </c:forEach>
                            </form:select>
                        </form:form>
                        </div>

                </sec:authorize>

                </div>
                <div id="result"></div>
            </div>
        </div>
    </div>

    <jsp:include page="modules/footer.jsp" />
  </body>
</html>