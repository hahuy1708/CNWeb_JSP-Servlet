package Model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.DBConnection;
import Model.bean.Room;

public class RoomDAO {
    public ArrayList<Room> getAllRoom() {
        ArrayList<Room> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM rooms";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Room r = new Room(
                    rs.getInt("id"),
                    rs.getString("room_name"),
                    rs.getInt("computer_count"),
                    rs.getString("description"),
                    rs.getString("status")
                );
                list.add(r);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public boolean insertRoom(Room room) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO rooms (room_name, computer_count, description, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, room.getRoomName());
            stmt.setInt(2, room.getComputerCount());
            stmt.setString(3, room.getDescription());
            stmt.setString(4, room.getStatus());
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        }
    }

    public boolean updateRoom(Room room) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE rooms SET room_name = ?, computer_count = ?, description = ?, status = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, room.getRoomName());
            stmt.setInt(2, room.getComputerCount());
            stmt.setString(3, room.getDescription());
            stmt.setString(4, room.getStatus());
            stmt.setInt(5, room.getId());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    public boolean deleteRoom(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM rooms WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        }
    }

    public ArrayList<Room> getAvailableRooms() throws SQLException, ClassNotFoundException {
        ArrayList<Room> list = new ArrayList<>();
        String sql = "SELECT * FROM rooms WHERE status = 'AVAILABLE'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Room r = new Room(
                    rs.getInt("id"),
                    rs.getString("room_name"),
                    rs.getInt("computer_count"),
                    rs.getString("description"),
                    rs.getString("status")
                );
                list.add(r);
            }
        }
        return list;
    }
}