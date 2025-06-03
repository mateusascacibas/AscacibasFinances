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
import com.finances.AscacibasFinances.dto.FinanceGoalRequestDTO;
import com.finances.AscacibasFinances.dto.FinanceGoalResponseDTO;
import com.finances.AscacibasFinances.service.FinanceGoalService;

@RestController
@RequestMapping("/financeGoal")
public class FinanceGoalController {
	
	@Autowired
	private FinanceGoalService financeGoalService;
	
	@PostMapping("/createFinanceGoal")
	public ResponseEntity<ResponseMessage> createFinanceGoal(@RequestBody FinanceGoalRequestDTO request) {
		ResponseMessage response = financeGoalService.createFinanceGoal(request);
		if(response.isSuccess()) {			
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.badRequest().body(response);
	}
	
	@DeleteMapping("/deleteFinanceGoal/{id}")
	public ResponseEntity<ResponseMessage> deleteFinanceGoal(@PathVariable Long id){
		ResponseMessage response = financeGoalService.deleteFinanceGoal(id);
		if(response.isSuccess()) {
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.badRequest().body(response);
	}
	
	@GetMapping()
	public ResponseEntity<List<FinanceGoalResponseDTO>> listAllFinanceGoals(){
		List<FinanceGoalResponseDTO> list = financeGoalService.listAllFinanceGoals();
		return ResponseEntity.ok(list);
	}
}
