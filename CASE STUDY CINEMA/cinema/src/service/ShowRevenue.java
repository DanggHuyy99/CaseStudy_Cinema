package service;

import back.GoBack;
import model.Phim;
import model.Ve;

import java.util.ArrayList;

public class ShowRevenue {
    public void showRevenue(ArrayList<Phim> danhsachPhim, ArrayList<Ve> danhSachVe){
        System.out.println("---------------------------------TỔNG DOANH THU CỦA RẠP-----------------------------------");
        System.out.printf("%-25s%-25s%-25s", "Phim", "Số lượng vé bán ra", "Doanh thu");
        double tongDoanhThu = 0.0;
        for (Phim danhSachPhim : danhsachPhim){
            int count = 0;
            for (Ve listTicket : danhSachVe){
                if (danhSachPhim.getTenPhim().equalsIgnoreCase(listTicket.getPhim())){
                    tongDoanhThu += listTicket.getGiaVe();
                    count++;
                }
            }
            System.out.printf("\n%-25s%-25s%-25s", danhSachPhim.getTenPhim(), count, count * 45000);
        }
        System.out.println();
        System.out.printf("%30s%29s","TỔNG DOANH THU CỦA RẠP", tongDoanhThu);
        System.out.println("\n---------------------------------*********************-----------------------------------");
        GoBack.input0ToGoBack();
    }
}
