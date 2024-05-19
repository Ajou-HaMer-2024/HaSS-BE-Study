package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository //spring이 이 코드가 리포지토리임을 알 수 있도록 한다.
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //sequence는 0,1,2와 같이 key 값을 그냥 생성한다.(실무에선, 동시성을 고려해 atom long 사용)
    @Override
    public Member save(Member member) {
        member.setId(++sequence); //멤버의 id 값을 세팅
        store.put(member.getId(), member); //저장
        return member; //저장된 결과 반환
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        //NULL을 그냥 반환하는 대신, optional로 감싸서 보낸다. 그럼 client에서 작업할 수 있다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) //member의 이름이 파라미터로 넘어온 이름과 같은지 확인
                .findAny(); //찾아지면, 그걸 반환 (없으면 ,Optional(null) 반환)
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //멤버들을 반환
    }

    public void clearStore(){
        store.clear();
    }
}
