<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="iuh.backend.models.Productprice" %>
<!DOCTYPE html>
<html>
<head>
    <title>New Product Price</title>
</head>
<body>
<h1>Thêm Giá Sản Phẩm Mới</h1>

<%
    // Lấy đối tượng Productprice từ request
    Productprice productPrice = (Productprice) request.getAttribute("productPrice");
%>

<form action="saveProductPrice" method="post">
    <div>
        <label for="productId">Mã Sản Phẩm:</label>
        <input type="text" id="productId" name="productId" value="<%= productPrice.getProduct().getId() %>" readonly />
    </div>
    <div>
        <label for="price">Giá:</label>
        <input type="text" id="price" name="price" value="<%= productPrice.getPrice() %>" required />
    </div>
    <div>
        <label for="note">Ghi Chú:</label>
        <textarea id="note" name="note"><%= productPrice.getNote() %></textarea>
    </div>
    <div>
        <input type="submit" value="Lưu" />
    </div>
</form>
</body>
</html>
