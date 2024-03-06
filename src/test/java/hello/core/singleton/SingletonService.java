package hello.core.singleton;

public class SingletonService {

    //싱글톤은 자기 자신을 내부적으로 static형태로 가지고 있음
    private static final SingletonService instance = new SingletonService();
    public static SingletonService getInstance() {
        return instance;
    }

    //생성자를 private로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 하지 못하도록 막는다.(가장 중요!!)
    private SingletonService() {}

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
