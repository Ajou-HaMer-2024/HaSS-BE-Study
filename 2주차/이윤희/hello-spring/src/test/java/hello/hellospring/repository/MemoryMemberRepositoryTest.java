package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

//Test Code를 먼저 작성하고 필요한 구현 코드를 작성하는 방법 = TDD(테스트 주도 개발)

class MemoryMemberRepositoryTest { //Class 레벨에서 돌리면, test를 한 번에 할 수 있다.

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach //하나의 method가 동작이 끝날 때마다 동작을 한다.
    public void afterEach(){
        repository.clearStore(); //하나의 test가 끝날 때마다 repository clear
        // 각각의 test는 의존 관계가 없이 설정되어야 한다.
        // 하지만, findAll()에서 member를 이미 생성했기 때문에 test 오류가 발생한다.
        // 따라서, 동작이 끝날 때마다 clear 작업이 필요하다. -> 순서가 상관 없어진다.
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); //반환 값은 optional, optional에서 값을 꺼낼 때는 get을 이용한다.
        //검증 _ 동일한지 확인
        //System.out.println("result =" + (result == member));
        //Assertions.assertEquals(member, result); //성공하면 초록불!
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        //회원 생성
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring2").get();

        assertThat(result).isEqualTo(member2);

    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
