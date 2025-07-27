package Model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.bean.PhongBan;
import Model.DB.DBConnection;

public class PhongBanDAO{
	public PhongBan getPBbyID(String id) {
		PhongBan pb = null;
		try(Connection conn = DBConnection.getConnection()){
			String sql = "SELECT * FROM phongban WHERE IDPB = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
	            pb = new PhongBan(rs.getString("IDPB"), rs.getString("TenPB"), rs.getString("Mota"));
	        }
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return pb;
	}
	
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
	public void inserPB(PhongBan pb) {
		try (Connection conn = DBConnection.getConnection()){
			String sql = "Insert INTO phongban(IDPB, TenPB, Mota) VALUES (?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, pb.getIdPB());
			stmt.setString(2, pb.getTenPB());
			stmt.setString(3, pb.getMoTa());
			stmt.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	public void updatePB(PhongBan pb) {
		try (Connection conn = DBConnection.getConnection()){
			String sql = "Update phongban SET TenPB = ? , Mota = ? WHERE IDPB = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, pb.getTenPB());
			stmt.setString(2, pb.getMoTa());
			stmt.setString(3, pb.getIdPB());
			stmt.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
			
		}
		
	}
	
	
	
	

	
	
}
