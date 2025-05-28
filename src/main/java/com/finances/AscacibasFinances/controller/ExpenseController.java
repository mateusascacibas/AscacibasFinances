package com.finances.AscacibasFinances.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finances.AscacibasFinances.dto.ExpenseRequestDTO;
import com.finances.AscacibasFinances.dto.ExpenseResponseDTO;
import com.finances.AscacibasFinances.service.ExpenseService;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
	
	@Autowired
	private ExpenseService expenseService;
	
	@PostMapping("/createExpense")
	public ResponseEntity<ExpenseResponseDTO> createExpense(@RequestBody ExpenseRequestDTO request) {
		return ResponseEntity.ok(expenseService.createExpense(request));
	}
	
	@GetMapping()
	public ResponseEntity<List<ExpenseResponseDTO>> listAllExpense(){
		List<ExpenseResponseDTO> response = expenseService.listAllExpenses();
		return ResponseEntity.ok(response);
	}
}
