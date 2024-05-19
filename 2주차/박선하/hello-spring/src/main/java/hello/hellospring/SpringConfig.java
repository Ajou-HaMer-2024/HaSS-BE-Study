package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //Springbean에 등록하라는 뜻
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    } //memberservice와 레포지토리를 생성하고 서비스에 레포지토리를 넣어줌

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
