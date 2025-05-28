package com.finances.AscacibasFinances.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finances.AscacibasFinances.dto.UserRequestDTO;
import com.finances.AscacibasFinances.dto.UserResponseDTO;
import com.finances.AscacibasFinances.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/createUser")
	public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO user){
		UserResponseDTO response = userService.createUser(user);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping()
	public ResponseEntity<List<UserResponseDTO>> listAllUsers(){
		List<UserResponseDTO> response = userService.listAllUsers();
		return ResponseEntity.ok(response);
	}
}
