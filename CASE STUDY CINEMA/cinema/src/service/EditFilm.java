package service;

import csvfile.WriteCSVFile;
import model.Phim;
import model.QuanLyRapChieu;

import java.util.ArrayList;
import java.util.Scanner;

public class EditFilm {
    public void editPhim(ArrayList<Phim> danhsachPhim) {
        java.lang.String csvFilePath = "data/film.csv";
        Scanner scanner = new Scanner(System.in);
        Phim phim;
        int choice;
        do {
            menuQuanLyPhim();
            while (true){
                try {
                    showChoice();
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice >= 0) break;
                    else System.out.println("Nhập lại");
                } catch (IllegalArgumentException e){
                    System.out.println("Nhập lại");
                }
            }
            QuanLyRapChieu quanLyRapChieu = new QuanLyRapChieu();
            switch (choice) {
                case 1:
                    showList(danhsachPhim, scanner);
                    break;
                case 2:
                    createAndDeleteFilm(danhsachPhim, csvFilePath, scanner);
                    break;
                case 0:
                    break;
            }
        } while (choice != 0);
    }

    private static void showList(ArrayList<Phim> danhsachPhim, Scanner scanner) {
        int choice;
        do {
            showDanhSach(danhsachPhim);
            System.out.println("\nNhập 0 để quay lại");
            choice = Integer.parseInt(scanner.nextLine());
        } while (choice != 0);
    }

    private static void createAndDeleteFilm(ArrayList<Phim> danhsachPhim, String csvFilePath, Scanner scanner) {
        int choice;
        do {
            menuEditFilm();
            showChoice();
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    WriteCSVFile.writeCSVFile(danhsachPhim, csvFilePath);
                    choice = 0;
                    break;
                case 2:
                    String phim_muon_xoa = getPhimMuonXoa(scanner);
                    QuanLyRapChieu.xoaPhim(danhsachPhim, phim_muon_xoa);
                    WriteCSVFile.writeCSVAfterDelete(danhsachPhim, csvFilePath);
                    choice = 0;
                    break;
                case 0:
                    System.out.println("Quay lại");
                    break;
            }
        } while (choice != 0);
    }

    private static String getPhimMuonXoa(Scanner scanner) {
        String input;
        while (true) {
            try {
                System.out.println("Chọn phim muốn xoá:");
                input = scanner.nextLine();

                if (input.trim().isEmpty()) {
                    throw new IllegalArgumentException("Không được để trống");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Không được để trống. Nhập lại");
            }
        }
        return input;
    }


    private static void showDanhSach(ArrayList<Phim> danhsachPhim) {
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tDANH SÁCH PHIM ĐANG CHIẾU TẠI CÁC RẠP:");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.printf("%-25s %-25s %-25s %-25s %-25s %-25s %-25s %-25s", "Tên phim", "Thể loại", "Đạo diễn", "Diễn viên chính", "Thời lượng(phút)", "Năm sản xuất", "Ngôn ngữ", "Mô tả");
        System.out.println();
        for (Phim list : danhsachPhim) {
            System.out.printf("%-25s %-25s %-25s %-25s %-25s %-25s %-25s %-25s", list.getTenPhim(), list.getTheLoai(), list.getDaoDien(), list.getDienVienChinh(), list.getThoiLuong(), list.getNamSanXuat(), list.getNgonNgu(), list.getMoTa());
            System.out.println();
        }
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
    }

    public static void showChoice() {
        System.out.println("Mời chọn:");
    }

    public static void menuQuanLyPhim() {
        System.out.println("* * * * * * * * * * * * * * * * * * *");
        System.out.println("\t1. Danh sách phim đang chiếu");
        System.out.println("\t2. Sửa phim");
        System.out.println("\t0. Quay lại");
        System.out.println("* * * * * * * * * * * * * * * * * * *");
    }

    public static void menuEditFilm() {
        System.out.println("* * * * * * * * * * * * * * * * * * *");
        System.out.println("\t1. Thêm phim");
        System.out.println("\t2. Xoá phim");
        System.out.println("\t0. Quay lại");
        System.out.println("* * * * * * * * * * * * * * * * * * *");
    }
}
