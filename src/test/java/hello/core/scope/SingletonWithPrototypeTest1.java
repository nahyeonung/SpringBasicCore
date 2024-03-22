package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2 .getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    //@RequiredArgsConstructor
    static class ClientBean{
        //prototypeBean이 생성시점에 주입되고 이후에는 싱글턴 스코프에서 주입된 참조값을 가지고 사용하기 때문에
        //prototypeBean에 접근한다고 해도 더 이상 생성되지 않는다.
        //private final PrototypeBean prototypeBean; // 생성자 주입 코드

        /*ObjectProvider 사용 코드
        @Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanProvider;*/

//        @Autowired
//        private ApplicationContext ac;

        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;

        public int logic(){
            //PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);//logic 메소드를 호출할 때마다 prototype scope인 빈을 생성시키는 무식한 방법

            //PrototypeBean prototypeBean = prototypeBeanProvider.getObject();//ObjectProvider 사용 코드
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("prototypeBean.destroy");
        }
    }
}
