package service;

import csvfile.ReadCSVFile;
import csvfile.WriteCSVFile;
import model.Phim;
import model.QuanLyRapChieu;
import model.RapChieu;
import model.SuatChieu;
import view.CinemaView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static view.CinemaView.scanner;

public class EditRapChieu {
    static String suatChieuPath = "data/suatchieu.csv";

    static String rapChieuPath = "data/rapchieu.csv";

    public void editSuatChieu(ArrayList<SuatChieu> danhSachSuatChieu, ArrayList<Phim> danhsachPhim, ArrayList<RapChieu> rapchieuPhim){
        int choice;
        ReadCSVFile.getDanhSachSuatChieu(danhSachSuatChieu, suatChieuPath);
        do {
            menuRapChieu();
            while (true){
                try {
                    CinemaView.showChoice();
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice >= 0) break;
                    else System.out.println("Nhập lại");
                } catch (IllegalArgumentException e){
                    System.out.println("Nhập lại");
                }
            }
            switch (choice) {
                case 1:
                    showRapChieu(rapchieuPhim);
                    break;
                case 2:
                    suaSuatChieu(rapchieuPhim);
                    break;
                case 3:
                    showSuatChieu(danhSachSuatChieu, danhsachPhim);
                    break;
                case 4:
                    WriteCSVFile.writeCSVSuatChieu(danhSachSuatChieu, suatChieuPath);
                    break;
                case 5:
                    deleteSuatChieu(danhSachSuatChieu);
                case 0:
                    break;
            }
        } while (choice != 0);
    }

    private static void deleteSuatChieu(ArrayList<SuatChieu> danhSachSuatChieu) {
        String phimMuonXoaSuatChieu = getPhimMuonXoaSuatChieu();
        String ngayMuonXoa = getNgayMuonXoa();
        String gioMuonXoa = getGioMuonXoa();
        QuanLyRapChieu.xoaSuatChieu(danhSachSuatChieu, ngayMuonXoa, gioMuonXoa, phimMuonXoaSuatChieu);
        WriteCSVFile.writeCSVSuatChieuAfterDelete(danhSachSuatChieu, suatChieuPath);
    }

    private static String getGioMuonXoa() {
        String gioMuonXoa;
        boolean checkGioMuonXoa = false;
        String time_regex = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$";
        do {
            System.out.println("Nhập giờ muốn xoá (HH:mm) ");
            gioMuonXoa = scanner.nextLine();
            if (Pattern.compile(time_regex).matcher(gioMuonXoa).matches()) {
                checkGioMuonXoa = true;
            }
        } while (!checkGioMuonXoa);
        return gioMuonXoa;
    }

    private static String getNgayMuonXoa() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Pattern pattern = Pattern.compile("^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$");
        String ngayMuonXoa;
        Date date = null;
        boolean checkNgayMuonXoa = false;
        do {
            System.out.println("Nhập ngày muốn xoá (dd/mm/yyyy)");
            ngayMuonXoa = scanner.nextLine();
            Matcher matcher = pattern.matcher(ngayMuonXoa);
            if (matcher.matches()) {
                try {
                    date = sdf.parse(ngayMuonXoa);
                    checkNgayMuonXoa = true;
                } catch (ParseException e) {
                    System.out.println("Ngày không hợp lệ, vui lòng nhập lại");
                }
            } else {
                System.out.println("Ngày không hợp lệ, vui lòng nhập lại");
            }
        } while (!checkNgayMuonXoa);
        return ngayMuonXoa;
    }

    private static String getPhimMuonXoaSuatChieu() {
        String phimMuonXoaSuatChieu;
        while (true){
            try {
                System.out.println("Nhập phim muốn xoá suất chiếu");
                phimMuonXoaSuatChieu = scanner.nextLine();
                if (phimMuonXoaSuatChieu.trim().isEmpty()){
                    throw new IllegalArgumentException("Định dạng sai. Nhập lại");
                }
                break;
            } catch (IllegalArgumentException e){
                System.out.println("Định dạng sai. Nhập lại");
            }
        }
        return phimMuonXoaSuatChieu;
    }

    private static void showSuatChieu(ArrayList<SuatChieu> danhSachSuatChieu, ArrayList<Phim> danhsachPhim) {
        int choice;
        do {
            System.out.println("                         DANH SÁCH CÁC SUẤT CHIẾU TẠI RẠP");
            System.out.println(" * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
            for (Phim movie : danhsachPhim) {
                System.out.printf("%-25s", movie.getTenPhim() + ":");
                for (SuatChieu thongtin : danhSachSuatChieu) {
                    if (thongtin.getPhimChieu().equals(movie.getTenPhim())) {
                        System.out.print(" " + thongtin.getNgayChieu() + " ");
                        System.out.print(thongtin.getGioChieu() + " || ");
                    }
                }
                System.out.println();
            }
            System.out.println(" * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
            System.out.println();
//            System.out.println("Nhập 0 để quay lại");
//            choice = Integer.parseInt(scanner.nextLine());
            while (true){
                try {
                    System.out.println("Nhấn 0 để quay lại");
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice == 0) break;
                    else System.out.println("Nhập lại");
                } catch (NumberFormatException e){
                    System.out.println("Nhập lại");
                }
            }
        } while (choice != 0);
    }

    private static void suaSuatChieu(ArrayList<RapChieu> rapchieuPhim) {
        int choice;
        do {
            WriteCSVFile.writeCSVRapChieu(rapchieuPhim, rapChieuPath);
            choice = 0;
        } while (choice != 0);
    }

    private static void showRapChieu(ArrayList<RapChieu> rapchieuPhim) {
        int choice;
        do {
            ReadCSVFile.getThongTinRapChieu(rapchieuPhim, rapChieuPath);
            for (RapChieu thongtin : rapchieuPhim) {
                System.out.println(" * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
                System.out.printf("Tên rạp:" + "\t" + thongtin.getTenRap() + "\n");
                System.out.printf("Địa chỉ:" + "\t" + thongtin.getDiaChi() + "\n");
                System.out.printf("Tổng Số phòng: " + "\t" + thongtin.getSoPhongChieu() + "\n");
                System.out.println(" * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
            }
//            System.out.println("Nhấn 0 để quay lại");
//            choice = Integer.parseInt(scanner.nextLine());
            while (true){
                try {
                    System.out.println("Nhấn 0 để quay lại");
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice == 0) break;
                    else System.out.println("Nhập lại");
                } catch (NumberFormatException e){
                    System.out.println("Nhập lại");
                }
            }
        } while (choice != 0);
    }

    private static void menuRapChieu() {
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println("1. Thông tin rạp chiếu");
        System.out.println("2. Sửa thông tin rạp chiếu");
        System.out.println("3. Danh sách các suất chiếu tại rạp");
        System.out.println("4. Thêm suất chiếu");
        System.out.println("5. Xoá suất chiếu");
        System.out.println("0. Quay lại menu chính");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * *");
    }
}
