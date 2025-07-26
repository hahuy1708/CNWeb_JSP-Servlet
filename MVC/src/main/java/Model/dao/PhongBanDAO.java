package Model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.bean.PhongBan;
import Model.DB.DBConnection;

public class PhongBanDAO{
	public ArrayList<PhongBan> getAllPB(){
		ArrayList<PhongBan> list = new ArrayList<>();
		
		try(Connection conn = DBConnection.getConnection()){
			String sql = "Select * From phongban";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String idPB = rs.getString("IDPB");
				String tenPB = rs.getString("TenPB");
				String moTa = rs.getString("Mota");
				PhongBan pb = new PhongBan(idPB,tenPB,moTa);
				list.add(pb);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
