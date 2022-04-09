import java.util.Scanner;
import java.util.*;

public class BookUtil { //책의 제목 ,작가, 번호 등 정보를 담은 책목록 리스트
    public void setBook(Book book) {//setting을 해줘야지 속성 값을 설정할 수 있다
        Scanner sc = new Scanner(System.in);
        System.out.println("책 제목을 입력하세요");
        String nowtitle = sc.next();
        book.setTitle(nowtitle);
        System.out.println("책 저자를 입력하세요");
        String nowAuthor = sc.next();
        sc.nextLine();
        book.setAuthor(nowAuthor);
        System.out.println("책 번호를 입력하세요");
        int nowNum = sc.nextInt();
        book.setNum(nowNum);
    }
}
