<html>
<body>
<h2>Hello World!</h2>

<!--${pageContext.request.contextPath} 用作表示當前項目，亦姐係/test (/test就係當前項目)-->
<form action="${pageContext.request.contextPath}/login" method="post">
    Username: <input type="text" name="username"> <br>
    Password: <input type="password" name="password"> <br>
    <input type="checkbox" name="hobbies" class="hobby-1"> girls
    <input type="checkbox" name="hobbies" class="hobby-2"> code
    <input type="checkbox" name="hobbies" class="hobby-3"> movie
    <input type="checkbox" name="hobbies" class="hobby-4"> games <br>
    <input type="submit">
</form>
</body>
</html>
