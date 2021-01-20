<html>
<body>
<h2>Hello World!</h2>

<!--${pageContext.request.contextPath} 用作表示當前項目，亦姐係/test (/test就係當前項目)-->
<form action="${pageContext.request.contextPath}/login" method="get">
    Username: <input type="text" class="username"> <br>
    Password: <input type="password" class="password"> <br>
    <input type="submit">
</form>
</body>
</html>
