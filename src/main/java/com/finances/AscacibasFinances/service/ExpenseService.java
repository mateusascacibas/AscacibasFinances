package com.finances.AscacibasFinances.service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finances.AscacibasFinances.comuns.ResponseMessage;
import com.finances.AscacibasFinances.comuns.Utils;
import com.finances.AscacibasFinances.dto.ExpenseRequestDTO;
import com.finances.AscacibasFinances.dto.ExpenseResponseDTO;
import com.finances.AscacibasFinances.mapper.ExpenseMapper;
import com.finances.AscacibasFinances.model.Expense;
import com.finances.AscacibasFinances.model.User;
import com.finances.AscacibasFinances.repository.ExpenseRepository;
import com.finances.AscacibasFinances.repository.UserRepository;

@Service
public class ExpenseService {

	@Autowired
	ExpenseRepository expenseRepository;

	@Autowired
	UserRepository userRepository;

	Utils utils;

	public ResponseMessage createExpense(ExpenseRequestDTO request) {
		User user = userRepository.findById(request.userId()).orElseThrow(() -> new RuntimeException("User not found"));
		try {
			Expense expense = ExpenseMapper.toEntity(request, user);
			expenseRepository.save(expense);

			return new ResponseMessage(true, "Expense created with success.");
		} catch (Exception e) {
			return new ResponseMessage(false, "Error creating Expense -> " + e.getMessage());
		}
	}

	public ResponseMessage deleteExpense(Long id) {
		try {
			Expense expense = expenseRepository.findById(id)
					.orElseThrow(() -> new RuntimeException("Expense not found"));
			expenseRepository.delete(expense);
			return new ResponseMessage(true, "Expense deleted with success.");
		} catch (Exception e) {
			return new ResponseMessage(false, "Error deleting Expense -> " + e.getMessage());
		}
	}

	public List<ExpenseResponseDTO> listExpensesBetweenDate(LocalDateTime startDate, LocalDateTime finalDate) {
		List<ExpenseResponseDTO> list = listAllExpenses();
		return list.stream().filter(expense -> {
			return (expense.date().isEqual(startDate) || expense.date().isAfter(startDate))
					&& (expense.date().isEqual(finalDate) || expense.date().isBefore(finalDate));
		}).toList();
	}

	public List<ExpenseResponseDTO> listAllExpenses() {
		List<Expense> list = expenseRepository.findAll();
		List<ExpenseResponseDTO> response = list.stream().map(ExpenseMapper::toDTO).toList();
		return response;
	}
}
