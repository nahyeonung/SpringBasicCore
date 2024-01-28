package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    //이렇게 만들어서 테스트 하는 것은 당연히 비효율적이고 메모리에 남으니 jUnit으로 테스트 할 것.
    public static void main(String[] args) {
/*        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();*/

        //ApplicationContext가 spring container라고 생각하면됨. 그래서 스프링은 대부분 이걸로 시작
        /*AnnotationConfigApplicationContext: 우리가 config파일에서 빈 등록은 annotation으로 했기 때문에 사용.
                                              => 그래서 파라미터로 AppConfig 클래스를 주입 */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //AppConfig에서 memberService이름으로 등록된 빈을 꺼내는 코드
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        //MemberService service = new MemberServiceImpl();
        Member member = new Member(1L, "Hyeonung", Grade.VIP);
        memberService.join(member);
        Member rMember = memberService.findMember(1L);

        System.out.println("member_name : " + member.getName());
        System.out.println("rMember_name :  " + rMember.getName());

    }
}
