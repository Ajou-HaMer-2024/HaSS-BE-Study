package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//service class는 비지니스에 가까운 용어를 사용해야 한다....
//role에 맞도록 이름을 설정해랴

//@Service //이걸 통해 spring이 service임을 확인하고 memberService에 등록해준다. -> service임을 알 수 있도록
public class MemberService {
    //회원 서비스를 만들기 위해 먼저, 회원 리포지토리가 필요함
    private final MemberRepository memberRepository;

    //@Autowired //repository와 연결(DI)
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    } //외부에서 넣어주도록(DI(dependency Injection)) 변경함 -> 생성자 주입 방식
    //기존 방식 :  private final MemberRepository memberRepository = new MemberRepository();
    //기존 방식은 test에서 new을 사용해 인스턴스를 생성하는 방식이기 때문에, test와 service에서 사용하는 객체는 다른 객체가 된다.
    //다른 인스턴스이기 때문에(다른 리포지토리를 사용하는 격) 내용물이 달라지는 등의 문제가 발생할 수 있다.

    //회원가입
    public Long join(Member member){

        /** 정리 전 코드
         * Optional<Member> result = memberRepository.findByName(member.getName()); //ctrl+alt+v(introduce variable)
         * //해당하는 값이 있다면 동작한다.(optional을 이용했기 때문에 이런 로직이 가능하다.)
        result.ifPresent(m -> { 
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }); **/

        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) { //하나의 method로 extract 했음 (ctrl+shift+alt+t)
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회웝입니다.");
                        });
    }

    /**
     *전체 회원 조회
     */

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> fineOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
