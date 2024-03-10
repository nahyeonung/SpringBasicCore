package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor //필수 값인 final이 붙은 필드를 가지고 생성자를 만들어줌! 개꿀!! 필드 주입보다 더 깔끔해짐
public class OrderServiceImpl implements OrderService {

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //고정 할인 정책에서 비율 할인 정책으로 변경
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    /* 필드 생성자 주입 코드
        @Autowired private MemberRepository memberRepository;
        @Autowired private DiscountPolicy discountPolicy;

        //필드 주입을 할때는 테스트 코드에서 순수 자바로 접근하기 위해서 setter를 만들어줘야 한다. 아니면 필드에 주입할 방법이 없음
        public void setMemberRepository(MemberRepository memberRepository){
            this.memberRepository = memberRepository;
        }

        public void setDiscountPolicy(DiscountPolicy discountPolicy){
            this.discountPolicy = discountPolicy;
        }
    */
    
    /* 수정 생성자 주입
        private MemberRepository memberRepository;
        private DiscountPolicy discountPolicy;

        @Autowired
        public void setDiscountPolicy(DiscountPolicy discountPolicy){
            System.out.println("DD = " + discountPolicy);
            this.discountPolicy = discountPolicy;
        }
    
        @Autowired
        public void setMemberRepository(MemberRepository memberRepository){
            System.out.println("MM = " + memberRepository);
            this.memberRepository = memberRepository;
        }
     */

    /* 생성자 의존성 주입 */
        private final MemberRepository memberRepository;
        private final DiscountPolicy discountPolicy;


        // 이제 롬복의 @RequiredArgsConstructor 덕분에 아래 코드를 자동으로 생성해줌
        //스프링도 어쨌든 자바고 생성자를 호출하려면 객체를 생성해야 하기 때문에,
        //컴포넌트 스캔시 new OrderServiceImpl(memberRepository, discountPolicy)를 자동으로 해서 의존성 주입을 시켜준다고 보면 된다.
        @Autowired //의존성 자동 주입을 위한 어노테이션 ex)ac.getBean(), 생성자가 하나일 때 생략해도 무방하다!!
        public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy){
            this.memberRepository = memberRepository;
            this.discountPolicy = discountPolicy;
        }
        /**/
    /**/

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
