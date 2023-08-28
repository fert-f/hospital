<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/left-menu.css">
    <title>Home page</title>
  </head>

  <body>
    <jsp:include page="modules/header.jsp" />
    <div class = "warp">
        <div class = "left-menu-warp">
            <jsp:include page="modules/left_menu.jsp" />
        </div>
        <div class = "content-warp">
            <div class = "content">
                <p>
                    ${message}!
                </p>
                <p>
                    ${main_page_content}
                </p>
            </div>
        </div>
    </div>
    <jsp:include page="modules/footer.jsp" />
  </body>
</html>



