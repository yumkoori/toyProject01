package toyProject.toyProject01.member.adapter.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    @GetMapping(value = "/members/join")            //uri 이름 수정예정(자원만담아야함)
    public String MemberJoinForm (Model model) {
        model.addAttribute("requestJoinDto", new RequestJoinDto());
        return "members/joinMemberForm";
    }

}
