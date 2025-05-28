package com.finances.AscacibasFinances.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public ExpenseResponseDTO createExpense(ExpenseRequestDTO request) {
		User user = userRepository.findById(request.userId()).orElseThrow(() -> new RuntimeException("User not found"));
		try {			
			Expense expense = ExpenseMapper.toEntity(request, user);
			expenseRepository.save(expense);
			return ExpenseMapper.toDTO(expense);
		} catch(Exception e) {
			System.out.println("Error creating Expense");
			throw e;
		}
	}
	
	public List<ExpenseResponseDTO> listAllExpenses(){
		List<Expense> list = expenseRepository.findAll();
		List<ExpenseResponseDTO> response = list.stream().map(ExpenseMapper::toDTO).toList();
		return response;
	}
}
