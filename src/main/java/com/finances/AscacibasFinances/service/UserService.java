package com.finances.AscacibasFinances.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finances.AscacibasFinances.dto.UserRequestDTO;
import com.finances.AscacibasFinances.dto.UserResponseDTO;
import com.finances.AscacibasFinances.mapper.UserMapper;
import com.finances.AscacibasFinances.model.User;
import com.finances.AscacibasFinances.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public UserResponseDTO createUser(UserRequestDTO request) {
		User user = new User();
		try {
			user = UserMapper.toEntity(request);
			userRepository.save(user);
			return UserMapper.toDTO(user);
		} catch(Exception e) {
			System.out.println("Error creating user.");
			throw e;
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
}
