package Model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.DBConnection;
import Model.bean.Room;

public class RoomDAO{
	public ArrayList<Room> getAllRoom(){
		ArrayList<Room> list = new ArrayList<>();
		try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM rooms";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Room r = new Room (
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
	
	public boolean insertUser(Room room) throws ClassNotFoundException {
		String sql = "INSERT INTO rooms (room_name, computer_count, description) VALUES (?,?,?)";
		try(Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setString(1, room.getRoomName());
		        stmt.setInt(2, room.getComputerCount());
		        stmt.setString(3, room.getDescription());
		        int rowsInserted = stmt.executeUpdate();
		        return rowsInserted > 0;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
		
}



