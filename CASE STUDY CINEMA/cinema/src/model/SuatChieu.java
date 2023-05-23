package model;

public class SuatChieu {
    private String ngayChieu;

    private String gioChieu;

    private RapChieu rapChieu;

    private String phimChieu;


    public String getNgayChieu() {
        return ngayChieu;
    }

    public void setNgayChieu(String ngayChieu) {
        this.ngayChieu = ngayChieu;
    }

    public String getGioChieu() {
        return gioChieu;
    }

    public void setGioChieu(String gioChieu) {
        this.gioChieu = gioChieu;
    }

    //    private long rapChieuId;


    public SuatChieu(String ngayChieu, String gioChieu, String phimChieu) {
        this.ngayChieu = ngayChieu;
        this.gioChieu = gioChieu;
        this.phimChieu = phimChieu;
    }

//    public java.lang.String getThongTinSuatChieu() {
//        return this.phimChieu.getTenPhim() + " - " + this.ngayChieu + " " + this.gioChieu + " - " + this.rapChieu.getTenRap();
//    }

    public RapChieu getRapChieu() {
        return rapChieu;
    }

    public void setRapChieu(RapChieu rapChieu) {
        this.rapChieu = rapChieu;
    }

    public String getPhimChieu() {
        return phimChieu;
    }

    public void setPhimChieu(String phimChieu) {
        this.phimChieu = phimChieu;
    }
}
