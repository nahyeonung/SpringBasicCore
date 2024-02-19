package hello.core.singleton;

import hello.core.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 =  ac.getBean(StatefulService.class);
        StatefulService statefulService2 =  ac.getBean(StatefulService.class);

        /*
        * 우리는 A라는 사용자가 10000원을 주문하고 주문 내역을 확인하려고 할 때, B라는 사용자가 20000원을 주문해서
        * 끼어들기 된 상황일 때, 싱글턴에서는 어떤 상황이 발생하는지 테스트 해본다.
        * */
        //ThreadA: A사용자 10000원 주문
        statefulService1.order("userA", 10000);
        //ThreadB: B사용자 20000원 주문
        statefulService2.order("userB", 20000);

        /*
        * A의 주문 내역은 10000원이 나와야 하지만 끼어들기에 당해버려서 B의 주문 내역인 20000원이 나온다.
        * 스프링 빈의 필드(싱글턴 객체 필드)에 공유 값을 설정했을 때 발생하는 에러
        * */
        //ThreadA: 사용자A 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price= " + price);

        //싱글턴 무상태로 교체 후 코드
        int userAPrice = statefulService1.order("userA", 10000);
        int userBPrice = statefulService2.order("userB", 20000);

        //원하는 결과가 나옴
        System.out.println("price = "  + userAPrice);
    }

    static class TestConfig{

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }

    }
}