package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService service = new MemberServiceImpl();

    @Test
    void join() {
        //given 주어지는 것
        Member member = new Member(1L, "Hyeonung", Grade.VIP);

        //when 실행하는 것
        service.join(member);
        Member findMember = service.findMember(1L);

        //then 결과
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
