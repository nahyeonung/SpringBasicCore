package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    //memberRepository에 구현체가 뭐가 들어갈지 생성자를 통해서 식별
    //이제 MemberServiceImpl은 memberRepository의 구현체에 대해서 모름(의존 X)
    /* 수동으로 bean 등록을 해줄 때 의존관계 주입을 위한 생성자 코드
    public MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
     */
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }


    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId); 
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
