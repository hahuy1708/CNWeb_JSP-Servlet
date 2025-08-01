package Model.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import Model.bean.Room;
import Model.dao.RoomDAO;

public class RoomBO {
    private RoomDAO roomDAO = new RoomDAO();

    public ArrayList<Room> getAllRoom() {
        return roomDAO.getAllRoom();
    }

    public boolean insertRoom(Room room) {
        try {
            return roomDAO.insertRoom(room);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateRoom(Room room) {
        try {
            return roomDAO.updateRoom(room);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteRoom(int id) {
        try {
            return roomDAO.deleteRoom(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Room> getAvailableRooms() {
        try {
            return roomDAO.getAvailableRooms();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public Room getRoomById(int id) {
        return roomDAO.getRoomById(id);
    }
}
