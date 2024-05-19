package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

//@ServiceSpring Container에 서비스를 등록함
public class MemberService {

    private final MemberRepository memberRepository;

    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /** 회원가입 **/
    public Long join(Member member) {
        getIfPresent(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void getIfPresent(Member member) {
        //중복 회원 검증
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }
    
    //전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findALL();
    }

    public Optional<Member> findOne(Long memberID) {
        return memberRepository.findById(memberID);
    }

}
