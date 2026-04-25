<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Product</title>
</head>
<body>
<h1>Add Product</h1>
<a href="/products?page=home">Home</a>
<hr>
<form action="/products?action=add" method="POST">
    <input type="text" name="name" placeholder="Name">
    <br>
    <input type="number" name="price" placeholder="Price">
    <br>
    <input type="text" name="image" placeholder="Image URL">
    <br>
    <select name="categoryId">
        <c:forEach var="item" items="${categories}">
            <option value="${item.id}">${item.name}</option>
        </c:forEach>
    </select>
    <br>
    <button>Submit</button>
</form>
</body>
</html>
