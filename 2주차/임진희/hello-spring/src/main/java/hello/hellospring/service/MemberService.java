package hello.hellospring.service;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// @component 로 해도 됨
public class MemberService {
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    // 직접 생성하는 게 아니라 외부에서 생성 dependency rejection

    /**
     * 회원 가입
     */
    public Long join(Member member){
        // 같은 회원이 있는 중복 회원 X
        Optional<Member> result = memberRepository.findByName(member.getName());


        // result.get() 직접 꺼내고 싶을 때 사용하나 권장하지 않음
        vailddateDuplicateMember(member); // 중복회원 검증
            
        memberRepository.save(member);
        return member.getId();
    }

    private void vailddateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberID){
        return  memberRepository.findByID(memberID);
    }
}
