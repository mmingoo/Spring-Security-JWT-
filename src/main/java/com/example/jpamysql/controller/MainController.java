package com.example.jpamysql.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.Iterator;

@Controller
@ResponseBody
public class MainController {
    @GetMapping("/main")
    public String Mainp(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        //현재 인증된 사용자의 정보를 담고 있는 객체입니다. 사용자의 이름, 권한 등의 정보를 포함합니다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //사용자에게 부여된 권한(역할)들의 컬렉션입니다. 예를 들어 "ROLE_USER", "ROLE_ADMIN" 등의 권한이 포함될 수 있습니다.
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();
        return "main controller" + name + role;
    }
}
