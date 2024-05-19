package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
//    public final MemberService memberService = new MemberService();
    // >> 멤버 컨트롤러가 아닌 다른 컨트롤러들도 이를 쓸 수 있다는 문제가 있음
//    private final MemberService memberService;
    private MemberService memberService;



    //    @Autowired private   MemberService memberService;
    // 필드 주입하기

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
    // 아무나 호출 할 수 있음

    // 생성자 주입 권장

    @Autowired
    public MemberController(MemberService memberService) {

        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "/member/createMemberForm";
    }
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String List(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
