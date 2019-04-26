<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset=utf-8>
    <meta name=viewport content="width=device-width,initial-scale=1">
    <meta name=viewport content="width=device-width,user-scalable=no,initial-scale=1,maximum-scale=1,minimum-scale=1">
    <title>index</title>

    <script type="text/javascript">
        function butClick(id) {
            window.location.href = 'http://localhost:8080/ts/api/select.xhtml';
        }
    </script>

    <style type="text/css">
        font {
            background: gainsboro;
            padding: 0 12px;
            color: crimson;
            height: 40px;
            border: 0;
            border-radius: 8px;
            font-size: 15px;
        }
    </style>
</head>
<body>
<h1>中秋节假日选择</h1>
<hr/>
<div>
    <c:forEach var="i" items="${data}">
        <font class="buts">${i}</font>
    </c:forEach>
</div>
<hr/>
<button id="but" type="button" onclick="butClick(this)">点击开始</button>
<br>
选中:<h1>${selectValue}</h1>
</body>
</html>
