<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Product</title>
</head>
<body>
<h1>Edit Product</h1>
<a href="/products?page=home">Home</a>
<hr>
<form action="/products?action=edit" method="POST">
    <input type="number" name="id" placeholder="Id" value="${foundProduct.id}" readonly>
    <br>
    <input type="text" name="name" placeholder="Name" value="${foundProduct.name}">
    <br>
    <input type="number" name="price" placeholder="Price" value="${foundProduct.price}">
    <br>
    <input type="text" name="image" placeholder="Image URL" value="${foundProduct.image}">
    <br>
    <select name="categoryId">
        <c:forEach var="item" items="${categories}">
            <option value="${item.id}"
            <c:if test="${item.id == foundProduct.category.id}">
                selected
            </c:if>
            >${item.name}</option>
        </c:forEach>
    </select>
    <button>Submit</button>
</form>
</body>
</html>