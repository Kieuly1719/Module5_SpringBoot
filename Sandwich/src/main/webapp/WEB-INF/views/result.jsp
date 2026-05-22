<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Result</title>
</head>
<body>
<h2>Gia vị bạn đã chọn:</h2>
<c:choose>
    <c:when test="${not empty selectedCondiment}">
        <ul>
            <c:forEach var="item" items="${selectedCondiment}">
                <li>${item}</li>
            </c:forEach>
        </ul>
    </c:when>
    <c:otherwise>
        <p>Bạn không chọn gia vị nào cả!</p>
    </c:otherwise>
</c:choose>
<a href="./">Quay lại</a>
</body>
</html>