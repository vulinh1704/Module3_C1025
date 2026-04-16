<%--
  Created by IntelliJ IDEA.
  User: truon
  Date: 4/16/2026
  Time: 7:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
<%--    <link rel="stylesheet" href="./style/index.css">--%>
    <link rel="stylesheet" href="<c:url value='/style/index.css'/>">
</head>
<body>
<h1>Java Servlet Syntax</h1>
<%
    String data = request.getAttribute("name").toString();
    String[] names = (String[]) request.getAttribute("names");
%>
<h3>Hello <%= data %>
</h3>
<h3> List Students: </h3>
<ul>
    <% for (String item : names) {%>
    <li><%= item%>
    </li>
    <% }%>
</ul>
<hr>

<h1>JSTL Syntax</h1>
<h3> Hello ${name}</h3>
<ul>
    <c:forEach var="item" items="${names}">
        <c:if test="${item.contains('o')}">
        <li>${item}</li>
        </c:if>
    </c:forEach>
</ul>
<h3>
    Student Info:
</h3>
<p>Id: ${student.id}</p>
<p>Name: ${student.name}</p>
<p>Age: ${student.age}</p>
</body>
</html>

<%--
Các phần cần nhớ của JSTL:
+ Hiển thị biến
+ Chạy vòng lặp
+ Cấu trúc điều kiện
--%>

<%--
JSTL:
là 1 thư viện hỗ trợ cho việc xử lý dữ liệu bên JSP 1 cách dễ dàng
Maven Repository:
// Source: https://mvnrepository.com/artifact/javax.servlet/jstl
implementation("javax.servlet:jstl:1.2")
--%>

<%--
Dữ liệu có thể truyền các servlet
JSP là Servlet
=> Truyền dữ liệu từ Servlet  -------> JSP
                  Code Logic    Nhận data và xử lý lên giao diện
--%>