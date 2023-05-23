package model;

import java.util.ArrayList;
import java.util.List;

public class Phim {
    private java.lang.String tenPhim;
    private java.lang.String theLoai;
    private java.lang.String daoDien;
    private java.lang.String dienVienChinh;
    private int thoiLuong;
    private int namSanXuat;
    private java.lang.String ngonNgu;
    private java.lang.String moTa;

    private List<SuatChieu> danhSachSuatChieu;
    private ArrayList<Ve> danhSachVe;

    private long idPhim = System.currentTimeMillis();

    public Phim(java.lang.String tenPhim, java.lang.String theLoai, java.lang.String daoDien, java.lang.String dienVienChinh, int thoiLuong, int namSanXuat, java.lang.String ngonNgu, java.lang.String moTa) {
        this.tenPhim = tenPhim;
        this.theLoai = theLoai;
        this.daoDien = daoDien;
        this.dienVienChinh = dienVienChinh;
        this.thoiLuong = thoiLuong;
        this.namSanXuat = namSanXuat;
        this.ngonNgu = ngonNgu;
        this.moTa = moTa;
        this.danhSachSuatChieu = new ArrayList<SuatChieu>();
    }

    public java.lang.String getMoTa() {
        return moTa;
    }

    public void setMoTa(java.lang.String moTa) {
        this.moTa = moTa;
    }

    public java.lang.String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(java.lang.String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public java.lang.String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(java.lang.String theLoai) {
        this.theLoai = theLoai;
    }

    public java.lang.String getDaoDien() {
        return daoDien;
    }

    public void setDaoDien(java.lang.String daoDien) {
        this.daoDien = daoDien;
    }

    public java.lang.String getDienVienChinh() {
        return dienVienChinh;
    }

    public void setDienVienChinh(java.lang.String dienVienChinh) {
        this.dienVienChinh = dienVienChinh;
    }

    public int getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(int thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public int getNamSanXuat() {
        return namSanXuat;
    }

    public void setNamSanXuat(int namSanXuat) {
        this.namSanXuat = namSanXuat;
    }

    public java.lang.String getNgonNgu() {
        return ngonNgu;
    }

    public void setNgonNgu(java.lang.String ngonNgu) {
        this.ngonNgu = ngonNgu;
    }

    // Thêm suất chiếu vào danh sách
    public void themSuatChieu(SuatChieu suatChieu) {
        this.danhSachSuatChieu.add(suatChieu);
    }

    // Lấy danh sách các suất chiếu
    public List<SuatChieu> getDanhSachSuatChieu() {
        return this.danhSachSuatChieu;
    }

    public void themVe(Ve ve){
        danhSachVe.add(ve);
    }

    public ArrayList<Ve> getDanhSachVe(){
        return danhSachVe;
    }

}
