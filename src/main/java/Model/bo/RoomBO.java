package Model.bo;

import java.util.ArrayList;

import Model.bean.Room;
import Model.dao.RoomDAO;

public class RoomBO {
    private RoomDAO roomDAO = new RoomDAO();

    public ArrayList<Room> getAll() {
        return roomDAO.getAllRoom();
    }

    public Room getById(int id) {
        return roomDAO.getRoomById(id);
    }

    public boolean addRoom(Room room) throws ClassNotFoundException {
        return roomDAO.insertRoom(room);
    }

    public boolean updateRoom(Room room) {
        return roomDAO.updateRoom(room);
    }

    public boolean deleteRoom(int id) {
        return roomDAO.deleteRoom(id);
    }
}
