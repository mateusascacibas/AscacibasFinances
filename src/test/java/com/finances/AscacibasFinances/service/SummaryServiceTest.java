package com.finances.AscacibasFinances.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import com.finances.AscacibasFinances.dto.ExpenseRequestDTO;
import com.finances.AscacibasFinances.dto.MonthlySummaryResponseDTO;
import com.finances.AscacibasFinances.dto.RevenueRequestDTO;
import com.finances.AscacibasFinances.enumerator.CategoryEnum;
import com.finances.AscacibasFinances.enumerator.RecurrenceEnum;
import com.finances.AscacibasFinances.enumerator.TypeEnum;
import com.finances.AscacibasFinances.mapper.ExpenseMapper;
import com.finances.AscacibasFinances.mapper.RevenueMapper;
import com.finances.AscacibasFinances.model.Expense;
import com.finances.AscacibasFinances.model.Revenue;
import com.finances.AscacibasFinances.model.User;
import com.finances.AscacibasFinances.repository.ExpenseRepository;
import com.finances.AscacibasFinances.repository.RevenueRepository;

@ExtendWith(MockitoExtension.class)
public class SummaryServiceTest {

	@Mock
	private RevenueRepository revenueRepository;
	
	@Mock
	private ExpenseRepository expenseRepository;
	
	@InjectMocks
	private SummaryService summaryService;

	private ExpenseRequestDTO expenseRequest;
	
	private RevenueRequestDTO revenueRequest;
	
	@BeforeEach
	void setup() {
		expenseRequest = new ExpenseRequestDTO(10.0, CategoryEnum.CREDIT, LocalDateTime.now(), "Test", RecurrenceEnum.DAILY, 1L);
		revenueRequest = new RevenueRequestDTO(10.0, LocalDateTime.now(), "Test", TypeEnum.INVESTIMENTO, 1L);
	}
	
	@Test
	void listSummary_ReturnSuccess() {
		Expense expense = new Expense();
		Revenue revenue = new Revenue();
		User user = new User();
		user.setId(expenseRequest.userId());
		List<Expense> listExpense = new ArrayList<Expense>();
		List<Revenue> listRevenue = new ArrayList<Revenue>();
		expense = ExpenseMapper.toEntity(expenseRequest, user);
		revenue = RevenueMapper.toEntity(revenueRequest, user);
		listExpense.add(expense);
		listRevenue.add(revenue);
		
		when(revenueRepository.findByUserId(revenueRequest.userId())).thenReturn(Optional.of(listRevenue));
		when(expenseRepository.findByUserId(expenseRequest.userId())).thenReturn(Optional.of(listExpense));
		MonthlySummaryResponseDTO response = summaryService.listSummary(2025, 06, 1L);
		
		assertThat(response.balance()).isEqualTo(0.0);
		
		verify(revenueRepository, times(1)).findByUserId(revenueRequest.userId());
		verify(expenseRepository, times(1)).findByUserId(expenseRequest.userId());
	}
	
	@Test
	void listSummary_ReturnError() {
		Expense expense = new Expense();
		Revenue revenue = new Revenue();
		User user = new User();
		user.setId(expenseRequest.userId());
		List<Expense> listExpense = new ArrayList<Expense>();
		List<Revenue> listRevenue = new ArrayList<Revenue>();
		expense = ExpenseMapper.toEntity(expenseRequest, user);
		revenue = RevenueMapper.toEntity(revenueRequest, user);
		listExpense.add(expense);
		listRevenue.add(revenue);
		
		assertThrows(IllegalArgumentException.class, () -> summaryService.listSummary(null, 06, 1L));

	}
}
