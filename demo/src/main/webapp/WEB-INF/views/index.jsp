<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chuyển đổi tiền tệ</title>
</head>
<body>
    <h2>Ứng dụng chuyển đổi tiền tệ</h2>
    <form action="${pageContext.request.contextPath}/convert" method="post">
        <label>Lượng USD:</label>
        <input type="number" name="usd" required> <br><br>
        <label>Tỉ giá (VND/USD):</label>
        <input type="number" name="rate" value="25000" required> <br><br>

        <button type="submit">Chuyển đổi</button>
    </form>
</body>
</html>
