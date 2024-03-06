package hello.core.order;

import hello.core.AppConfig;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    OrderService orderservice;
    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        orderservice = appConfig.orderService();
        memberService = appConfig.memberService();
    }

    @Test
    void order() {
        //given
        memberService.join(new Member(1L, "Hyeonung", Grade.VIP));

        //when
        Order order = orderservice.createOrder(1L, "itemA", 10000);

        //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

//    @Test
//    void fieldInjectionTest() {
//        //의존성 주입을 필드로 하게되면 스프링이 아닌 순수 자바로 객체를 생성하고 사용하려고 하면 필드에 값이 주입되지 않기 때문에 nullPointerException 발생한다.(setter로 주입시켜주면 됨)
//        //순수 자바로 객체를 생성해서 접근하지 않으면 되지 않을까? 라는 나의 순 수 한 생각이 든다. => 테스트코드를 작성할 때 문제가 되서 그런가?!
//        memberService.join(new Member(1L, "Hyeonung", Grade.VIP));
//
//        OrderServiceImpl orderService = new OrderServiceImpl();
//        orderService.setDiscountPolicy(new FixDiscountPolicy());
//        orderService.setMemberRepository(new MemoryMemberRepository());
//        orderService.createOrder(1L, "itemA", 10000);
//    }
}
