package org.myserver.spring_oauth_authorization_server.controller;

import lombok.RequiredArgsConstructor;
import org.myserver.spring_oauth_authorization_server.dto.RegisterDTO;
import org.myserver.spring_oauth_authorization_server.entity.RegisterEntity;
import org.myserver.spring_oauth_authorization_server.repository.RegisterRepository;
import org.myserver.spring_oauth_authorization_server.service.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterRepository registerRepository;
    private final RegisterService registerService;

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    @ResponseBody
    public RegisterEntity register(@ModelAttribute RegisterDTO dto) {
        return registerService.register(dto);
    }
}
