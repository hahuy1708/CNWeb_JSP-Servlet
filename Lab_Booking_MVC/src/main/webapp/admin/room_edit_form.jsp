<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sửa thông tin phòng</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <style>
	.form-container {
            max-width: 500px;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        input[type="text"],
        input[type="number"],
        textarea {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
        .form-actions {
            text-align: right;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Sửa thông tin phòng: ${room.roomName}</h2>
        <form action="RoomServlet" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="${room.id}">
            
            <div class="form-group">
                <label for="roomName">Tên phòng:</label>
                <input type="text" id="roomName" name="roomName" 
                       value="${room.roomName}" required>
            </div>
            
            <div class="form-group">
                <label for="computerCount">Số máy tính:</label>
                <input type="number" id="computerCount" name="computerCount" 
                       value="${room.computerCount}" min="1" required>
            </div>
            
            <div class="form-group">
                <label for="description">Mô tả:</label>
                <textarea id="description" name="description" rows="3">${room.description}</textarea>
            </div>
            
            <div class="form-group">
                <label for="status">Trạng thái:</label>
                <select id="status" name="status" required>
                    <option value="ACTIVE" ${room.status == 'ACTIVE' ? 'selected' : ''}>Có sẵn</option>
					<option value="INACTIVE" ${room.status == 'INACTIVE' ? 'selected' : ''}>Không khả dụng</option>
                    
                </select>
            </div>
            
            <div class="form-actions">
                <a href="RoomServlet?action=list" class="btn-cancel">Hủy</a>
                <button type="submit" class="btn-submit">Cập nhật</button>
            </div>
        </form>
    </div>
</body>
</html>