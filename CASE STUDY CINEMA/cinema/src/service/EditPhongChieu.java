package service;

import back.GoBack;
import csvfile.ReadCSVFile;
import model.Phim;
import model.PhongChieu;
import model.RapChieu;
import model.SuatChieu;
import view.CinemaView;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class EditPhongChieu {

    static String phongChieuPath = "data/phongchieu.csv";

    public void editPhongChieu(ArrayList<PhongChieu> danhSachPhongChieu, ArrayList<RapChieu> rapchieuPhim, ArrayList<Phim> danhsachPhim, ArrayList<SuatChieu> danhSachSuatChieu) {
        Scanner scanner = new Scanner(System.in);
        ReadCSVFile.getThongTinPhongChieu(danhSachPhongChieu, phongChieuPath);
        int choice;
        do {
            menuPhongChieu();
            choice = getChoice(scanner);
            switch (choice) {
                case 1:
                    showStatus(danhSachPhongChieu);
                    break;
                case 2:
                    editPhongChieu(danhSachPhongChieu, rapchieuPhim, danhsachPhim, danhSachSuatChieu, scanner);
                    break;
                case 0:
                    break;
            }
        } while (choice != 0);
    }

    private static int getChoice(Scanner scanner) {
        int choice;
        while (true){
            try {
                CinemaView.showChoice();
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >=0)break;
                else System.out.println("Nhập lại");
            } catch (IllegalArgumentException e){
                System.out.println("Nhập lại");
            }
        }
        return choice;
    }

    private static void editPhongChieu(ArrayList<PhongChieu> danhSachPhongChieu, ArrayList<RapChieu> rapchieuPhim, ArrayList<Phim> danhsachPhim, ArrayList<SuatChieu> danhSachSuatChieu, Scanner scanner) {
        try {
//            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(phongChieuPath));

            Integer maPhong = getMaPhong(rapchieuPhim, scanner);
            if (maPhong == null) return;

            int tongSoGhe = getTongSoGhe(scanner);

            Integer soGheTrong = getSoGheTrong(scanner, maPhong, tongSoGhe);
            if (soGheTrong == null) return;

            String phimChieu = getPhimCHIEU(danhsachPhim, scanner);
            if (phimChieu == null) return;

            String ngayChieu = getNgayCHieu(danhSachSuatChieu, scanner, phimChieu);
            if (ngayChieu == null) return;

            String gioChieu = getGioCHieu(danhSachSuatChieu, scanner, phimChieu, ngayChieu);
            if (gioChieu == null) return;

            checkThongTin(danhSachPhongChieu, maPhong, tongSoGhe, soGheTrong, phimChieu, ngayChieu, gioChieu);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void checkThongTin(ArrayList<PhongChieu> danhSachPhongChieu, Integer maPhong, int tongSoGhe, Integer soGheTrong, String phimChieu, String ngayChieu, String gioChieu) throws IOException {
        Scanner scanner = new Scanner(System.in);
//        boolean checkLoop = false;
        for (int j = 0; j < danhSachPhongChieu.size(); j++) {
            PhongChieu chieu = danhSachPhongChieu.get(j);
            if (maPhong == chieu.getMaPhong() && chieu.getNgayChieu().equalsIgnoreCase(ngayChieu) && chieu.getGioChieu().equalsIgnoreCase(gioChieu)) {
                System.out.println("Phòng chiếu đã có suất chiếu vào giờ này");
                break;
            }
            if (maPhong == chieu.getMaPhong() && chieu.getPhimChieu().equalsIgnoreCase(phimChieu) && chieu.getNgayChieu().equalsIgnoreCase(ngayChieu)) {
                int choice;
                do {
                    System.out.println("Bạn muốn thêm suất chiếu cho phim " + phimChieu + " hay xoá suất chiếu của phim " + phimChieu + " vào " + ngayChieu);
                    System.out.println("1. Xoá");
                    System.out.println("2. Thêm");
                    choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            ArrayList<PhongChieu> listPhongChieu = new ArrayList<>();
                            System.out.println("Các suất chiếu của phim " + phimChieu + " vào " + ngayChieu);
                            for (PhongChieu thongTinGioChieu : danhSachPhongChieu) {
                                if (thongTinGioChieu.getMaPhong() == maPhong && thongTinGioChieu.getPhimChieu().equalsIgnoreCase(phimChieu) && thongTinGioChieu.getNgayChieu().equalsIgnoreCase(ngayChieu)) {
                                    System.out.println("♣♣ " + thongTinGioChieu.getGioChieu());
                                    listPhongChieu.add(thongTinGioChieu);
                                }
                            }
                            String time_delete;
                            boolean check_time = false;
                            do {
                                System.out.println("Bạn muốn xoá suất chiếu nào");
                                time_delete = scanner.nextLine();
                                for (int i = 0; i < listPhongChieu.size(); i++) {
                                    PhongChieu thongTinPhongChieu = listPhongChieu.get(i);
                                    if (thongTinPhongChieu.getGioChieu().equalsIgnoreCase(time_delete)) {
                                        listPhongChieu.remove(thongTinPhongChieu);
                                        danhSachPhongChieu.remove(thongTinPhongChieu);
                                        check_time = true;
                                        System.out.println("Đã xoá suất chiếu " + thongTinPhongChieu.getGioChieu() + " vào " + thongTinPhongChieu.getNgayChieu() + " của phim " + thongTinPhongChieu.getPhimChieu() + " thành công");
                                        break;
                                    }
                                }
                            } while (!check_time);
                            break;
                        case 2:
                            break;
                    }
                } while (choice != 1 && choice != 2);
                break;
            }
        }
        addPhongChieu(danhSachPhongChieu, maPhong, tongSoGhe, soGheTrong, phimChieu, ngayChieu, gioChieu);
    }

    private static void addPhongChieu(ArrayList<PhongChieu> danhSachPhongChieu, Integer maPhong, int tongSoGhe, Integer soGheTrong, String phimChieu, String ngayChieu, String gioChieu) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(phongChieuPath));

        PhongChieu phongChieu = new PhongChieu(maPhong, tongSoGhe, soGheTrong, phimChieu, ngayChieu, gioChieu);
        danhSachPhongChieu.add(phongChieu);
        System.out.println("♠ Đã sửa thông tin thành công");

        for (PhongChieu thongtinPhongChieu : danhSachPhongChieu) {
            bufferedWriter.write(thongtinPhongChieu.getMaPhong() + ","
                    + thongtinPhongChieu.getTongSoGhe() + ","
                    + thongtinPhongChieu.getSoGheTrong() + ","
                    + thongtinPhongChieu.getPhimChieu() + ","
                    + thongtinPhongChieu.getNgayChieu() + ","
                    + thongtinPhongChieu.getGioChieu());
            bufferedWriter.newLine();
        }
        bufferedWriter.close();

    }

    private static String getGioCHieu(ArrayList<SuatChieu> danhSachSuatChieu, Scanner scanner, String phimCHIEU, String ngayCHieu) {
        System.out.println("Các suất chiếu theo giờ của phim " + phimCHIEU + " vào " + ngayCHieu);
        for (SuatChieu thongTinSuatChieu : danhSachSuatChieu) {
            if (phimCHIEU.equals(thongTinSuatChieu.getPhimChieu())) {
                System.out.print(thongTinSuatChieu.getGioChieu() + "   ||   ");
            }
        }
        boolean checkGioChieu = false;
        System.out.println("\nNhập giờ chiếu (HH:mm:ss)");
        String gioCHieu = scanner.nextLine();
        for (SuatChieu thongtinSuatChieu : danhSachSuatChieu) {
            if (phimCHIEU.equals(thongtinSuatChieu.getPhimChieu())) {
                if (gioCHieu.equals(thongtinSuatChieu.getGioChieu())) {
                    checkGioChieu = true;
                }
            }
        }
        if (!checkGioChieu) {
            System.out.println("Không có suất chiếu phim " + phimCHIEU + " vào " + ngayCHieu + " suất " + gioCHieu);
            return null;
        }
        return gioCHieu;
    }

    private static String getNgayCHieu(ArrayList<SuatChieu> danhSachSuatChieu, Scanner scanner, String phimCHIEU) {
        System.out.println("Các suất chiếu theo ngày của phim " + phimCHIEU);
        for (SuatChieu thongTinSuatChieu : danhSachSuatChieu) {
            if (phimCHIEU.equals(thongTinSuatChieu.getPhimChieu())) {
                System.out.print(thongTinSuatChieu.getNgayChieu() + "   ||   ");
            }
        }
        boolean checkNgayChieu = false;
        System.out.println("\nNhập ngày chiếu (dd/mm/yyyy)");
        String ngayCHieu = scanner.nextLine();
        for (SuatChieu thongtinSuatChieu : danhSachSuatChieu) {
            if (phimCHIEU.equals(thongtinSuatChieu.getPhimChieu())) {
                if (ngayCHieu.equals(thongtinSuatChieu.getNgayChieu())) {
                    checkNgayChieu = true;
                }
            }
        }
        if (!checkNgayChieu) {
            System.out.println("Không có suất chiếu phim " + phimCHIEU + " vào " + ngayCHieu);
            return null;
        }
        return ngayCHieu;
    }

    private static String getPhimCHIEU(ArrayList<Phim> danhsachPhim, Scanner scanner) {
        System.out.println("Danh sách các phim đang chiếu tại rạp");
        for (Phim phimchieu : danhsachPhim) {
            System.out.print("♣ " + phimchieu.getTenPhim() + "    ");
        }
        boolean checkPhimChieu = false;
        String phimChieu;
        while (true){
            try {
                System.out.println("\nNhập phim chiếu");
                phimChieu = scanner.nextLine();
                if (phimChieu.trim().isEmpty()){
                    throw new IllegalArgumentException("Không dược để trống. Nhập lại");
                }
                break;
            } catch (IllegalArgumentException e){
                System.out.println("Tên phim không được để trống. Nhập lại");
            }
        }
        for (Phim phimchieu : danhsachPhim) {
            if (phimChieu.equals(phimchieu.getTenPhim())) {
                checkPhimChieu = true;
                break;
            }
        }
        if (!checkPhimChieu) {
            System.out.println("Không có phim " + phimChieu + " trong danh sách");
            return null;
        }
        return phimChieu;
    }

    private static Integer getSoGheTrong(Scanner scanner, Integer maPhong, int tongSoGhe) {
        int soGheTrong;
        while (true){
            try {
                System.out.println("Nhập số ghế trống:");
                soGheTrong = Integer.parseInt(scanner.nextLine());
                if (soGheTrong > 0) break;
                else System.out.println("Số ghế trống phải lớn hơn 0");

            } catch (IllegalArgumentException e){
                System.out.println("Không đúng định dạng số. Vui lòng nhập lại");
            }
        }
        if (soGheTrong > tongSoGhe) {
            System.out.println("Phòng chiếu " + maPhong + " chỉ có " + tongSoGhe + " ghế");
            return null;
        }
        return soGheTrong;
    }

    private static int getTongSoGhe(Scanner scanner) {
        System.out.println("Nhập tổng số ghế");
        return Integer.parseInt(scanner.nextLine());
    }

    private static Integer getMaPhong(ArrayList<RapChieu> rapchieuPhim, Scanner scanner) {
        int maPhong;
        while (true){
            try {
                System.out.println("Nhập mã phòng:");
                maPhong = Integer.parseInt(scanner.nextLine());
                if (maPhong > 0) break;
                else System.out.println("Mã phòng phải lớn hơn 0");

            } catch (IllegalArgumentException e){
                System.out.println("Không đúng định dạng mã phòng. Vui lòng nhập lại");
            }
        }
//        System.out.println("Nhập mã phòng");
//        int maPhong = Integer.parseInt(scanner.nextLine());
        boolean checkMaPhong = true;
        for (RapChieu thongTinSoPhong : rapchieuPhim) {
            if (maPhong > thongTinSoPhong.getSoPhongChieu()) {
                System.out.println("Rạp chỉ có " + thongTinSoPhong.getSoPhongChieu() + " phòng");
                checkMaPhong = false;
                break;
            }
        }
        if (!checkMaPhong) {
            return null;
        }
        return maPhong;
    }

    private static void showStatus(ArrayList<PhongChieu> danhSachPhongChieu) {
        ReadCSVFile.getThongTinPhongChieu(danhSachPhongChieu, phongChieuPath);
        System.out.println(" * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s\n", "Mã phòng", "Tổng số ghế", "Số ghế trống", "Phim Chiếu", "Ngày Chiếu", "Giờ Chiếu");
        for (PhongChieu thongtinPhongChieu : danhSachPhongChieu) {
            System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s\n",
                    thongtinPhongChieu.getMaPhong(),
                    thongtinPhongChieu.getTongSoGhe(),
                    thongtinPhongChieu.getSoGheTrong(),
                    thongtinPhongChieu.getPhimChieu(),
                    thongtinPhongChieu.getNgayChieu(),
                    thongtinPhongChieu.getGioChieu());
        }
        System.out.println(" * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println();
        GoBack.input0ToGoBack();
    }

    private static void menuPhongChieu() {
        System.out.println(" * * * * * * * * * * * * * * * * * ");
        System.out.println("1. Trạng thái phòng chiếu");
        System.out.println("2. Sửa phòng chiếu");
        System.out.println("0. Quay lại menu chính");
        System.out.println(" * * * * * * * * * * * * * * * * * ");
    }
}
