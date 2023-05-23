package service;

import model.Phim;
import model.PhongChieu;
import model.SuatChieu;
import model.Ve;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SellTicket {
    static String phongChieuPath = "data/phongchieu.csv";

    static String vePath = "data/ve.csv";
    static Scanner scanner = new Scanner(System.in);
    public boolean ticketSales(ArrayList<Phim> danhsachPhim, ArrayList<SuatChieu> danhSachSuatChieu, ArrayList<PhongChieu> danhSachPhongChieu, ArrayList<Ve> danhSachVe) {
        String flower = showListPhim(danhsachPhim);

        String phimMuonXem = getPhimMuonXem(danhsachPhim);
        if (phimMuonXem == null) return true;

        showListDayAndTime(danhSachSuatChieu, flower, phimMuonXem);


        String ngayMuonXem = getNgayMuonXem(danhSachSuatChieu);
        if (ngayMuonXem == null) return true;


        String gioMuonXem = getGioMuonXem(danhSachSuatChieu);
        if (gioMuonXem == null) return true;


        if (checkPhongChieu(danhSachPhongChieu, phimMuonXem, ngayMuonXem, gioMuonXem)) return true;

        showBlankSeat(danhSachPhongChieu, danhSachVe, phimMuonXem, ngayMuonXem, gioMuonXem);


        int soGhe = 0;
        double giaVe = 45000;
        ArrayList<Integer> listVeDaDat = new ArrayList<>();
        ArrayList<Ve> danhSachVeHientai = new ArrayList<>();
        int soLuongVeDat;
        while (true){
            try {
                System.out.println("\nBạn muốn đặt bao nhiêu vé");
                soLuongVeDat = Integer.parseInt(scanner.nextLine());
                if (soLuongVeDat > 0) break;
                else System.out.println("Nhập sai. Nhập lại");
            } catch (IllegalArgumentException e){
                System.out.println("Nhập sai. Nhập lại");
            }
        }
        int i = 0;
        while (i < soLuongVeDat) {
            boolean checkTongSoGhe = true;
            for (PhongChieu tongSoGhe : danhSachPhongChieu) {
                if (phimMuonXem.equals(tongSoGhe.getPhimChieu()) && ngayMuonXem.equals(tongSoGhe.getNgayChieu()) && gioMuonXem.equals(tongSoGhe.getGioChieu())) {
                    while (true) {
                        System.out.println("\nBạn muốn chọn ghế số mấy");
                        soGhe = Integer.parseInt(scanner.nextLine());
                        boolean checkVeDaDat = false;
                        ArrayList<Ve> danhSachVeHienTai = new ArrayList<>();
                        for (Integer gheDaDat : listVeDaDat) {
                            if (soGhe == gheDaDat) {
                                System.out.println("Ghế " + soGhe + " đã có người đặt");
                                checkVeDaDat = true;
                                break;
                            }
                        }
                        if (checkVeDaDat) continue;
                        if (soGhe <= tongSoGhe.getTongSoGhe()) {
                            listVeDaDat.add(soGhe);
                            break;
                        }
                        System.out.println("Phòng " + tongSoGhe.getMaPhong() + " chỉ có " + tongSoGhe.getTongSoGhe() + " ghế");

                    }

                    try {
                        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(phongChieuPath));

                        for (PhongChieu thongTinPhongChieu : danhSachPhongChieu) {
                            bufferedWriter.write(thongTinPhongChieu.getMaPhong() + ","
                                    + thongTinPhongChieu.getTongSoGhe() + ","
                                    + thongTinPhongChieu.getSoGheTrong() + ","
                                    + thongTinPhongChieu.getPhimChieu() + ","
                                    + thongTinPhongChieu.getNgayChieu() + ","
                                    + thongTinPhongChieu.getGioChieu());
                            bufferedWriter.newLine();
                        }
                        bufferedWriter.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            Ve ve = new Ve(soGhe, giaVe, phimMuonXem, ngayMuonXem, gioMuonXem);
            danhSachVeHientai.add(ve);
            i++;
        }
        String sun = "\uD83C\uDF1E";
        for (int j = 0; j < 5; j++) {
            System.out.print(sun + " ");
        }
        System.out.print("\u001B[31m" + "YOUR TICKET" + "\u001B[0m");
        for (int j = 0; j < 5; j++) {
            System.out.print(sun + " ");
        }
        System.out.printf("\n" + sun + "%35s%15s\n", "\033[33m" + "Phim: " + phimMuonXem + "\033[0m", sun);
        for (PhongChieu thongTinPhongChieu : danhSachPhongChieu) {
            if (phimMuonXem.equals(thongTinPhongChieu.getPhimChieu()) && ngayMuonXem.equals(thongTinPhongChieu.getNgayChieu()) && gioMuonXem.equals(thongTinPhongChieu.getGioChieu())) {
                System.out.printf(sun + "%32s%18s\n", "\033[33m" + "Phòng chiếu: " + thongTinPhongChieu.getMaPhong() + "\033[0m", sun);
                break;
            }
        }
        System.out.printf(sun + "%30s%20s\n", "\033[33m" + ngayMuonXem + "\033[0m", sun);
        System.out.printf(sun + "%30s%20s\n", "\033[33m" + gioMuonXem + "\033[0m", sun);
        for (int veDaDat : listVeDaDat) {
            System.out.printf(sun + "%30s%20s\n", "\033[33m" + "Ghế số: " + veDaDat + "\033[0m", sun);
        }
        System.out.printf(sun + "%34s%16s\n", "\033[33m" + " Price: " + soLuongVeDat * danhSachVe.get(0).getGiaVe() + " VND" + "\033[0m", sun);
        for (int j = 0; j < 14; j++) {
            System.out.print(sun + " ");
        }
        System.out.println();

        String input = null;
        do {
            try {
                System.out.println("Xác nhận muốn đặt vé hay không (YES / NO)");
                input = scanner.nextLine();

                if (input.isEmpty()) {
                    throw new IllegalArgumentException("Không được bỏ trống");
                } else if (input.equalsIgnoreCase("YES")) {
                    System.out.println("Đã đặt vé thành công");

                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(vePath));
                        danhSachVe.addAll(danhSachVeHientai);
                        for (Ve thongTinVe : danhSachVe) {
                            writer.write(thongTinVe.getSoGhe() + ","
                                    + thongTinVe.getGiaVe() + ","
                                    + thongTinVe.getPhim() + ","
                                    + thongTinVe.getNgayChieu() + ","
                                    + thongTinVe.getGioChieu());
                            writer.newLine();
                        }
                        writer.close();

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    for (PhongChieu tongSoGhe : danhSachPhongChieu) {
                        if (phimMuonXem.equals(tongSoGhe.getPhimChieu()) && ngayMuonXem.equals(tongSoGhe.getNgayChieu()) && gioMuonXem.equals(tongSoGhe.getGioChieu())) {
                            tongSoGhe.setSoGheTrong(tongSoGhe.getSoGheTrong() - soLuongVeDat);
                        }
                    }
                    try {
                        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(phongChieuPath));

                        for (PhongChieu thongTinPhongChieu : danhSachPhongChieu) {
                            bufferedWriter.write(thongTinPhongChieu.getMaPhong() + ","
                                    + thongTinPhongChieu.getTongSoGhe() + ","
                                    + thongTinPhongChieu.getSoGheTrong() + ","
                                    + thongTinPhongChieu.getPhimChieu() + ","
                                    + thongTinPhongChieu.getNgayChieu() + ","
                                    + thongTinPhongChieu.getGioChieu());
                            bufferedWriter.newLine();
                        }
                        bufferedWriter.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (input.equalsIgnoreCase("NO")) {
                    System.out.println("Đã huỷ bỏ đặt vé ");
                } else {
                    System.out.println("Giá trị nhập không hợp lệ. Vui lòng nhập lại");
                    input = null;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Nhập lại");
            }
        } while (input == null || input.isEmpty());
        return false;
    }

    private static void showBlankSeat(ArrayList<PhongChieu> danhSachPhongChieu, ArrayList<Ve> danhSachVe, String phimMuonXem, String ngayMuonXem, String gioMuonXem) {
        int soGheTrong = 0;
        for (PhongChieu thongTinPhongChieu : danhSachPhongChieu) {
            if (phimMuonXem.equals(thongTinPhongChieu.getPhimChieu())) {
                if (ngayMuonXem.equals(thongTinPhongChieu.getNgayChieu()) && gioMuonXem.equals(thongTinPhongChieu.getGioChieu())) {
                    soGheTrong = thongTinPhongChieu.getSoGheTrong();
                    System.out.println("Phòng " + thongTinPhongChieu.getMaPhong() + " đang chiếu phim " + phimMuonXem + " vào " + ngayMuonXem + " suất " + gioMuonXem);
                    for (int i = 1; i <= thongTinPhongChieu.getTongSoGhe(); i++) {
                        if (i == 11 || i == 21 || i == 31 || i == 41 || i == 51 || i == 61 || i == 71 || i == 81 || i == 91) {
                            System.out.println();
                        }

                        boolean checkGheDaDat = false;
                        for (Ve ve : danhSachVe) {
                            if (ve.getPhim().equalsIgnoreCase(phimMuonXem) && ve.getNgayChieu().equalsIgnoreCase(ngayMuonXem) && ve.getGioChieu().equalsIgnoreCase(gioMuonXem)) {
                                if (ve.getSoGhe() == i) {
                                    System.out.printf("%-10s", "( " + "\u001B[31m" + "XX" + "\u001B[0m" + " ) " + " ");
                                    checkGheDaDat = true;
                                }
                            }
                        }
                        if (checkGheDaDat) {
                            continue;
                        }
                        System.out.printf("%-10s", "( " + "\u001B[33m" + i + "\u001B[0m" + " ) " + " ");
                    }
                }
            }
        }
        System.out.println("\u001B[33m" + "\n(  ): " + " ghế trống" + "\u001B[0m");
        System.out.println("\u001B[31m" + "  XX: " + " ghế đã đặt" + "\u001B[0m");
        System.out.println("Số ghế còn trống là: " + soGheTrong);
    }

    private static boolean checkPhongChieu(ArrayList<PhongChieu> danhSachPhongChieu, String phimMuonXem, String ngayMuonXem, String gioMuonXem) {
        boolean checkPhongChieu = false;
        for (PhongChieu thongTinPhongChieu : danhSachPhongChieu) {
            if (phimMuonXem.equals(thongTinPhongChieu.getPhimChieu())) {
                if (ngayMuonXem.equals(thongTinPhongChieu.getNgayChieu()) && gioMuonXem.equals(thongTinPhongChieu.getGioChieu())) {
                    checkPhongChieu = true;
                }
            }
        }
        if (!checkPhongChieu) {
            System.out.println("Tạm thời chưa có phòng chiếu phim " + phimMuonXem + ". Quý khách thông cảm!");
            return true;
        }
        return false;
    }

    private static String getGioMuonXem(ArrayList<SuatChieu> danhSachSuatChieu) {
        String gioMuonXem;
        while (true){
            try {
                System.out.println("\nChọn giờ bạn muốn xem");
                gioMuonXem = scanner.nextLine();
                if (!gioMuonXem.trim().isEmpty()) break;
                System.out.println("Nhập lại");
            } catch (IllegalArgumentException e){
                System.out.println("Nhập lại");
            }
        }
//        System.out.println("Chọn giờ bạn muốn xem");
//        String gioMuonXem = scanner.nextLine();
        boolean checkGioMuonXem = false;
        for (SuatChieu gioChieu : danhSachSuatChieu) {
            if (Objects.equals(gioMuonXem, gioChieu.getGioChieu())) {
                checkGioMuonXem = true;
                break;
            }
        }
        if (!checkGioMuonXem) {
            System.out.println("Không có giờ chiếu này");
            return null;
        }
        return gioMuonXem;
    }

    private static String getNgayMuonXem(ArrayList<SuatChieu> danhSachSuatChieu) {
        String ngayMuonXem;
        while (true){
            try {
                System.out.println("\nChọn ngày bạn muốn xem");
                ngayMuonXem = scanner.nextLine();
                if (!ngayMuonXem.trim().isEmpty()) break;
                System.out.println("Nhập lại");
            } catch (IllegalArgumentException e){
                System.out.println("Nhập lại");
            }
        }

        boolean checkNgayMuonXem = false;
        for (SuatChieu ngayChieu : danhSachSuatChieu) {
            if (Objects.equals(ngayMuonXem, ngayChieu.getNgayChieu())) {
                checkNgayMuonXem = true;
                break;
            }
        }
        if (!checkNgayMuonXem) {
            System.out.println("Không có ngày chiếu này");
            return null;
        }
        return ngayMuonXem;
    }

    private static void showListDayAndTime(ArrayList<SuatChieu> danhSachSuatChieu, String flower, String phimMuonXem) {
        for (int i = 0; i < 20; i++) {
            System.out.print(flower + " ");
        }
        System.out.println("\nDanh sách các suất chiếu theo ngày và giờ của phim " + phimMuonXem);
        System.out.printf("\t\t\t%-40s%-40s\n", "Ngày chiếu", "Giờ chiếu");
        Set<String> danhSachNgayChieu = new HashSet<>();
        for (SuatChieu thongTinSuatChieu1 : danhSachSuatChieu){
            danhSachNgayChieu.add(thongTinSuatChieu1.getNgayChieu());
        }
//        for (SuatChieu thongTinSuatChieu : danhSachSuatChieu) {
//            if (Objects.equals(phimMuonXem, thongTinSuatChieu.getPhimChieu())) {
//                for (String ngayChieu : danhSachNgayChieu){
//                    System.out.printf("\t\t\t%-40s", ngayChieu);
//                    if (ngayChieu.equalsIgnoreCase(thongTinSuatChieu.getNgayChieu())){
//                        System.out.printf(thongTinSuatChieu.getGioChieu() + " || ");
//                    }
//                }
////                System.out.printf("\t\t\t%-40s%-40s\n", thongTinSuatChieu.getNgayChieu(), thongTinSuatChieu.getGioChieu());
//            }
//        }
        for (String ngayChieu : danhSachNgayChieu) {
            System.out.printf("\t\t\t%-40s", ngayChieu);
            for (SuatChieu thongTinSuatChieu : danhSachSuatChieu){
                if (phimMuonXem.equalsIgnoreCase(thongTinSuatChieu.getPhimChieu()) && thongTinSuatChieu.getNgayChieu().equalsIgnoreCase(ngayChieu)){
                    System.out.print(thongTinSuatChieu.getGioChieu() + " || ");
                }
            }
            System.out.println();
        }
        for (int i = 0; i < 20; i++) {
            System.out.print(flower + " ");
        }
    }

    private static String showListPhim(ArrayList<Phim> danhsachPhim) {
        String flower = "\uD83C\uDF3A";
        for (int i = 0; i < 13; i++) {
            System.out.print(flower + " ");
        }
        System.out.println("\n" + flower + "DANH SÁCH CÁC PHIM ĐANG CHIẾU TẠI RẠP" + flower);
        int count = 1;
        for (Phim tenPhim : danhsachPhim) {
            System.out.printf(flower + "%-37s%-100s\n", count + ". " + tenPhim.getTenPhim(), flower);
            count++;
        }
        for (int i = 0; i < 13; i++) {
            System.out.print(flower + " ");
        }
        return flower;
    }

    private static String getPhimMuonXem(ArrayList<Phim> danhsachPhim) {
        String phim;
        while (true){
            try {
                System.out.println("\nBạn muốn xem phim nào");
                phim = scanner.nextLine();
                if (!phim.trim().isEmpty()) break;
            } catch (IllegalArgumentException e){
                System.out.println("Nhập lại");
            }
        }
        boolean checkTenPhim = false;
        for (Phim tenPhim : danhsachPhim) {
            if (!Objects.equals(phim, tenPhim.getTenPhim())) {
                checkTenPhim = true;
                break;
            }
        }
        if (!checkTenPhim) {
            System.out.println("Không có phim " + phim + " trong danh sách các phim đang chiếu tại rạp");
            return null;
        }
        return phim;
    }
}
