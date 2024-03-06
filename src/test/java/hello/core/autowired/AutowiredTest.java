package hello.core.autowired;

import hello.core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean{

        //일부러 스프링 빈이 등록되지 않은 Member를 가져옴
        //required = true면 Member가 스프링 빈에 등록되지 않은 인스턴스이기 때문에 null 에러 skr skr~
        @Autowired(required = false) //false면 자동 주입할 대상이 없을 시 null이 입력된다.
        public void setNoBean1(Member noBean1){
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2){//@Nullable은 호출은 되지만 null값이 담김
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3){//자동 주입할 대상이 없으면 Optional.empty가 입력된다.
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
