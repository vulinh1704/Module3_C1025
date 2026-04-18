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
    <input type="number" name="id" placeholder="Id">
    <br>
    <input type="text" name="name" placeholder="Name">
    <br>
    <input type="number" name="price" placeholder="Price">
    <br>
    <input type="text" name="image" placeholder="Image URL">
    <br>
    <button>Submit</button>
</form>
</body>
</html>
