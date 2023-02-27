package poc.Lmsapplication.entities;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomerUserDetail implements UserDetails {

	public User user;
	
	public CustomerUserDetail(User user){
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		HashSet<SimpleGrantedAuthority> set1=new HashSet<>();
		set1.add(new SimpleGrantedAuthority(this.user.getRole()));
		return set1;
	}

	@Override
	public String getPassword() {
	
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String toString() {
		return "CustomerUserDetail [user=" + user + "]";
	}

	public CustomerUserDetail()
	{
		
	}
	
	

}
