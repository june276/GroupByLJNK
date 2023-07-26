package com.project.config;

import com.project.domain.Authority;
import com.project.domain.User;
import com.project.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// 로그인 한 유저의 정보를 가져오기 위한 class
// Security Session -> Authentication 객체 -> UserDetails 정보
public class PrincipalDetails implements UserDetails {

    UserService userService;

    public void setUserService(UserService userService){
        this.userService = userService;
    }

    private User user;

    public void setUser(User user){ this.user = user; }

    public User getUser() { return user; }

    public PrincipalDetails(User user){
        this.user = user;
    }

    // 로그인한 User 의 권한 을 가져오기 위한 method
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();

        List<Authority> list = userService.findAuthorityById(user.getId());

        for(Authority auth : list){
            collect.add(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return auth.getName();
                }

                @Override
                public String toString(){
                    return auth.getName();
                }
            });
        }
        return collect;
    }

    public String getEmail() {return  user.getEmail();}
    public long getId() {return  user.getId();}

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 계정 만료 여부
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠김여부
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 계정 credential 만료 여부
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 활성화 여부
    @Override
    public boolean isEnabled() {
        return true;
    }
}
