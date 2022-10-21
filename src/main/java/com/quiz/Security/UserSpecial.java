package com.quiz.Security;




import com.quiz.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserSpecial implements UserDetails {

    private String username;
    private String password;
    private Set<SimpleGrantedAuthority> roles;
    private Boolean active;

    public UserSpecial(){}

    public UserSpecial(User user){
        this.username = user.getUsername();
        this.password = user.getPassword();

        this.roles =
                user.getRoles()
                        .stream()
                        .map(l->new SimpleGrantedAuthority(l.name()))
                        .collect(Collectors.toSet());
        this.active = user.getActive();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }


}

