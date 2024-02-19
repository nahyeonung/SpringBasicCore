package hello.core;

import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
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
    /*
    @Bean memberService -> new MemoryMemberRepository()를 호출해줌.
    @Bean orderService -> new MemoryMemberRepository()를 호출해줌.
    결과적으로 다른 2개의 MemoryMemberRepository가 생성되면서 싱글톤이 깨지는 것 처럼 보인다.
    스프링 컨테이너는 이 문제를 어떻게 해결할까?
    */


    //예상 호출
    //call AppConfig.memberService -> call AppConfig.memberRepository
    //call AppConfig.memberRepository
    //call AppConfig.orderService -> call AppConfig.memberRepository

    //호출 결과 (@Configuration으로 AppConfig 작성시)
    //call AppConfig.memberService
    //call AppConfig.memberRepository (예상대로라면 얘가 3번 호출되어야 하는데 스프링 컨테이너가 싱클턴을 보장해주고 있기 때문에 한 번만 호출된다.)
    //call AppConfig.orderService

    //호출 결과(@Configuration을 사용하지 않고 @Bean으로만 AppConfig 작성시 => 싱글턴 보장이 안됨)
    //call AppConfig.memberService -> call AppConfig.memberRepository
    //call AppConfig.memberRepository
    //call AppConfig.orderService -> call AppConfig.memberRepository
    @Bean
    //생성자를 통해서 객체가 생성된게 들어가서 생성자 주입이라고 함.
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
