package Model.bean;

public class PhongBan {
    private String idPB;
    private String tenPB;
    private String moTa;

    public PhongBan() {}

    public PhongBan(String idPB, String tenPB, String moTa) {
        this.idPB = idPB;
        this.tenPB = tenPB;
        this.moTa = moTa;
    }

    public String getIdPB() {
        return idPB;
    }

    public void setIdPB(String idPB) {
        this.idPB = idPB;
    }

    public String getTenPB() {
        return tenPB;
    }

    public void setTenPB(String tenPB) {
        this.tenPB = tenPB;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
