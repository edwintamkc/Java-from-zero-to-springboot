<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>

  <%--jsp表達式，用嚟output去client (browser)
      <%variable 或者 expression%>
  --%>
  <%="aaaaaaaa!"%>  <%--留意有等號--%>
  <%= 1==1%>

  <%--jsp script--%>
  <%
    int sum = 0;
    for (int i = 0; i <= 100; i++) {
      sum += i;
    }
    out.println("<h1>"+ sum +"</h1>");  // 會自動轉為servlet，並且set好曬content等等嘅配置，然後用response嚟output
  %>

  <%--jsp script入面加入html tag--%>
  <%
    for (int i = 0; i < 5; i++) {

  %>
    <h2>hello world! <%=i%></h2>
  <%
    }
  %>

  <%--jsp 聲明
      所有寫係入面嘅嘢，jsp生成java果陣會擺係最出面，相當於一個global variable，
      而script，expression等等嘅寫法就放係_jspService()入面
  --%>
  <%!    // 留意 ，感歎號
    int globalVar = 0;
  %>
  </body>
</html>
