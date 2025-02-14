package org.myserver.spring_oauth_authorization_server.dto;

import lombok.Data;

@Data
public class RegisterDTO {
    private String clientName;
    private String redirectUris;
    private String postLogoutRedirectUris;
    private String scopes;
}
