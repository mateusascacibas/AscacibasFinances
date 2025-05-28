package com.finances.AscacibasFinances.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finances.AscacibasFinances.dto.FinanceGoalRequestDTO;
import com.finances.AscacibasFinances.dto.FinanceGoalResponseDTO;
import com.finances.AscacibasFinances.service.FinanceGoalService;

@RestController
@RequestMapping("/financeGoal")
public class FinanceGoalController {
	
	@Autowired
	private FinanceGoalService financeGoalService;
	
	@PostMapping("/createFinanceGoal")
	public ResponseEntity<FinanceGoalResponseDTO> createFinanceGoal(@RequestBody FinanceGoalRequestDTO request) {
		FinanceGoalResponseDTO response = financeGoalService.createFinanceGoal(request);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping()
	public ResponseEntity<List<FinanceGoalResponseDTO>> listAllFinanceGoals(){
		List<FinanceGoalResponseDTO> list = financeGoalService.listAllFinanceGoals();
		return ResponseEntity.ok(list);
	}
}
