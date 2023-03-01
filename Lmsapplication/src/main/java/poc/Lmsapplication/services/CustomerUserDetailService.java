package poc.Lmsapplication.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import poc.Lmsapplication.entities.CustomerUserDetail;
import poc.Lmsapplication.entities.User;
import poc.Lmsapplication.repositories.UserRepository;

import static poc.Lmsapplication.Enum.ResponseStatus.APPROVED;


@Service
public class CustomerUserDetailService implements UserDetailsService
{
	
	@Autowired
	public UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<User> userList=this.userRepository.findByUsername(username);
		User user = null;
		for(int i=0;i<userList.size();i++){
			if(userList.get(i).getUsername().equalsIgnoreCase(username)){
				user=userList.get(i);
				break;
			}
		}

		if(user==null){
			throw new UsernameNotFoundException("No User Found !");
		}
		else{
			if(user.getResponseStatus().toString().equals("APPROVED")){
				return new CustomerUserDetail(user);
			}else {
				throw new UsernameNotFoundException("User Not Approved !");
			}
		}


//		return new CustomerUserDetail(user);
		
//		Optional<User> userInfo = userRepository.findByusername(username);
//		System.out.println("user optional"+userInfo);
//		System.out.println(userInfo.map(CustomerUserDetail::new)
//                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username)));
//        return userInfo.map(CustomerUserDetail::new)
//                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
	}

}
