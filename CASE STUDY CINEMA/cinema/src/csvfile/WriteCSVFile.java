package csvfile;

import model.Phim;
import model.RapChieu;
import model.SuatChieu;
import view.CinemaView;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WriteCSVFile {
    static Scanner scanner = new Scanner(System.in);

//    public static void writeCSVVe(ArrayList<Ve> danhSachVe, String veCSV){
//        Ve ve;
//        try {
//            danhSachVe.clear();
//            BufferedWriter writer = new BufferedWriter(new FileWriter(veCSV));
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static void writeCSVRapChieu(ArrayList<RapChieu> rapChieuPhim, java.lang.String suatchieuCSV) {
        RapChieu rapChieu;
        try {
            rapChieuPhim.clear();
            BufferedWriter writer = new BufferedWriter(new FileWriter(suatchieuCSV));
            rapChieu = suaRapChieu();
            rapChieuPhim.add(rapChieu);
            for (RapChieu thongTinRap : rapChieuPhim) {
                writer.write(thongTinRap.getTenRap() + ","
                        + thongTinRap.getDiaChi() + ","
                        + thongTinRap.getSoPhongChieu());
            }
            writer.close();
            System.out.println("Đã sửa thông tin thành công");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeCSVSuatChieu(ArrayList<SuatChieu> danhSachSuatChieu, String suatchieuCSV) {
        SuatChieu suatChieu;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(suatchieuCSV));
            suatChieu = suaSuatChieu();
            danhSachSuatChieu.add(suatChieu);
            for (SuatChieu thongtinSuatChieu : danhSachSuatChieu) {
                writer.write(thongtinSuatChieu.getNgayChieu() + ","
                        + thongtinSuatChieu.getGioChieu() + ","
                        + thongtinSuatChieu.getPhimChieu());
                writer.newLine();
            }
            writer.close();
            System.out.println("Đã thêm suất chiếu của phim " + suatChieu.getPhimChieu() + " lúc " + suatChieu.getGioChieu() + " vào ngày " + suatChieu.getNgayChieu());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeCSVSuatChieuAfterDelete(ArrayList<SuatChieu> danhSachSuatChieu, String suatchieuCSV) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(suatchieuCSV));
            for (SuatChieu suatChieu : danhSachSuatChieu) {
                writer.write(suatChieu.getNgayChieu() + ","
                        + suatChieu.getGioChieu() + ","
                        + suatChieu.getPhimChieu());
                writer.newLine();
            }
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void writeCSVFile(ArrayList<Phim> danhsachPhim, java.lang.String csvFilePath) {
        Phim phim;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath));
            phim = themPhim();
            danhsachPhim.add(phim);
            for (Phim movie : danhsachPhim) {
                writer.write(movie.getTenPhim() + ","
                        + movie.getTheLoai() + ","
                        + movie.getDaoDien() + ","
                        + movie.getDienVienChinh() + ","
                        + movie.getThoiLuong() + ","
                        + movie.getNamSanXuat() + ","
                        + movie.getNgonNgu() + ","
                        + movie.getMoTa());
                writer.newLine();
            }
            writer.close();
            System.out.println("Đã thêm phim " + phim.getTenPhim() + " thành công");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static java.lang.String nhapTenPhim() {
        String input;
        while (true){
            try {
                System.out.println("Nhập tên phim:");
                input = scanner.nextLine();

                if (input.trim().isEmpty()){
                    throw new IllegalArgumentException("Tên phim không được để trống");
                }
                break;
            } catch (IllegalArgumentException e){
                System.out.println("Tên phim không được để trống. Nhập lại");
            }
        }
        return input;
    }

    public static java.lang.String nhapDaoDien() {
        String input;
        while (true){
            try {
                System.out.println("Nhập đạo diễn:");
                input = scanner.nextLine();

                if (input.trim().isEmpty()){
                    throw new IllegalArgumentException("Tên đạo diễn không được để trống");
                }
                break;
            } catch (IllegalArgumentException e){
                System.out.println("Tên đạo diễn không được để trống. Nhập lại");
            }
        }
        return input;
    }

    public static java.lang.String nhapTheLoai() {
        String input;
        while (true){
            try {
                System.out.println("Nhập thể loại:");
                input = scanner.nextLine();

                if (input.trim().isEmpty()){
                    throw new IllegalArgumentException("Thể loại không được để trống");
                }
                break;
            } catch (IllegalArgumentException e){
                System.out.println("Thể loại không được để trống. Nhập lại");
            }
        }
        return input;
    }

    public static java.lang.String nhapDienVienChinh() {
        String input;
        while (true){
            try {
                System.out.println("Nhập diễn viên chính: ");
                input = scanner.nextLine();

                if (input.trim().isEmpty()){
                    throw new IllegalArgumentException("Tên diễn viên chính không được để trống");
                }
                break;
            } catch (IllegalArgumentException e){
                System.out.println("Tên diễn viên chính không được để trống. Nhập lại");
            }
        }
        return input;
    }

    public static int nhapThoiLuong() {
        int time;
        while (true) {
            try {
                System.out.println("Nhập thời lượng: ");
                time = Integer.parseInt(scanner.nextLine());
                if (time > 0) {
                    break;
                } else {
                    System.out.println("Thời lượng phải lớn hơn 0, vui lòng nhập lại");
                }
            } catch (Exception e) {
                System.out.println("Thời lượng phải là số, vui lòng nhập lại");
            }
        }
        return time;
    }

    public static int nhapNamSanXuat() {
        int year;
        String regex = "^(19|20)\\d{2}$";
        Pattern pattern = Pattern.compile(regex);
        while (true) {
            try {
                System.out.println("Nhập năm sản xuất:");
                String input = scanner.nextLine();
                Matcher matcher = pattern.matcher(input);
                if (matcher.matches()) {
                    year = Integer.parseInt(input);
                    break;
                } else {
                    throw new Exception("Định dạng năm sản xuất không hợp lệ. Vui lòng nhập lại");
                }
            } catch (Exception e) {
                System.out.println("Định dạng năm sản xuất không hợp lệ. Vui lòng nhập lại");
            }
        }
        return year;
    }

    public static java.lang.String nhapNgonNgu() {
        String input;
        while (true){
            try {
                System.out.println("Nhập ngôn ngữ: ");
                input = scanner.nextLine();

                if (input.trim().isEmpty()){
                    throw new IllegalArgumentException("Ngôn ngữ không được để trống");
                }
                break;
            } catch (IllegalArgumentException e){
                System.out.println("Ngôn ngữ không được để trống. Nhập lại");
            }
        }
        return input;
    }

    public static java.lang.String nhapMoTa() {
        String input;
        while (true){
            try {
                System.out.println("Nhập mô tả: ");
                input = scanner.nextLine();

                if (input.trim().isEmpty()){
                    throw new IllegalArgumentException("Mô tả không được để trống");
                }
                break;
            } catch (IllegalArgumentException e){
                System.out.println("Mô tả không được để trống. Nhập lại");
            }
        }
        return input;
    }

    public static Phim themPhim() {
        java.lang.String tenPhim = nhapTenPhim();
        java.lang.String theLoai = nhapTheLoai();
        java.lang.String daoDien = nhapDaoDien();
        java.lang.String dienVien = nhapDienVienChinh();
        int thoiLuong = nhapThoiLuong();
        int namSanXuat = nhapNamSanXuat();
        java.lang.String ngonNgu = nhapNgonNgu();
        java.lang.String moTa = nhapMoTa();
        Phim phim = new Phim(tenPhim, theLoai, daoDien, dienVien, thoiLuong, namSanXuat, ngonNgu, moTa);
        return phim;
    }

    public static void writeCSVAfterDelete(ArrayList<Phim> danhsachPhim, java.lang.String csvFilePath) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath));
            for (Phim movie : danhsachPhim) {
                writer.write(movie.getTenPhim() + ","
                        + movie.getTheLoai() + ","
                        + movie.getDaoDien() + ","
                        + movie.getDienVienChinh() + ","
                        + movie.getThoiLuong() + ","
                        + movie.getNamSanXuat() + ","
                        + movie.getNgonNgu() + ","
                        + movie.getMoTa());
                writer.newLine();
            }
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }


    public static RapChieu suaRapChieu() {
        String tenRap = getTenRap();
        String diaChi = getDiaChi();
        int soPhong = getSoPhong();
        RapChieu rapChieu = new RapChieu(tenRap, diaChi, soPhong);
        return rapChieu;
    }

    private static int getSoPhong() {
        int soPhong;
        while (true){
            try {
                System.out.println("Nhập số phòng:");
                soPhong = Integer.parseInt(scanner.nextLine());
                if (soPhong > 0) break;
                else System.out.println("Số phòng phải lớn hơn 0");

            } catch (IllegalArgumentException e){
                System.out.println("Không đúng định dạng số phòng. Vui lòng nhập lại");
            }
        }
        return soPhong;
    }

    private static String getDiaChi() {
        String diaChi;
        while (true){
            try {
                System.out.println("Nhập địa chỉ:");
                diaChi = scanner.nextLine();
                if (diaChi.trim().isEmpty()){
                    throw new IllegalArgumentException("Địa chỉ không được để trống");
                }
                break;
            } catch (IllegalArgumentException e){
                System.out.println("Địa chỉ không được để trống. Nhập lại");
            }
        }
        return diaChi;
    }

    private static String getTenRap() {
        String tenRap;
        while (true){
            try {
                System.out.println("Nhập tên rạp:");
                tenRap = scanner.nextLine();
                if (tenRap.trim().isEmpty()){
                    throw new IllegalArgumentException("Tên rạp không được để trống");
                }
                break;
            } catch (IllegalArgumentException e){
                System.out.println("Tên rạp không được để trống. Nhập lại");
            }
        }
        return tenRap;
    }

    public static SuatChieu suaSuatChieu() {
        String date = getNgayChieu();

        String time = getGioChieu();

        String tenPhim = getTenPhim();
        SuatChieu suatChieu = new SuatChieu(date, time, tenPhim);
        return suatChieu;

    }

    private static String getGioChieu() {
        String gioChieu;
        String regex = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$";
        Pattern pattern = Pattern.compile(regex);
        while (true){
            try {
                System.out.println("Nhập giờ chiếu (HH:mm)");
                String input = scanner.nextLine();
                Matcher matcher = pattern.matcher(input);
                if (matcher.matches()){
                    gioChieu = input;
                    break;
                } else {
                    throw new Exception("Định dạng giờ chiếu không hợp lệ. Vui lòng nhập lại");
                }
            } catch (Exception e) {
                System.out.println("Định dạng giờ chiếu không hợp lệ. Vui lòng nhập lại");
            }

        }
        return gioChieu;
    }

    private static String getNgayChieu() {
        String ngayChieu;
        String regex = "^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        while (true) {
            try {
                System.out.println("Nhập ngày chiếu (dd/MM/yyyy)");
                String input = scanner.nextLine();
                Matcher matcher = pattern.matcher(input);
                if (matcher.matches()) {
                    ngayChieu = input;
                    break;
                } else {
                    throw new Exception("Định dạng ngày chiếu không hợp lệ. Vui lòng nhập lại");
                }
            } catch (Exception e) {
                System.out.println("Định dạng ngày chiếu không hợp lệ. Vui lòng nhập lại");
            }
        }
        return ngayChieu;
    }

    private static String getTenPhim() {
        String tenPhim;
        while (true){
            try {
                System.out.println("Nhập tên phim muốn thêm suất chiếu");
                tenPhim = scanner.nextLine();
                if (tenPhim.trim().isEmpty()) throw new IllegalArgumentException("Tên phim không được để trống. Nhập lại");
                break;
            } catch (IllegalArgumentException e){
                System.out.println("Tên phim không được để trống. Nhập lại");
            }
        }
        return tenPhim;
    }
}
