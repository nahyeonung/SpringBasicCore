package hello.core;

import hello.core.Order.OrderService;
import hello.core.Order.OrderServiceImpl;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Spring에서는 설정정보(구성정보)에 @Configuration을 적어주게 되어있음
@Configuration
public class AppConfig {

    //@Bean을 적어주면 해당 메서드들이 Spring Container에 등록됨.
    @Bean
    //생성자를 통해서 객체가 생성된게 들어가서 생성자 주입이라고 함.
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
