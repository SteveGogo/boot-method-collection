package com.mylearn.springsecuritydemo.auth_security.userDetailInfo;

import com.mylearn.springsecuritydemo.entity.SysUser;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义控制认证逻辑 从DB验证用户
 */
@Getter
@Setter
@EqualsAndHashCode
public class CustomerUserDetails implements UserDetails {

    private String username;
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private Set<GrantedAuthority> authorities;


    public CustomerUserDetails(SysUser user, List<SimpleGrantedAuthority> authorities) {
        this.setUsername(user.getAccount());
        this.setPassword(user.getPassword());
        this.setAccountNonExpired(user.getAccountNonExpired());
        this.setAccountNonLocked(user.getAccountNonLocked());
        this.setCredentialsNonExpired(user.getCredentialsNonExpired());
        this.setEnabled(user.getEnabled());
        this.setAuthorities(new HashSet<>(authorities));
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }


    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}