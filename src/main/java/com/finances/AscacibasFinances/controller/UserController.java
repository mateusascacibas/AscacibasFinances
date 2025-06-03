package com.finances.AscacibasFinances.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finances.AscacibasFinances.comuns.ResponseMessage;
import com.finances.AscacibasFinances.dto.UserRequestDTO;
import com.finances.AscacibasFinances.dto.UserResponseDTO;
import com.finances.AscacibasFinances.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/createUser")
	public ResponseEntity<ResponseMessage> createUser(@RequestBody UserRequestDTO user){
		ResponseMessage response = userService.createUser(user);
		if(response.isSuccess()) {			
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.badRequest().body(response);
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<ResponseMessage> deleteUser(@PathVariable Long id){
		ResponseMessage response = userService.deleteUser(id);
		if(response.isSuccess()) {
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.badRequest().body(response);
	}
	
	@GetMapping()
	public ResponseEntity<List<UserResponseDTO>> listAllUsers(){
		List<UserResponseDTO> response = userService.listAllUsers();
		return ResponseEntity.ok(response);
	}
}
