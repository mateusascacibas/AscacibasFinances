package com.finances.AscacibasFinances.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.finances.AscacibasFinances.comuns.ResponseMessage;
import com.finances.AscacibasFinances.dto.ExpenseRequestDTO;
import com.finances.AscacibasFinances.dto.ExpenseResponseDTO;
import com.finances.AscacibasFinances.enumerator.CategoryEnum;
import com.finances.AscacibasFinances.enumerator.RecurrenceEnum;
import com.finances.AscacibasFinances.model.Expense;
import com.finances.AscacibasFinances.model.User;
import com.finances.AscacibasFinances.repository.ExpenseRepository;
import com.finances.AscacibasFinances.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class ExpenseServiceTest {
	
	@Mock
	private ExpenseRepository expenseRepository;
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private ExpenseService expenseService;
	
	private ExpenseRequestDTO requestExpense;

	@BeforeEach
	void setup() {
		requestExpense = new ExpenseRequestDTO(10.0, CategoryEnum.CREDIT, LocalDateTime.now(), "Test", RecurrenceEnum.DAILY, 1L);	
	}
	
	@Test
	void createExpense_ReturnSuccess() {
		User user = new User();
		user.setId(requestExpense.userId());
		when(userRepository.findById(requestExpense.userId())).thenReturn(Optional.of(user));
		
		ResponseMessage response = expenseService.createExpense(requestExpense);
		
		assertThat(response.isSuccess()).isTrue();
		assertThat(response.getMessage()).isEqualTo("Expense created with success.");
		
		verify(userRepository, times(1)).findById(requestExpense.userId());
	}
	
	@Test
	void createExpense_ReturnError() {
		User user = new User();
		user.setId(requestExpense.userId());
		when(userRepository.findById(requestExpense.userId())).thenReturn(Optional.of(user));
		when(expenseRepository.save(any(Expense.class))).thenThrow(new RuntimeException("Error creating Expense"));
		ResponseMessage response = expenseService.createExpense(requestExpense);
		
		assertThat(response.isSuccess()).isFalse();
		assertThat(response.getMessage()).contains("Error creating Expense");
		
		verify(userRepository, times(1)).findById(requestExpense.userId());
		verify(expenseRepository, times(1)).save(any(Expense.class));
	}
	
	@Test
	void deleteExpense_ReturnSuccess() {
		Expense expense = new Expense();
		expense.setId(1L);
		when(expenseRepository.findById(expense.getId())).thenReturn(Optional.of(expense));
		ResponseMessage response = expenseService.deleteExpense(expense.getId());
		
		assertThat(response.isSuccess()).isTrue();
		assertThat(response.getMessage()).isEqualTo("Expense deleted with success.");
		
		verify(expenseRepository, times(1)).findById(expense.getId());
	}
	
	@Test
	void deleteExpense_ReturnError() {
		Expense expense = new Expense();
		expense.setId(1L);
		when(expenseRepository.findById(expense.getId())).thenReturn(Optional.of(expense));
		doThrow(new RuntimeException("Error deleting Expense")).when(expenseRepository).delete(any(Expense.class));
		ResponseMessage response = expenseService.deleteExpense(expense.getId());
		
		assertThat(response.isSuccess()).isFalse();
		assertThat(response.getMessage()).contains("Error deleting Expense");
		
		verify(expenseRepository, times(1)).findById(expense.getId());
		verify(expenseRepository, times(1)).delete(any(Expense.class));
	}
	
	@Test
	void editExpense_ReturnSuccess() {
		Expense expense = new Expense();
		User user = new User();
		expense.setId(1L);
		user.setId(requestExpense.userId());
		expense.setUser(user);
		when(expenseRepository.findById(expense.getId())).thenReturn(Optional.of(expense));
		ResponseMessage response = expenseService.editExpense(expense.getId(), requestExpense);
		
		assertThat(response.isSuccess()).isTrue();
		assertThat(response.getMessage()).isEqualTo("Expense edited with success.");
		
		verify(expenseRepository, times(1)).findById(expense.getId());
	}
	
	@Test
	void editExpense_ReturnError() {
		Expense expense = new Expense();
		expense.setId(1L);
		when(expenseRepository.findById(expense.getId())).thenReturn(Optional.of(expense));
		ResponseMessage response = expenseService.editExpense(expense.getId(), requestExpense);
		
		assertThat(response.isSuccess()).isFalse();
		assertThat(response.getMessage()).contains("Error editing Expense");
		
		verify(expenseRepository, times(1)).findById(expense.getId());
	}
	
	@Test
	void listAllExpenses_ReturnSuccess() {
		Expense expense = new Expense();
		expense.setId(1L);
		List<Expense> list = new ArrayList<Expense>();
		list.add(expense);
		when(expenseRepository.findAll()).thenReturn(list);
		List<ExpenseResponseDTO> response = expenseService.listAllExpenses();
		
		assertThat(response.get(0).id()).isEqualTo(expense.getId());
		
		verify(expenseRepository, times(1)).findAll();
		
	}
	
	@Test
	void listExpensesBetweenDate_ReturnSuccess() {
		Expense expense = new Expense();
		expense.setDate(LocalDateTime.now());
		Expense expense2 = new Expense();
		expense2.setDate(LocalDateTime.of(2025,5,30,14,0));
		List<Expense> list = new ArrayList<Expense>();
		list.add(expense);
		list.add(expense2);
		when(expenseRepository.findAll()).thenReturn(list);
		List<ExpenseResponseDTO> response = expenseService.listExpensesBetweenDate(LocalDateTime.of(2025,5,15,10,0), LocalDateTime.of(2025,5,30,20,0));
		
		assertThat(response.size()).isEqualTo(1);
		assertThat(response.get(0).date()).isEqualTo(expense2.getDate());
		
		verify(expenseRepository, times(1)).findAll();
	}
	
//	@Test
//	void listExpensesByCategory_ReturnSuccess() {
//		Expense expense = new Expense();
//		expense.setCategory(CategoryEnum.CREDIT);
//		Expense expense2 = new Expense();
//		expense2.setCategory(CategoryEnum.DEBIT);
//		List<Expense> list = new ArrayList<Expense>();
//		list.add(expense);
//		list.add(expense2);
//		when(expenseRepository.findAll()).thenReturn(list);
//		List<ExpenseResponseDTO> response = expenseService.listExpensesByCategory(CategoryEnum.CREDIT);
//		
//		assertThat(response.size()).isEqualTo(1);
//		assertThat(response.get(0).date()).isEqualTo(expense.getCategory());
//		
//		verify(expenseRepository, times(1)).findAll();
//	}
}
