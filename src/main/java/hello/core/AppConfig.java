package hello.core;

import hello.core.Order.OrderService;
import hello.core.Order.OrderServiceImpl;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;

public class AppConfig {

    //생성자를 통해서 객체가 생성된게 들어가서 생성자 주입이라고 함.
    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
