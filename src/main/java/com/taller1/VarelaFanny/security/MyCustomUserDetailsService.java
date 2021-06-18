package com.taller1.VarelaFanny.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.taller1.VarelaFanny.model.Userr;
import com.taller1.VarelaFanny.repositories.UserrRepository;


@Service
public class MyCustomUserDetailsService implements UserDetailsService {
	
	
	private UserrRepository userrRepository;
	
	@Autowired
	public MyCustomUserDetailsService(UserrRepository userrRepository) {
		this.userrRepository = userrRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Userr userr = userrRepository.findByUserName(username).get(0);
		System.out.println(userr.getUserName() + "contra:" + userr.getUserPassword());
		if (userr != null) {
//			User.UserBuilder builder = User.withUsername(username).password(userr.getUserPassword()).roles(userr.getPerson().getPersonRoles().get(0).getRolee().getRoleName());
			User.UserBuilder builder = User.withUsername(username).password(userr.getUserPassword()).roles(userr.getRole());

			return builder.build();
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
	}
}