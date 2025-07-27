package Model.bo;

import Model.dao.PhongBanDAO;
import Model.bean.PhongBan;
import java.util.ArrayList;

public class PhongBanBO{
	PhongBanDAO dao = new PhongBanDAO();
	public ArrayList<PhongBan> getAllPB(){
		return dao.getAllPB();
	}
	public void insertPB(PhongBan pb) {
		dao.inserPB(pb);
	}
	public void updatePB(PhongBan pb) {
		dao.updatePB(pb);
	}
	public PhongBan getPBbyID(String IdPB) {
		return dao.getPBbyID(IdPB);
	}
}
