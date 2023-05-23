package model;

import java.util.ArrayList;
import java.util.List;

public class QuanLyRapChieu {
    private List<Phim> danhSachPhim;
    private List<RapChieu> danhSachRapChieu;

    // Hàm khởi tạo
    public QuanLyRapChieu() {
        this.danhSachPhim = new ArrayList<>();
        this.danhSachRapChieu = new ArrayList<>();
    }

    // Thêm phim
    public void themPhim(Phim phim) {
        this.danhSachPhim.add(phim);
    }



    // Thêm rạp chiếu
//    public void themRapChieu(RapChieu rapChieu) {
//        this.danhSachRapChieu.add(rapChieu);
//    }
//
//    public void themSuatChieu(SuatChieu suatChieu) {
//        suatChieu.getPhimChieu().themSuatChieu(suatChieu);
//        suatChieu.getRapChieu().themSuatChieu(suatChieu);
//    }
//
//    public void xoaSuatChieu(SuatChieu suatChieu) {
//        suatChieu.getPhimChieu().getDanhSachSuatChieu().remove(suatChieu);
//        suatChieu.getRapChieu().getDanhSachSuatChieu().remove(suatChieu);
//    }

    // Lấy danh sách suất chiếu theo rạp chiếu
    public List<SuatChieu> getDanhSachSuatChieuTheoRap(RapChieu rapChieu) {
        List<SuatChieu> danhSachSuatChieuTheoRap = new ArrayList<SuatChieu>();
        for (SuatChieu suatChieu : rapChieu.getDanhSachSuatChieu()) {
            danhSachSuatChieuTheoRap.add(suatChieu);
        }
        return danhSachSuatChieuTheoRap;
    }

    // Lấy danh sách suất chiếu theo phim
    public List<SuatChieu> getDanhSachSuatChieuTheoPhim(Phim phimChieu) {
        List<SuatChieu> danhSachSuatChieuTheoPhim = new ArrayList<SuatChieu>();
        for (RapChieu rapChieu : this.danhSachRapChieu) {
            for (SuatChieu suatChieu : rapChieu.getDanhSachSuatChieu()) {
                if (suatChieu.getPhimChieu().equals(phimChieu)) {
                    danhSachSuatChieuTheoPhim.add(suatChieu);
                }
            }
        }
        return danhSachSuatChieuTheoPhim;
    }

//    public Ve datve(java.lang.String tenPhim, int soGhe) {
//        Phim phim = null;
//        for (Phim p : danhSachPhim) {
//            if (p.getTenPhim().equals(tenPhim)) {
//                phim = p;
//                break;
//            }
//        }
//        if (phim == null) {
//            System.out.println("Không có phim này để đặt vé");
//            return null;
//        }
//        for (Ve ve : phim.getDanhSachVe()) {
//            if (ve.getPhim().equals(phim) && ve.getSoGhe() == soGhe) {
//                return null;
//            }
//        }
//        double giaVe = 45000;
//        Ve ve = new Ve(soGhe, giaVe, phim);
//        phim.themVe(ve);
//        return ve;
//    }

    public static void xoaPhim(ArrayList<Phim> danhsachPhim, java.lang.String phim_muon_xoa) {
        for (int i = 0; i < danhsachPhim.size(); i++){
            if (danhsachPhim.get(i).getTenPhim().equals(phim_muon_xoa)){
                danhsachPhim.remove(i);
                System.out.println("Đã xoá phim " + phim_muon_xoa + " ra khỏi danh sách");
                return;
            }
        }
        System.out.println("Không có phim " + phim_muon_xoa + " trong danh sách");
    }

    public static void xoaSuatChieu(ArrayList<SuatChieu> danhSachSuatChieu, String ngayMuonXoa, String gioMuonXoa, String tenPhim) {
        for (int i = 0; i < danhSachSuatChieu.size(); i++){
            if (danhSachSuatChieu.get(i).getNgayChieu().equals(ngayMuonXoa)){
                if (danhSachSuatChieu.get(i).getGioChieu().equals(gioMuonXoa)){
                    danhSachSuatChieu.remove(i);
                    System.out.println("Đã xoá suất chiếu vào lúc " + gioMuonXoa + " ngày " + ngayMuonXoa + " của phim " + tenPhim);
                    return;
                }
            }
        }
        System.out.println("Không có suất chiếu này để xoá " );
    }
}
