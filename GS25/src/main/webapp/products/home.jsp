<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>GS25</title>
    <style>
        img {
            width: 50px;
            height: 50px;
            object-fit: cover;
        }
    </style>
</head>
<body>
<h1>Welcome to GS25</h1>
<form action="/products" method="GET">
    <%-- /products?page=home&keyword=...   --%>
    <input type="hidden" name="page" value="home">
    <input type="text" name="keyword" placeholder="Search name">
    <button>Submit</button>
</form>
<a href="/products?page=add">Add Product</a>
<hr>
<table border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Price</th>
        <th>Image</th>
        <th colspan="2">Action</th>
    </tr>
    <c:forEach items="${products}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.price}</td>
            <td><img src="${item.image}" alt=""></td>
            <td><a href="/products?page=edit&id=${item.id}">Edit</a></td>
            <td><a href="/products?page=delete&id=${item.id}">Delete</a></td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
