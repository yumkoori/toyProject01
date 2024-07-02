package toyProject.toyProject01.bord.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import toyProject.toyProject01.bord.domain.Member;
import toyProject.toyProject01.bord.service.MemberService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/members/join")
    public String joinForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/joinMemberForm";
    }

    @PostMapping("/members/join")
    public String join(@ModelAttribute("memberForm") MemberForm form, BindingResult result) {

       // if (result.hasErrors()) {

            log.info("form = {} ", form.getId());
            log.info("form = {} ", form.getPw());
            log.info("form = {} ", form.getNickname());
            log.info("form = {} ", form.getAge());
            log.info("form = {} ", form.getTel());
            log.info("form = {} ", form.getEmail());
        //    return "members/joinMemberForm";
       // }

        Member member = new Member();
        member.setId(form.getId());
        member.setPw(form.getPw());
        member.setNickname(form.getNickname());
        member.setAge(form.getAge());
        member.setTel(form.getTel());
        member.setEmail(form.getEmail());

        memberService.join(member);

        return "members/joinMemberForm";     //임시 로그인 페이지로 보내줘야함.
    }

}
