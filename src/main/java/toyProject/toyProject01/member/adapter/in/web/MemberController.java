package toyProject.toyProject01.member.adapter.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import toyProject.toyProject01.member.application.port.in.MemberJoinUseCase;
import toyProject.toyProject01.member.application.port.in.command.JoinCommand;
import toyProject.toyProject01.member.common.ResultDto;

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
