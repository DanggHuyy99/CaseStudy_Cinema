package back;

import java.util.Scanner;

public class GoBack {
    public static void input0ToGoBack(){
        Scanner scanner = new Scanner(System.in);
        int choice;
//        do {
//            System.out.println("Nhấn 0 để quay lại");
//            choice = Integer.parseInt(scanner.nextLine());
//        } while (choice != 0);
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
    }
}
