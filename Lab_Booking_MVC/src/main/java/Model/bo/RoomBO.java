package Model.bo;

import java.util.ArrayList;
import Model.bean.Room;
import Model.dao.RoomDAO;

public class RoomBO{
	RoomDAO roomDAO = new RoomDAO();
	public ArrayList<Room> getAllRoom(){
		return roomDAO.getAllRoom();
	}
}