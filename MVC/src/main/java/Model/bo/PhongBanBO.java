package Model.bo;

import Model.dao.PhongBanDAO;
import Model.bean.PhongBan;
import java.util.ArrayList;

public class PhongBanBO{
	PhongBanDAO dao = new PhongBanDAO();
	public ArrayList<PhongBan> getAllPB(){
		return dao.getAllPB();
	}
}
