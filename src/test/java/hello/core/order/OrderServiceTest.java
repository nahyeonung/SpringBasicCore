package hello.core.order;

import hello.core.Order.Order;
import hello.core.Order.OrderService;
import hello.core.Order.OrderServiceImpl;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    OrderService orderservice = new OrderServiceImpl();
    MemberService memberService = new MemberServiceImpl();

    @Test
    void order() {
        //given
        memberService.join(new Member(1L, "Hyeonung", Grade.VIP));

        //when
        Order order = orderservice.createOrder(1L, "itemA", 10000);

        //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
