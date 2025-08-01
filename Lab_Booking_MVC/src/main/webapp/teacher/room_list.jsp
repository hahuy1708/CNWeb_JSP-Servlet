<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh sách phòng trống - Giáo viên</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <h1>Danh sách phòng trống</h1>
    <form action="RoomServlet" method="get">
        <input type="hidden" name="action" value="list">
        <input type="text" name="search" placeholder="Tìm kiếm phòng">
        <button type="submit">Tìm kiếm</button>
    </form>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Tên phòng</th>
            <th>Số máy</th>
            <th>Mô tả</th>
            <th>Trạng thái</th>
            <th>Hành động</th>
        </tr>
        <c:forEach var="room" items="${availableRooms}">
            <tr>
                <td>${room.id}</td>
                <td>${room.roomName}</td>
                <td>${room.computerCount}</td>
                <td>${room.description}</td>
                <td>${room.status}</td>
                <td>
                    <a href="RoomServlet?action=book&id=${room.id}">Đăng ký</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>