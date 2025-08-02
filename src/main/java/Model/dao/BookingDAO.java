package Model.dao;

import Model.bean.Booking;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

	public Booking getById(int id) {
	    String sql = "SELECT b.*, u.fullname AS teacher_name, r.room_name AS room_name " +
	                 "FROM bookings b " +
	                 "JOIN users u ON b.user_id = u.id " +
	                 "JOIN rooms r ON b.room_id = r.id " +
	                 "WHERE b.id = ?";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, id);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return extractBooking(rs);
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return null;
	}

    public List<Booking> getAll() {
        List<Booking> list = new ArrayList<>();

        String sql = "SELECT b.*, u.fullname AS teacher_name, r.room_name AS room_name " +
                     "FROM bookings b " +
                     "JOIN users u ON b.user_id = u.id " +
                     "JOIN rooms r ON b.room_id = r.id " +
                     "ORDER BY b.date DESC, b.`from`";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(extractBooking(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(Booking booking) {
        if (isConflict(booking)) return false;

        String sql = "INSERT INTO bookings (user_id, room_id, date, `from`, `to`, purpose) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, booking.getUserId());
            ps.setInt(2, booking.getRoomId());
            ps.setDate(3, booking.getDate());
            ps.setInt(4, booking.getFrom());
            ps.setInt(5, booking.getTo());
            ps.setString(6, booking.getPurpose());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Booking booking) {
        if (isConflict(booking)) return false;

        String sql = "UPDATE bookings SET user_id = ?, room_id = ?, date = ?, `from` = ?, `to` = ?, purpose = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, booking.getUserId());
            ps.setInt(2, booking.getRoomId());
            ps.setDate(3, booking.getDate());
            ps.setInt(4, booking.getFrom());
            ps.setInt(5, booking.getTo());
            ps.setString(6, booking.getPurpose());
            ps.setInt(7, booking.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM bookings WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Booking extractBooking(ResultSet rs) throws SQLException {
        Booking booking = new Booking();
        booking.setId(rs.getInt("id"));
        booking.setUserId(rs.getInt("user_id"));
        booking.setRoomId(rs.getInt("room_id"));
        booking.setDate(rs.getDate("date"));
        booking.setFrom(rs.getInt("from"));
        booking.setTo(rs.getInt("to"));
        booking.setPurpose(rs.getString("purpose"));

        booking.setTeacherName(rs.getString("teacher_name"));
        booking.setRoomName(rs.getString("room_name"));

        return booking;
    }

    private boolean isConflict(Booking booking) {
        String sql = "SELECT COUNT(*) FROM bookings " +
                     "WHERE room_id = ? AND date = ? " +
                     "AND NOT (? < `from` OR ? > `to`)";

        if (booking.getId() > 0) {
            sql += " AND id != ?";
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, booking.getRoomId());
            ps.setDate(2, booking.getDate());
            ps.setInt(3, booking.getTo());   // newTo < oldFrom
            ps.setInt(4, booking.getFrom()); // newFrom > oldTo

            if (booking.getId() > 0) {
                ps.setInt(5, booking.getId());
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
    
    public List<Booking> filter(Integer roomId, Date date) {
        List<Booking> list = new ArrayList<>();
        
        StringBuilder sql = new StringBuilder(
            "SELECT b.*, u.fullname AS teacher_name, r.room_name AS room_name " +
            "FROM bookings b " +
            "JOIN users u ON b.user_id = u.id " +
            "JOIN rooms r ON b.room_id = r.id " +
            "WHERE 1=1 "
        );

        if (roomId != null) {
            sql.append("AND b.room_id = ? ");
        }
        if (date != null) {
            sql.append("AND b.date = ? ");
        }

        sql.append("ORDER BY b.date DESC, b.`from`");

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            int paramIndex = 1;

            if (roomId != null) {
                ps.setInt(paramIndex++, roomId);
            }

            if (date != null) {
                ps.setDate(paramIndex++, date);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(extractBooking(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
