package Model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.bean.Room;
import util.DBConnection;

public class RoomDAO {

    public ArrayList<Room> getAllRoom() {
        ArrayList<Room> list = new ArrayList<>();
        String sql = "SELECT * FROM rooms";
        
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean insertRoom(Room room) {
        String sql = "INSERT INTO rooms (room_name, computer_count, description) VALUES (?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, room.getRoomName());
            stmt.setInt(2, room.getComputerCount());
            stmt.setString(3, room.getDescription());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Room getRoomById(int id) {
        String sql = "SELECT * FROM rooms WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Room(
                        rs.getInt("id"),
                        rs.getString("room_name"),
                        rs.getInt("computer_count"),
                        rs.getString("description"),
                        rs.getString("status")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateRoom(Room room) {
        String sql = "UPDATE rooms SET room_name = ?, computer_count = ?, description = ?, status = ? WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, room.getRoomName());
            stmt.setInt(2, room.getComputerCount());
            stmt.setString(3, room.getDescription());
            stmt.setString(4, room.getStatus());
            stmt.setInt(5, room.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteRoom(int id) {
        String sql = "DELETE FROM rooms WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
