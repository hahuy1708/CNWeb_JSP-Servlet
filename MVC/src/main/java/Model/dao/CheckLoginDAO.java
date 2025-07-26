package Model.dao;

import Model.DB.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CheckLoginDAO {
    public boolean isValidUser(String username, String password) {
        boolean result = false;

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            result = rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
