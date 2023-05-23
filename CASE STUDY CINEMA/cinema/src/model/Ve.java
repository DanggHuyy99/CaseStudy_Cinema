package model;

public class Ve {
    private int soGhe;

    private double giaVe;

    private String phim;

    private String ngayChieu;

    private String gioChieu;

    public Ve(int soGhe, double giaVe, String phim, String ngayChieu, String gioChieu){
        this.soGhe = soGhe;
        this.giaVe = giaVe;
        this.phim = phim;
        this.ngayChieu = ngayChieu;
        this.gioChieu = gioChieu;
    }

    public int getSoGhe() {
        return soGhe;
    }

    public void setSoGhe(int soGhe) {
        this.soGhe = soGhe;
    }

    public double getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(double giaVe) {
        this.giaVe = giaVe;
    }

    public String getPhim() {
        return phim;
    }

    public void setPhim(String phim) {
        this.phim = phim;
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
