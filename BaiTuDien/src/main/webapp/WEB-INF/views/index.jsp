<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> <%-- Dành riêng cho Tomcat 10 --%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Từ điển Anh-Việt đơn giản</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 50px auto;
            max-width: 600px;
            background-color: #f8f9fa;
            color: #333;
        }
        .container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        }
        h2 {
            color: #2c3e50;
            margin-bottom: 20px;
            text-align: center;
        }
        .form-group {
            display: flex;
            gap: 10px;
            margin-bottom: 20px;
        }
        input[type="text"] {
            flex: 1;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            padding: 10px 20px;
            font-size: 16px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background 0.2s;
        }
        button:hover {
            background-color: #0056b3;
        }
        .result-box {
            margin-top: 25px;
            padding: 15px;
            background-color: #e2f0d9;
            border-left: 5px solid #385723;
            border-radius: 4px;
        }
        .error-box {
            margin-top: 25px;
            padding: 15px;
            background-color: #fce4d6;
            border-left: 5px solid #c65911;
            border-radius: 4px;
            color: #c65911;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Ứng dụng từ điển Anh-Việt</h2>
    <form action="<c:url value='/dictionary' />" method="POST">
        <div class="form-group">
            <input type="text" name="search" placeholder="Nhập từ cần tra cứu" value="${search}" required>
            <button type="submit">Tra từ</button>
        </div>
    </form>

    <c:if test="${not empty result}">
        <div class="result-box">
            <p><strong>Từ tiếng anh:</strong> <span style="color:#007bff;">${search}</span> </p>
            <p><strong>Nghĩa tiếng việt: </strong><span>${result}</span></p>
        </div>
    </c:if>
    <c:if test="${not empty message}">
        <div class="error-box">
            <p style="margin: 0;"> ${message}</p>
        </div>
    </c:if>
</div>
</body>
</html>
