<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Kết quả chuyển đổi</title>
</head>
<body>
    <h2>Kết quả:</h2>
    <p>Lượng USD: ${usd}</p>
    <p>Tỉ giá: ${rate}</p>
    <h3 style="color: red">Thành tiền: ${vnd} VNĐ</h3>

    <a href="${pageContext.request.contextPath}/">Quay lại</a>
</body>
</html>
