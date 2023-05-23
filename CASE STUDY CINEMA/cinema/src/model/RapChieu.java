package model;

import java.util.ArrayList;
import java.util.List;

public class RapChieu {
    private String tenRap;
    private String diaChi;

    private int soPhongChieu;
    private List<SuatChieu> danhSachSuatChieu;

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getSoPhongChieu() {
        return soPhongChieu;
    }

    public void setSoPhongChieu(int soPhongChieu) {
        this.soPhongChieu = soPhongChieu;
    }


    public RapChieu(String tenRap, String diaChi, int soPhongChieu) {
        this.tenRap = tenRap;
        this.diaChi = diaChi;
        this.soPhongChieu = soPhongChieu;
        this.danhSachSuatChieu = new ArrayList<SuatChieu>();
    }

    public RapChieu(){
        tenRap = "CineStar Hai Bà Trưng";
        diaChi = "135 Hai Bà Trưng, P. Bến Nghé, Q.1, Tp. Hồ Chí Minh";
        soPhongChieu = 12;
    }

    public String getTenRap() {
        return tenRap;
    }

    public void setTenRap(String tenRap) {
        this.tenRap = tenRap;
    }

    public void themSuatChieu(SuatChieu suatChieu) {
        this.danhSachSuatChieu.add(suatChieu);
    }

    public List<SuatChieu> getDanhSachSuatChieu() {
        return this.danhSachSuatChieu;
    }

    // Lấy thông tin về rạp chiếu
    public String getThongTinRapChieu() {
        return this.tenRap + " - " + this.diaChi + " - " + this.soPhongChieu + " phòng chiếu";
    }

}
