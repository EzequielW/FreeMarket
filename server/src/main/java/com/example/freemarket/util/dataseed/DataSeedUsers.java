package com.example.freemarket.util.dataseed;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.freemarket.model.Role;
import com.example.freemarket.model.User;
import com.example.freemarket.repository.RoleRepository;
import com.example.freemarket.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataSeedUsers {
	private final RoleRepository roleRepository;
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	
	private boolean userDataIsEmpty() {
		return userRepository.count() == 0;
	}
	
	public void loadData() {
		if (userDataIsEmpty()) {
			makeAdminUsers();
		} else
			log.info("Users data is not empty");
		
	}
	
	private Role getRole(String roleName) {
		Role role = roleRepository.findByName(roleName);
		if (role == null) {
			role = new Role(roleName);
			role = roleRepository.save(role);
		}
		return role;
	}

	
	private void makeAdminUsers() {
		Role role = getRole("ROLE_ADMIN");
		List<User> listaUsuarios = new ArrayList<User>() {
			private static final long serialVersionUID = 1L;
			{
				add(new User("John","Doe","admin@email.com", passwordEncoder.encode("1234"), role));
            }
		};
		saveUsers(listaUsuarios);
	}
	
	private void saveUsers(List<User> users) {
		for(User u : users) {
			u = userRepository.save(u);
		}
	}
}
