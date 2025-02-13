package org.myserver.spring_oauth_authorization_server.controller;

import lombok.RequiredArgsConstructor;
import org.myserver.spring_oauth_authorization_server.dto.UserDTO;
import org.myserver.spring_oauth_authorization_server.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class JoinController {

    private final UserService userService;

    // 회원 가입 페이지
    @GetMapping("/join")
    public String joinPage() {
        return "joinPage";
    }

    @PostMapping("/join")
    @ResponseBody
    public String join(UserDTO dto) {
        userService.join(dto);
        return "ok";
    }
}
