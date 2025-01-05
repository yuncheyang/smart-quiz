package com.yunche.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yunche.infra.entity.AuthUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginUser implements UserDetails {


    private AuthUser authUser;

    private String password;     // 对应 "password" 字段
    private String username;     // 对应 "username" 字段
    private boolean enabled;     // 对应 "enabled" 字段
    private boolean credentialsNonExpired; // 对应 "credentialsNonExpired" 字段
    private boolean accountNonExpired;     // 对应 "accountNonExpired" 字段
    private boolean accountNonLocked;      // 对应 "accountNonLocked" 字段

    //存放当前登录用户的权限信息，一个用户可以有多个权限
    private List<String> permissions;

    public LoginUser(AuthUser user, List<String> permissions) {
        this.authUser = user;
        this.permissions = permissions;
    }

    // 权限集合
    @JsonIgnore
    private List<SimpleGrantedAuthority> authorities;

    //获取权限信息
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (authorities != null) {
            return authorities;
        }
        //方式二
        authorities = permissions.stream().
                map(SimpleGrantedAuthority::new).
                collect(Collectors.toList());

        return authorities;
    }

    public LoginUser(AuthUser authUser) {
        this.authUser = authUser;
    }


    @Override
    public String getPassword() {
        return authUser.getPassword();
    }

    @Override
    public String getUsername() {
        return authUser.getUserName();
    }

    //是否未过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //是否未锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //凭证是否未过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //是否可用
    @Override
    public boolean isEnabled() {
        return true;
    }

}
