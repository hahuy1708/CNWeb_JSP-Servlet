package Model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.bean.NhanVien;
import Model.DB.DBConnection;

public class NhanVienDAO {

    public NhanVien getNVbyID(String id) {
        NhanVien nv = null;
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM nhanvien WHERE IDNV = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                nv = new NhanVien(
                    rs.getString("IDNV"),
                    rs.getString("Hoten"),
                    rs.getString("IDPB"),
                    rs.getString("Diachi")
                );
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return nv;
    }

    public ArrayList<NhanVien> getAllNV() {
        ArrayList<NhanVien> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM nhanvien";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(
                    rs.getString("IDNV"),
                    rs.getString("Hoten"),
                    rs.getString("IDPB"),
                    rs.getString("Diachi")
                );
                list.add(nv);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public void insertNV(NhanVien nv) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO nhanvien(IDNV, Hoten, IDPB, Diachi) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nv.getIdNV());
            stmt.setString(2, nv.getHoTen());
            stmt.setString(3, nv.getIdPB());
            stmt.setString(4, nv.getDiaChi());
            stmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateNV(NhanVien nv) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE nhanvien SET Hoten = ?, IDPB = ?, Diachi = ? WHERE IDNV = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nv.getHoTen());
            stmt.setString(2, nv.getIdPB());
            stmt.setString(3, nv.getDiaChi());
            stmt.setString(4, nv.getIdNV());
            stmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteNV(String idNV) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM nhanvien WHERE IDNV = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, idNV);
            stmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int deleteAllNV(ArrayList<String> listIdNV) {
        if (listIdNV == null || listIdNV.isEmpty()) return 0;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < listIdNV.size(); i++) {
            sb.append("?,");
        }
        sb.setLength(sb.length() - 1); // remove last comma
        String sql = "DELETE FROM nhanvien WHERE IDNV IN (" + sb.toString() + ")";

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            for (int i = 0; i < listIdNV.size(); i++) {
                stmt.setString(i + 1, listIdNV.get(i));
            }
            return stmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }
}
