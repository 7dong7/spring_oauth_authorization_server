package org.myserver.spring_oauth_authorization_server.dto;

import lombok.Data;

@Data
public class UserDTO {

    private String username;
    private String password;
    private String role;
    private String nickname;
    private String phone;
}
