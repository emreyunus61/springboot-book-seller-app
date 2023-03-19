package com.example.springbootbooksellerapp.security;

import com.example.springbootbooksellerapp.model.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Data
@Builder
public class UserPrincipal implements UserDetails {


    private Long id;
    private String username;

    transient  private  String password; //serbest bırakılmış yerlerde görünme

    transient  private User user ;  //sadece oturum açma işlemi için kullanıcı, JWT'de kullanmayın.

    private Set<GrantedAuthority> authorities;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
        return true;
    }
}
