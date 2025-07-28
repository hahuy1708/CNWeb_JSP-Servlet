package Model.bo;

import java.util.ArrayList;
import Model.bean.NhanVien;
import Model.dao.NhanVienDAO;

public class NhanVienBO {
    NhanVienDAO dao = new NhanVienDAO();

    public ArrayList<NhanVien> getAllNhanVien() {
        return dao.getAllNV();
    }

    public void insertNhanVien(NhanVien nv) {
        dao.insertNV(nv);
    }

    public void updateNhanVien(NhanVien nv) {
        dao.updateNV(nv);
    }

    public NhanVien getNhanVienByID(String idNV) {
        return dao.getNVbyID(idNV);
    }

    public void deleteNhanVien(String idNV) {
        dao.deleteNV(idNV);
    }

    public int deleteAllNhanVien(ArrayList<String> listIdNV) {
        return dao.deleteAllNV(listIdNV);
    }
}
