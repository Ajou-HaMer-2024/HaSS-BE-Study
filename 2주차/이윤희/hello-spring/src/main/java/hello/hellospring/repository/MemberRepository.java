package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //회원을 저장하면, 저장된 회원이 반환된다.
    Optional<Member> findById(Long id); //ID로 회원을 찾는다.
    Optional<Member> findByName(String name);//해당하는 값이 없으면, NULL을 반환하는데, optional로 감싸서 반환
    List<Member> findAll(); //지금까지 저장된 모든 회원정보를 반환한다.
}
