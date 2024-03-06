package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceImplTest {

    @Test
    void createOrder() {

        /*생성자 주입을 사용해서 테스트해본 코드=>생성자 주입은 애초에 컴파일 에러가 발생하기 때문에 에러를 바로 잡을 수 있음*/
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);
        /**/
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

        /*수정자 주입을 통해 다음과 같이 테스트를 해보면 의존 관계 주입 누락으로 NullPointerException 에러가 발생함
        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
        orderService.createOrder(1L, "itemA", 10000);
         */
    }
}
