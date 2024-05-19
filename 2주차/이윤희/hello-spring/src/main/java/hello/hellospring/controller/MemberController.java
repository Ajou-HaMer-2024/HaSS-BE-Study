package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller //이거랑 public class 선언만 해주어도, spring 컨테이너가 관리해준다
public class MemberController {

    private final MemberService memberService;

    /**
     * 필드 주입 방식(별로 좋은 방식은 아니다 -> 변경이 불가능함)
     * @Autowired private MemberService memberService;
     *
     * setter 주입 방식(public하게 노출이 된다.)
     * @Autowired
     * public void setMemberController(MemberService memberService) {
     * this.memberService = memberService;
     * }
     */

    @Autowired //스프링 컨테이너에 있는 memberService와 연결시켜준다. -> 생성자 주입 방식
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; //회원 가입이 끝난 후에 홈으로 보낸다.
    }

    @GetMapping("/members")
    public String list(Model model){
         List<Member> members = memberService.findMembers();
         model.addAttribute("members",members);
         return "members/memberList";
    }

}
