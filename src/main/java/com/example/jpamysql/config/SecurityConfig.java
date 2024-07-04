package com.example.jpamysql.config;

import com.example.jpamysql.jwt.LoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AuthenticationConfiguration authenticationConfiguration;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration) {

        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }
    // 시큐리티를 통해서 회원 정보를 저장하고, 회원가입하고, 검증할 땐 비밀번호를 해시로 암호화시켜서 검증하는 방식으로 진행
    //BCryptPasswordEncoder 사용
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception {
        //csrf disable
        // 세션방식에선 세션이 고정되기 때문에 csrf 공격이 필수적으로 방어를 해줘야 한다.
        //jwt는 세션을 stateless 상태로 관리하기 때문에 csrf에 대한 공격을 방어할 필요가 없기 때문에 disable

        http
                .csrf((auth) -> auth.disable());


        //jwt 를 사용할 것이기 때문에
        //From 로그인 방식 disable
        http
                .formLogin((auth) -> auth.disable());

        //http basic 인증 방식 disable
        http
                .httpBasic((auth) -> auth.disable());

        //경로별 인가 작업
        http
                .authorizeHttpRequests((auth) -> auth //
                        .requestMatchers("/login", "/", "/join").permitAll() // 로그인과 join은 모두 접근 가능
                        .requestMatchers("/admin").hasRole("ADMIN") // 관리자 페이지는 ADMIN만 접근 가능
                        .anyRequest().authenticated()); // 다른 요청은 로그인된 사람만 가능 

        //필터 추가 LoginFilter()는 인자를 받음 (AuthenticationManager() 메소드에 authenticationConfiguration 객체를 넣어야 함) 따라서 등록 필요
        http
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration)), UsernamePasswordAuthenticationFilter.class);

        //세션 설정
        //stateless방식으로 해야함
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));




        return http.build();
    }
}
