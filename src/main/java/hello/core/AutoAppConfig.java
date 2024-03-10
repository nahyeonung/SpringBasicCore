package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


//spring에서 bean을 자동으로 스캔해서 등록해주는 어노테이션
@ComponentScan(
        // basePackages = "hello.core.member" => 컴포넌트 스캔을 시작할 위치 지정. 지정하지 않으면 @ComponentScan이 붙은 파일 패키지가 기준
        //우리가 앞선 예제를 하기 위해 수동 등록하는 AppConfig파일은 자동으로 bean등록 해주면 안되기 때문에 예외 처리
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

//    @Bean(name = "memoryMemberRepository")
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }

}
