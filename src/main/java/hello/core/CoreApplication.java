package hello.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(
		// basePackages = "hello.core.member" => 컴포넌트 스캔을 시작할 위치 지정. 지정하지 않으면 @ComponentScan이 붙은 파일 패키지가 기준
		//우리가 앞선 예제를 하기 위해 수동 등록하는 AppConfig파일은 자동으로 bean등록 해주면 안되기 때문에 예외 처리
		excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class  CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

}
