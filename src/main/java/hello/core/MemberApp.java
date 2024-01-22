package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {

    //이렇게 만들어서 테스트 하는 것은 당연히 비효율적이고 메모리에 남으니 jUnit으로 테스트 할 것.
    public static void main(String[] args) {
        MemberService service = new MemberServiceImpl();
        Member member = new Member(1L, "Hyeonung", Grade.VIP);
        service.join(member);
        Member rMember = service.findMember(1L);

        System.out.println("member_name : " + member.getName());
        System.out.println("rMember_name :  " + rMember.getName());

    }
}
