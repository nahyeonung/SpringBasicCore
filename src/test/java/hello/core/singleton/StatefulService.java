package hello.core.singleton;

public class StatefulService {

    //private int price; //상태를 유지하는 필드
    public int order(String name, int price) {
        System.out.println("name = " + name + "price = " + price);
        //this.price = price; //여기가 문제!
        return price; //싱글턴을 무상태로 만들기
    }

    public int getPrice() {
        //return price;
        return 1; //error 제거 임시 코드
    }
}
