package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

//콜백 인터페이스 사용
//public class NetworkClient implements InitializingBean, DisposableBean {
public class NetworkClient  {

    private String url;

    public NetworkClient(){System.out.println("생성자 호, url = " + url);}

    public void setUrl(String url){this.url = url;}

    //서비스 시작시 호출
    public void connect() {System.out.println("connect: " + url);}

    public void call(String message){System.out.println("call:  " + url + "message: " + message);}

    //서비스 종료시 호출
    public void disconnect() {System.out.println("close: " + url);}

    @PostConstruct
    public void init() throws Exception {
        //의존관계 주입이 끝나면 호출해 주겠다는 메소드
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }

    //콜백 인터페이스 사용
/*    @Override
    public void afterPropertiesSet() throws Exception {
        //의존관계 주입이 끝나면 호출해 주겠다는 메소드
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }*/


}
