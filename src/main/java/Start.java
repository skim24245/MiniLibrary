import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Start {
    public static void main(String[] args) throws IOException {
        ArrayList<Book> booklist = new ArrayList<>();
        ArrayList<Book> rentallist = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        BookUtil butil = new BookUtil();

        while (true) {
            System.out.println("*************메뉴를 선택하세요************");
            System.out.println("1.책 등록\t 2.책 검색\t  3.책 삭제 \t  4.책 전체 조회\t   5.책 대여\t   6.책 반납 \t" +
                    "7.종료 및 엑셀 저장");
            System.out.print("메뉴 입력 => ");

            int choice = sc.nextInt();
            if (choice == 1) {
                System.out.println("****************책 등록*****************");
                Book addedbook = new Book();
                butil.setBook(addedbook);
                booklist.add(addedbook);
                System.out.println("");
                System.out.println("***************************************");
                System.out.println("책 등록이 완료되었습니다");

            }
            if (choice == 2) {
                String a = sc.next();
                for (int i = 0; i < booklist.size(); i++) {
                    if (booklist.get(i).getTitle().equals(a)) {
                        System.out.println("찾으려는 책은 " + (i + 1) + "번째에 위치해있습니다");
                    }
                }

            }
            if (choice == 3) {
                String removingBook = sc.next();
                int idx = 0;
                for (Book book : booklist) {
                    if (book.getTitle().equals(removingBook)) {
                        booklist.remove(idx);
                        System.out.println(removingBook + " 책이 삭제되었습니다");
                        break;
                    }
                    idx++;
                }
            }
            if (choice == 4) {
                for (Book book : booklist) {
                    System.out.println("책 정보: " + book.getTitle() + "," + " " + "책 작가: " + book.getAuthor() + "," + " " + "책이름: " + book.getNum());

                }
                System.out.println("---------------------------------------");
                System.out.println("현재 도서관에는 총 " + booklist.size() + "권의 책이 있습니다.");

            }
            if (choice == 5) {
                String RentingBook = sc.next();
                for (int i = 0; i < booklist.size(); i++) {
                    if (booklist.get(i).getTitle().equals(RentingBook)) {
                        rentallist.add(booklist.get(i));
                        booklist.remove(i);
                    }
                }
                System.out.println(RentingBook + "을 대여합니다");
            }

            if (choice == 6) {
                String ReturningBook = sc.next();
                for (int i = 0; i < rentallist.size(); i++) {
                    if (rentallist.get(i).getTitle().equals(ReturningBook)) {
                        booklist.add(rentallist.get(i));
                        rentallist.remove(i);
                    }
                }
                System.out.println(ReturningBook + "책을 반납합니다");
            }
            if (choice == 7) {
                System.out.println("종료");
                int rows = booklist.size() + 1;
                FileInputStream fis = new FileInputStream("C:\\KOPOLibrary\\src\\main\\exceltest\\booklist.xls");
                HSSFWorkbook workbook = new HSSFWorkbook(fis);
                HSSFSheet sheet = workbook.getSheetAt(0);
                for (int i = 1; i < rows; i++) {
                    HSSFRow row = sheet.createRow(i);
                    for (int j = 0; j < 3; j++) {
                        HSSFCell cell = row.createCell(j);
                        if (j == 0) {
                            cell.setCellValue(booklist.get(i - 1).getTitle());
                        }
                        if (j == 1) {
                            cell.setCellValue(booklist.get(i - 1).getAuthor());
                        }
                        if (j == 2) {
                            cell.setCellValue(booklist.get(i - 1).getNum());
                        }
                    }
                }
                try {
                    FileOutputStream fileoutputstream = new FileOutputStream("C:\\KOPOLibrary\\src\\main\\exceltest\\booklist.xls");
                    workbook.write(fileoutputstream);
                    fileoutputstream.close();
                    System.out.println("****책의 정보를 엑셀 파일에서 확인해보세요****");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
    }
}






