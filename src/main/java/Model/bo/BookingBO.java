package Model.bo;

import Model.bean.Booking;
import Model.dao.BookingDAO;

import java.sql.Date;
import java.util.List;

public class BookingBO {
    private final BookingDAO bookingDAO;

    public BookingBO() {
        this.bookingDAO = new BookingDAO();
    }

    public Booking getById(int id) {
        return bookingDAO.getById(id);
    }

    public List<Booking> getAll() {
        return bookingDAO.getAll();
    }

    public boolean insert(Booking booking) {
        return bookingDAO.insert(booking);
    }

    public boolean update(Booking booking) {
        return bookingDAO.update(booking);
    }

    public boolean delete(int id) {
        return bookingDAO.delete(id);
    }
    
    public List<Booking> filter(Integer roomId, Date date) {
        return bookingDAO.filter(roomId, date);
    }
}
