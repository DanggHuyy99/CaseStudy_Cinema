package csvfile;

import model.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadCSVFile {
    Scanner scanner = new Scanner(System.in);

    public static void getDanhSachVe(ArrayList<Ve> danhSachVe, String veCSV){
        try {
            danhSachVe.clear();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(veCSV));

            String line = bufferedReader.readLine();
            while (line != null){
                String[] ve = line.split(",");
                int soGhe = Integer.parseInt(ve[0]);
                double giaVe = Double.parseDouble(ve[1]);
                String tenPhim = ve[2];
                String ngayChieu = ve[3];
                String gioChieu = ve[4];
                Ve ticket = new Ve(soGhe, giaVe, tenPhim, ngayChieu, gioChieu);
                danhSachVe.add(ticket);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void getDanhSachPhimCSV(ArrayList<Phim> danhSachPhim, java.lang.String csvFilePath) {
        try {
            danhSachPhim.clear();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFilePath));

            java.lang.String line = bufferedReader.readLine();
            while (line != null) {
                java.lang.String[] phim = line.split(",");
                java.lang.String tenPhim = phim[0];
                java.lang.String theLoai = phim[1];
                java.lang.String daoDien = phim[2];
                java.lang.String dienVien = phim[3];
                int thoiLuong = Integer.parseInt(phim[4]);
                int namSanXuat = Integer.parseInt(phim[5]);
                java.lang.String ngonNgu = phim[6];
                java.lang.String moTa = phim[7];
                Phim phim1 = new Phim(tenPhim, theLoai, daoDien, dienVien, thoiLuong, namSanXuat, ngonNgu, moTa);
                danhSachPhim.add(phim1);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();

        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Không tìm thấy file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   public static void getThongTinRapChieu(ArrayList<RapChieu> rapChieuPhim, java.lang.String suatChieuCSV){
        try{
            rapChieuPhim.clear();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(suatChieuCSV));

            java.lang.String line = bufferedReader.readLine();
            while (line != null){
                java.lang.String[] rapChieu = line.split(",");
                java.lang.String tenRap = rapChieu[0];
                java.lang.String diaChi = rapChieu[1];
                int soPhong = Integer.parseInt(rapChieu[2]);
                RapChieu rapChieu1 = new RapChieu(tenRap, diaChi, soPhong);
                rapChieuPhim.add(rapChieu1);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (FileNotFoundException fileNotFoundException){
            fileNotFoundException.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
   }

   public static void getThongTinPhongChieu(ArrayList<PhongChieu> phongChieuPhim, String phongChieuCSV){
        try {
            phongChieuPhim.clear();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(phongChieuCSV));

            String line = bufferedReader.readLine();
            while (line != null){
                String[] phongChieu = line.split(",");
                int maPhong = Integer.parseInt(phongChieu[0]);
                int tongSoGhe = Integer.parseInt(phongChieu[1]);
                int soGheTrong = Integer.parseInt(phongChieu[2]);
                String phimChieu = phongChieu[3];
                String ngayChieu = phongChieu[4];
                String gioChieu = phongChieu[5];
                PhongChieu phongChieu1 = new PhongChieu(maPhong, tongSoGhe, soGheTrong, phimChieu, ngayChieu, gioChieu);
                phongChieuPhim.add(phongChieu1);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
   }

    public static void getDanhSachSuatChieu(ArrayList<SuatChieu> danhSachSuatChieu, java.lang.String suatChieuCSV){
        try{
            danhSachSuatChieu.clear();

            BufferedReader bufferedReader = new BufferedReader(new FileReader(suatChieuCSV));

            String line = bufferedReader.readLine();
            while (line != null){
                String[] dsSuatChieu = line.split(",");
                String ngayChieu = (dsSuatChieu[0]);
                String gioChieu = (dsSuatChieu[1]);
                String tenPhim = dsSuatChieu[2];
                SuatChieu suatChieu = new SuatChieu(ngayChieu, gioChieu, tenPhim);
                danhSachSuatChieu.add(suatChieu);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (FileNotFoundException fileNotFoundException){
            fileNotFoundException.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }


}
