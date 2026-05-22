<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Calculator</title>
</head>
<body>
    <h2>Calculator</h2>
    <form action="calculator" method="post">
        <input type="number" name="num1" value="${num1}" required step="any" />
        <input type="number" name="num2" value="${num2}" required step="any" />
        <br><br>
        <button type="submit" value="add" name="action">Addition(+)</button>
        <button type="submit" value="sub" name="action">Subtraction(-)</button>
        <button type="submit" value="mul" name="action">Multiplication(*)</button>
        <button type="submit" value="div" name="action">Division(/)</button>
    </form>

    <c:if test="{not empty result}">
        <h3>${result}</h3>
    </c:if>
</body>
</html>