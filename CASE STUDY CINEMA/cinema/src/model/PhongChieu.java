package model;

public class PhongChieu {
    private int maPhong;

    private int tongSoGhe;

    private int soGheTrong;

    private String phimChieu;

    private String ngayChieu;

    private String gioChieu;

    public PhongChieu(int maPhong, int tongSoGhe, int soGheTrong, String phimChieu, String ngayChieu, String gioChieu){
        this.maPhong = maPhong;
        this.tongSoGhe = tongSoGhe;
        this.soGheTrong = soGheTrong;
        this.phimChieu = phimChieu;
        this.ngayChieu = ngayChieu;
        this.gioChieu = gioChieu;
    }

    public void hienThiThongTin(){
        System.out.println("Phòng " + maPhong + ": " + soGheTrong + "/" + tongSoGhe + " ghế trống");
    }

    public int getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    public int getTongSoGhe() {
        return tongSoGhe;
    }

    public void setSoGhe(int tongSoGhe) {
        this.tongSoGhe = tongSoGhe;
    }

    public int getSoGheTrong() {
        return soGheTrong;
    }

    public void setSoGheTrong(int soGheTrong) {
        this.soGheTrong = soGheTrong;
    }

    public String getPhimChieu() {
        return phimChieu;
    }

    public void setPhimChieu(String phimChieu) {
        this.phimChieu = phimChieu;
    }

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
}
