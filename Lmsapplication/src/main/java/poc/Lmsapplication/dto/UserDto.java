package poc.Lmsapplication.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import poc.Lmsapplication.entities.JwtRequest;

import java.util.ArrayList;
import java.util.Collection;


/**
 * @author deeksha.singh
 */
public class UserDto extends User {

    private JwtRequest jwtRequest;

    public UserDto(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }





    public JwtRequest getJwtRequest() {
        return jwtRequest;
    }

    public void setJwtRequest(JwtRequest jwtRequest) {
        this.jwtRequest = jwtRequest;
    }



    public Collection<GrantedAuthority> getAuthorities() {
        return new ArrayList<>();//Collections.<GrantedAuthority>singletonList(new SimpleGrantedAuthority("User"));

    }

    @Override
    public String getPassword() {
        return jwtRequest.getPassword();
    }

    @Override
    public String getUsername() {
        return jwtRequest.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
