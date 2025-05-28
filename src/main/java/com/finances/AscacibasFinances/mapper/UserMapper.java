package com.finances.AscacibasFinances.mapper;

import com.finances.AscacibasFinances.dto.UserRequestDTO;
import com.finances.AscacibasFinances.dto.UserResponseDTO;
import com.finances.AscacibasFinances.model.User;

public class UserMapper {
	
	public static User toEntity(UserRequestDTO dto) {
		User user = new User();
		user.setName(dto.name());
		user.setEmail(dto.email());
		user.setPassword(dto.password());
		return user;
	}
	
	public static UserResponseDTO toDTO(User user) {
		return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getCreatedDate());
	}

}
