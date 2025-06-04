package com.finances.AscacibasFinances.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.finances.AscacibasFinances.comuns.ResponseMessage;
import com.finances.AscacibasFinances.dto.UserRequestDTO;
import com.finances.AscacibasFinances.dto.UserResponseDTO;
import com.finances.AscacibasFinances.model.User;
import com.finances.AscacibasFinances.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@InjectMocks
	private UserService userService;
	
	private UserRequestDTO requestDTO;
	
	@BeforeEach
	void setup() {
		requestDTO = new UserRequestDTO("Mateus", "mateus@teste.com", "123456");
	}
	
	@Test
	void createUser_ReturnSuccess_WhenUserIsCreated() {
		User user = toEntity();
		
		when(passwordEncoder.encode(requestDTO.password())).thenReturn("encryptedPassword");
		when(userRepository.save(any(User.class))).thenReturn(user);
		
		ResponseMessage response = userService.createUser(requestDTO);
		
		assertThat(response.isSuccess()).isTrue();
		assertThat(response.getMessage()).isEqualTo("User created with success.");
		
		verify(userRepository, times(1)).save(any(User.class));
		verify(passwordEncoder, times(1)).encode(requestDTO.password());
	}

	
	
	@Test
	void createUser_ReturnError() {
		when(passwordEncoder.encode(requestDTO.password())).thenThrow(new RuntimeException("Encryption failed"));
		ResponseMessage response = userService.createUser(requestDTO);
		
		assertThat(response.isSuccess()).isFalse();
		assertThat(response.getMessage()).contains("Error creating user");
		
		verify(passwordEncoder, times(1)).encode(requestDTO.password());
		verify(userRepository, never()).save(any(User.class));
		
	}
	
	@Test
	void deleteUser_ReturnSuccess_WhenUserIsDeleted() {
		User user = toEntity();
		Long userId = 1L;
		when(userRepository.findById(userId)).thenReturn(Optional.of(user));
		ResponseMessage response = userService.deleteUser(userId);
		
		assertThat(response.isSuccess()).isTrue();
		assertThat(response.getMessage()).isEqualTo("User deleted with success.");
		
		verify(userRepository, times(1)).findById(userId);
	}
	
	@Test
	void deleteUser_ReturnError() {
		Long userId = 1L;
		when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));
		doThrow(new RuntimeException("Error deleting user")).when(userRepository).delete(any(User.class));
		ResponseMessage response = userService.deleteUser(1L);
		
		assertThat(response.isSuccess()).isFalse();
		assertThat(response.getMessage()).contains("Error deleting user");
		
		verify(userRepository, times(1)).delete(any(User.class));
	}
	
	@Test
	void listAllUser_ReturnSuccess() {
		User user = toEntity();
		List<User> users = new ArrayList<User>();
		users.add(user);
		when(userRepository.findAll()).thenReturn(users);
		List<UserResponseDTO> response = userService.listAllUsers();
		assertThat(response.get(0).email()).isEqualTo(user.getEmail());
		
		verify(userRepository, times(1)).findAll();
	}
	
	@Test
	void listAllUser_ReturnError() {
		when(userRepository.findAll()).thenThrow(new RuntimeException("Error listing users"));
		RuntimeException thrown = assertThrows(RuntimeException.class, () ->{
			userService.listAllUsers();
		});
		assertThat(thrown.getMessage()).contains("Error listing users");
		verify(userRepository, times(1)).findAll();
	}
	
	@Test
	void changeUserPassword_ReturnSuccess() {
		User user = toEntity();
		Long userId = 1L;
		when(userRepository.findById(userId)).thenReturn(Optional.of(user));
		ResponseMessage response = userService.changeUserPassword(userId, requestDTO);
		assertThat(response.isSuccess()).isTrue();
		assertThat(response.getMessage()).isEqualTo("Password updated with success.");
		
		verify(userRepository, times(1)).findById(userId);
	}
	
	@Test
	void changeUserPassword_ReturnError() {
		User user = toEntity();
		Long userId = 1L;
		when(userRepository.findById(userId)).thenReturn(Optional.of(user));
		when(passwordEncoder.encode(requestDTO.password())).thenThrow(new RuntimeException("Encrypted failed"));
		
		ResponseMessage response = userService.changeUserPassword(userId, requestDTO);
		assertThat(response.isSuccess()).isFalse();
		assertThat(response.getMessage()).contains("Error changing password");
		
		verify(userRepository, times(1)).findById(userId);
		verify(passwordEncoder, times(1)).encode(requestDTO.password());
	}
	
	
	private User toEntity() {
		User user = new User();
		user.setName(requestDTO.name());
		user.setEmail(requestDTO.email());
		user.setPassword("encryptedPassword");
		return user;
	}

}
