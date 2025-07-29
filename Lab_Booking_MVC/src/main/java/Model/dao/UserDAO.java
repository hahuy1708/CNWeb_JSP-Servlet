package Model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.DBConnection;
import Model.bean.User;

public class UserDAO{
	public User authenticate(String username, String password) throws ClassNotFoundException {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("fullname"),
                        rs.getString("role"),
                        rs.getString("status"),
                        rs.getTimestamp("created_at")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
	public boolean insertUser(User user) throws ClassNotFoundException {
		String sql = "INSERT INTO users (username, password, fullname) VALUES (?,?,?)";
		try(Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setString(1, user.getUsername());
		        stmt.setString(2, user.getPassword());
		        stmt.setString(3, user.getFullname());
		        int rowsInserted = stmt.executeUpdate();
		        return rowsInserted > 0;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
		
}



