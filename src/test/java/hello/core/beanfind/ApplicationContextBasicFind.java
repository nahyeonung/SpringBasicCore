package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBasicFind {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println(ac.getBean("memberService", MemberService.class));
        System.out.println("memberService : " + memberService);
        System.out.println("memberService.class : " + memberService.getClass());
        System.out.println("memberServiceImpl.class : " + MemberServiceImpl.class);
        //우리는 memberService bean에 memberServiceImpl을 리턴해놨기 때문에 memberServiceImpl.class와 비교
        //MemberService.class와 비교해도 됨 어짜피 MemberServiceImpl이 나옴
        assertThat(memberService).isInstanceOf(MemberService.class);
    }

    @Test
    @DisplayName("이름없이 타입으로만 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2(){
        //아래와 같이 구현체로 빈 조회가 가능하지만, 이렇게 되면 역할과 구현 중 구현에 의존하게 되므로 별로 좋진 않음(유연성이 떨어짐)
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX(){
        //람다식 우항의 결과가 좌항의 결과와 같으면 성공(즉, NoSuchBeanDefinitionException error가 발생하면 성공)
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxx", MemberService.class));
    }
}
