package com.finances.AscacibasFinances.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finances.AscacibasFinances.comuns.ResponseMessage;
import com.finances.AscacibasFinances.dto.ExpenseRequestDTO;
import com.finances.AscacibasFinances.dto.ExpenseResponseDTO;
import com.finances.AscacibasFinances.dto.FilterDateDTO;
import com.finances.AscacibasFinances.enumerator.CategoryEnum;
import com.finances.AscacibasFinances.enumerator.TypeEnum;
import com.finances.AscacibasFinances.service.ExpenseService;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;

	@PostMapping("/createExpense")
	public ResponseEntity<ResponseMessage> createExpense(@RequestBody ExpenseRequestDTO request) {
		ResponseMessage response = expenseService.createExpense(request);
		if (response.isSuccess()) {
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.badRequest().body(response);
	}

	@DeleteMapping("/deleteExpense/{id}")
	public ResponseEntity<ResponseMessage> deleteExpense(@PathVariable Long id) {
		ResponseMessage response = expenseService.deleteExpense(id);
		if (response.isSuccess()) {
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.badRequest().body(response);
	}

	@PutMapping("/editExpense/{id}")
	public ResponseEntity<ResponseMessage> editExpense(@PathVariable Long id, @RequestBody ExpenseRequestDTO request) {
		ResponseMessage response = expenseService.editExpense(id, request);
		if (response.isSuccess()) {
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.badRequest().body(response);
	}

	@GetMapping("/listBetweenDate")
	public ResponseEntity<List<ExpenseResponseDTO>> listExpensesBetweenDate(@RequestBody FilterDateDTO filter) {
		List<ExpenseResponseDTO> response = expenseService.listExpensesBetweenDate(filter.getStartDate(),
				filter.getFinalDate());
		return ResponseEntity.ok(response);
	}

	@GetMapping("/listByCategory/{category}")
	public ResponseEntity<List<ExpenseResponseDTO>> listExpensesByCategory(@PathVariable CategoryEnum category) {
		List<ExpenseResponseDTO> response = expenseService.listExpensesByCategory(category);
		return ResponseEntity.ok(response);
	}

	@GetMapping()
	public ResponseEntity<List<ExpenseResponseDTO>> listAllExpense() {
		List<ExpenseResponseDTO> response = expenseService.listAllExpenses();
		return ResponseEntity.ok(response);
	}
}
