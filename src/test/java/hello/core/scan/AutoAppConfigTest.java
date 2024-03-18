package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.autowired.AllBeanTest;
import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AutoAppConfigTest {

    @Test
    void basicScan(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("bean name : " + beanDefinitionName + " bean : " + bean);
        }

//        MemberService memberService = ac.getBean(MemberService.class);
//        assertThat(memberService).isInstanceOf(MemberService.class);
//
//        MemberService memberService1 = ac.getBean(MemberServiceImpl.class);
//        assertThat(memberService1).isInstanceOf(MemberServiceImpl.class);
//
//        System.out.println(memberService);
//        System.out.println(memberService1);
//

    }
}
