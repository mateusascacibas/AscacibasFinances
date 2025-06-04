package com.finances.AscacibasFinances.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.finances.AscacibasFinances.comuns.ResponseMessage;
import com.finances.AscacibasFinances.dto.UserRequestDTO;
import com.finances.AscacibasFinances.dto.UserResponseDTO;
import com.finances.AscacibasFinances.mapper.UserMapper;
import com.finances.AscacibasFinances.model.User;
import com.finances.AscacibasFinances.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	

    @Autowired
    private PasswordEncoder passwordEncoder;
	
	public ResponseMessage createUser(UserRequestDTO request) {
		User user = new User();
		try {
			user = UserMapper.toEntity(request);
			user.setPassword(passwordEncoder.encode(request.password()));
			userRepository.save(user);
			return new ResponseMessage(true, "User created with success.");
		} catch(Exception e) {
			return new ResponseMessage(false, "Error creating user. -> " + e.getMessage());
		}
	}
	
	public ResponseMessage deleteUser(Long id) {
		try {			
			User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
			userRepository.delete(user);
			return new ResponseMessage(true, "User deleted with success.");
		} catch (Exception e) {
			return new ResponseMessage(false, "Error deleting user -> " + e.getMessage());
		}
	}
	
	public ResponseMessage changeUserPassword(Long id, UserRequestDTO request) {
		try {			
			User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
			user.setPassword(passwordEncoder.encode(request.password()));
			userRepository.save(user);
			return new ResponseMessage(true, "Password updated with success.");
		} catch (Exception e) {
			return new ResponseMessage(false, "Error changing password -> " + e.getMessage());
		}
	}
	
	public List<UserResponseDTO> listAllUsers(){
		try {
			List<User> list = new ArrayList<User>();
			List<UserResponseDTO> response = new ArrayList<UserResponseDTO>();
			list = userRepository.findAll();
			response = list.stream().map(UserMapper::toDTO).toList();
			return response;
		} catch(Exception e) {
			System.out.println("Error listing users.");
			throw e;
		}
	}
	
	@Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities("USER")
                .build();
    }

}
