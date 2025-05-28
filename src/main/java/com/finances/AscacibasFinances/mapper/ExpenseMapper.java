package com.finances.AscacibasFinances.mapper;

import com.finances.AscacibasFinances.dto.ExpenseRequestDTO;
import com.finances.AscacibasFinances.dto.ExpenseResponseDTO;
import com.finances.AscacibasFinances.model.Expense;
import com.finances.AscacibasFinances.model.User;

public class ExpenseMapper {
	
	public static Expense toEntity(ExpenseRequestDTO request, User user) {
		Expense expense = new Expense();
		expense.setCategory(request.category());
		expense.setDate(request.date());
		expense.setDescription(request.description());
		expense.setRecurrence(request.recurrence());
		expense.setAmount(request.amount());
		expense.setUser(user);
		return expense;
	}
	
	public static ExpenseResponseDTO toDTO(Expense expense) {
		return new ExpenseResponseDTO(expense.getId(), expense.getAmount(), expense.getCategory(), expense.getDate(), expense.getDescription(), expense.getRecurrence());
	}

}
