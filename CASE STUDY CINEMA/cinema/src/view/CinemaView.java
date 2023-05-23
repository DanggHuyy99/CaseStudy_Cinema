package view;

import back.GoBack;
import csvfile.ReadCSVFile;
import service.*;
import model.*;
import model.Phim;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CinemaView {
    public static Scanner scanner = new Scanner(System.in);

    static java.lang.String csvFilePath = "data/film.csv";

    static String rapChieuPath = "data/rapchieu.csv";

    static String suatChieuPath = "data/suatchieu.csv";

    static String phongChieuPath = "data/phongchieu.csv";

    static String vePath = "data/ve.csv";

    static ArrayList<Phim> danhsachPhim = new ArrayList<Phim>();

    static ArrayList<RapChieu> rapchieuPhim = new ArrayList<RapChieu>();

    static ArrayList<SuatChieu> danhSachSuatChieu = new ArrayList<SuatChieu>();

    static ArrayList<PhongChieu> danhSachPhongChieu = new ArrayList<PhongChieu>();

    static ArrayList<Ve> danhSachVe = new ArrayList<Ve>();

    Phim phim;
    static RapChieu rapChieu;

    public void showMenu() {
        ReadCSVFile.getDanhSachPhimCSV(danhsachPhim, csvFilePath);
        ReadCSVFile.getThongTinRapChieu(rapchieuPhim, rapChieuPath);
        ReadCSVFile.getDanhSachSuatChieu(danhSachSuatChieu, suatChieuPath);
        ReadCSVFile.getThongTinPhongChieu(danhSachPhongChieu, phongChieuPath);
        ReadCSVFile.getDanhSachVe(danhSachVe, vePath);
        java.lang.String choice;
        EditFilm editFilm = new EditFilm();
        EditRapChieu editRapChieu = new EditRapChieu();
        EditPhongChieu editPhongChieu = new EditPhongChieu();
        SellTicket sellTicket = new SellTicket();
        ShowRevenue showRevenue = new ShowRevenue();
        do {
            ShowMainMenu.menu();
            showChoice();
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    editFilm.editPhim(danhsachPhim);
                    break;
                case "2":
                    editRapChieu.editSuatChieu(danhSachSuatChieu, danhsachPhim, rapchieuPhim);
                    break;
                case "3":
                    if (sellTicket.ticketSales(danhsachPhim, danhSachSuatChieu, danhSachPhongChieu, danhSachVe)) break;
                    GoBack.input0ToGoBack();
                    break;
                case "4":
                    editPhongChieu.editPhongChieu(danhSachPhongChieu, rapchieuPhim, danhsachPhim, danhSachSuatChieu);
                    break;
                case "5":
                    showRevenue.showRevenue(danhsachPhim, danhSachVe);
                    break;
                case "0":
                    System.out.println("Thoát chương trình");
                    System.exit(0);
            }
        } while (true);
    }

    public static void showChoice() {
        System.out.println("Mời chọn:");
    }

}
