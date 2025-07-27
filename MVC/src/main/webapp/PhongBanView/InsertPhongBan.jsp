<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm Phòng Ban</title>
    <style>
        body {
            font-family: Arial;
            padding: 20px;
            background-color: #f4f4f4;
        }

        h2 {
            color: #333;
        }

        form {
            background-color: #fff;
            padding: 20px;
            border: 1px solid #ccc;
            max-width: 400px;
            margin: auto;
        }

        label {
            display: block;
            margin-top: 10px;
        }

        input[type="text"] {
            width: 100%;
            padding: 6px;
            margin-top: 5px;
        }

        input[type="submit"] {
            margin-top: 15px;
            padding: 8px 16px;
            background-color: #28a745;
            color: white;
            border: none;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #218838;
        }

        .back-link {
            display: block;
            text-align: center;
            margin-top: 15px;
        }
    </style>
</head>
<body>

    <h2>Thêm Phòng Ban Mới</h2>

    <form method="post" action="<%= request.getContextPath() %>/PhongBanServlet?action=insert">
        <label for="idPB">Mã Phòng Ban:</label>
        <input type="text" name="idPB" id="idPB" required>

        <label for="tenPB">Tên Phòng Ban:</label>
        <input type="text" name="tenPB" id="tenPB" required>

        <label for="moTa">Mô Tả:</label>
        <input type="text" name="moTa" id="moTa">

        <input type="submit" value="Thêm Phòng Ban">
    </form>

    <div class="back-link">
        <a href="<%= request.getContextPath() %>/PhongBanServlet?action=list">← Quay lại danh sách</a>
    </div>
    
    <div class="back-link">
        <a href="<%= request.getContextPath() %>/home.jsp">← Quay lại trang chủ</a>
    </div>

</body>
</html>
