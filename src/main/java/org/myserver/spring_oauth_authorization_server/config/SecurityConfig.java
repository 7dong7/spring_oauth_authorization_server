package org.myserver.spring_oauth_authorization_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 인가 서버 설정
    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder()
//                .issuer("http://localhost:9000")
//                .authorizationEndpoint("/oauth2/v1/authorize")
//                .tokenEndpoint("/oauth2/v1/token")
//                .tokenIntrospectionEndpoint("/oauth2/v1/introspect") // 토큰 상태
//                .tokenRevocationEndpoint("/oauth2/v1/revoke") // 토큰 폐기 RFC 7009
//                .jwkSetEndpoint("/oauth2/v1/jwks") // 공개키 확인
//                .oidcLogoutEndpoint("/connect/v1/logout")
//                .oidcUserInfoEndpoint("/connect/v1/userinfo") // 리소스 서버 유저 정보 연관
//                .oidcClientRegistrationEndpoint("/connect/v1/register") // OAuth2 사용 신청
                .build();
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServer(HttpSecurity http) throws Exception {

        // deprecated code
//        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        // 사용됨
        http
                .with(OAuth2AuthorizationServerConfigurer.authorizationServer(), Customizer.withDefaults());


        http
                .getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults());
        http
                .exceptionHandling((exceptions) -> exceptions
                        .defaultAuthenticationEntryPointFor(
                                new LoginUrlAuthenticationEntryPoint("/login"),
                                new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
                        )
                );

        return http.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // csrf 비활성화
        http.csrf(AbstractHttpConfigurer::disable);
        
        // 인가 설정
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                );

        // 로그인 설정
        http
                .formLogin(Customizer.withDefaults());

        return http.build();
    }

}
