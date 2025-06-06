package com.finances.AscacibasFinances.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.finances.AscacibasFinances.comuns.ResponseMessage;
import com.finances.AscacibasFinances.dto.ExpenseRequestDTO;
import com.finances.AscacibasFinances.dto.ExpenseResponseDTO;
import com.finances.AscacibasFinances.dto.FilterDateDTO;
import com.finances.AscacibasFinances.enumerator.CategoryEnum;
import com.finances.AscacibasFinances.enumerator.RecurrenceEnum;
import com.finances.AscacibasFinances.repository.ExpenseRepository;
import com.finances.AscacibasFinances.service.ExpenseService;

@ExtendWith(MockitoExtension.class)
public class ExpenseControllerTest {

	@InjectMocks
	private ExpenseController controller;
	
	@Mock 
	private ExpenseService service;
	
	@Mock
	private ExpenseRepository repository;
	
	private ExpenseRequestDTO request;
	
	private ExpenseResponseDTO response;
	
	private FilterDateDTO filter;
	
	@BeforeEach
	void setup() {
		request = new ExpenseRequestDTO(10.0, CategoryEnum.CREDIT, LocalDateTime.now(), "Test", RecurrenceEnum.DAILY, 1L);
		response = new ExpenseResponseDTO(1L, 10.0, CategoryEnum.CREDIT, LocalDateTime.now(), "Test", RecurrenceEnum.DAILY);
		filter = new FilterDateDTO();
	}
	
	@Test
	void createExpense_ReturnSuccess() {
		when(service.createExpense(request)).thenReturn(new ResponseMessage(true, "Expense created with success."));
		ResponseEntity<ResponseMessage> response = controller.createExpense(request);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		verify(service, times(1)).createExpense(request);
		
	}
	
	@Test
	void createExpense_ReturnError() {
		when(service.createExpense(request)).thenReturn(new ResponseMessage(false, "Failed creating Expense"));
		ResponseEntity<ResponseMessage> response = controller.createExpense(request);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		
		verify(service, times(1)).createExpense(request);
		
	}
	
	@Test
	void deleteExpense_ReturnSuccess() {
		when(service.deleteExpense(1L)).thenReturn(new ResponseMessage(true, "Expense deleted with success."));
		ResponseEntity<ResponseMessage> response = controller.deleteExpense(1L);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		verify(service, times(1)).deleteExpense(1L);
		
	}
	
	@Test
	void deleteExpense_ReturnError() {
		when(service.deleteExpense(1L)).thenReturn(new ResponseMessage(false, "Failed deleting Expense"));
		ResponseEntity<ResponseMessage> response = controller.deleteExpense(1L);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		
		verify(service, times(1)).deleteExpense(1L);
		
	}
	
	@Test
	void editExpense_ReturnSuccess() {
		when(service.editExpense(1L, request)).thenReturn(new ResponseMessage(true, "Expense edited with success."));
		ResponseEntity<ResponseMessage> response = controller.editExpense(1L, request);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		verify(service, times(1)).editExpense(1L, request);
		
	}
	
	@Test
	void editExpense_ReturnError() {
		when(service.editExpense(1L, request)).thenReturn(new ResponseMessage(false, "Failed editing Expense"));
		ResponseEntity<ResponseMessage> response = controller.editExpense(1L, request);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		
		verify(service, times(1)).editExpense(1L, request);
		
	}
	
	@Test
	void listBetweenDateExpense_ReturnSuccess() {
		List<ExpenseResponseDTO> listResponse = new ArrayList<ExpenseResponseDTO>();
		listResponse.add(response);
		filter.setStartDate(LocalDateTime.now());
		filter.setFinalDate(LocalDateTime.now());

		when(service.listExpensesBetweenDate(filter.getStartDate(), filter.getFinalDate())).thenReturn(listResponse);
		ResponseEntity<List<ExpenseResponseDTO>> listResponse2 = controller.listExpensesBetweenDate(filter);
		assertThat(listResponse2.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		verify(service, times(1)).listExpensesBetweenDate(filter.getStartDate(), filter.getFinalDate());
		
	}
	
	@Test
	void listByTypeExpense_ReturnSuccess() {
		List<ExpenseResponseDTO> listResponse = new ArrayList<ExpenseResponseDTO>();
		listResponse.add(response);

		when(service.listExpensesByCategory(CategoryEnum.CREDIT)).thenReturn(listResponse);
		ResponseEntity<List<ExpenseResponseDTO>> listResponse2 = controller.listExpensesByCategory(CategoryEnum.CREDIT);
		assertThat(listResponse2.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		verify(service, times(1)).listExpensesByCategory(CategoryEnum.CREDIT);
		
	}
	
	@Test
	void listAllExpense_ReturnSuccess() {
		List<ExpenseResponseDTO> listResponse = new ArrayList<ExpenseResponseDTO>();
		listResponse.add(response);

		when(service.listAllExpenses()).thenReturn(listResponse);
		ResponseEntity<List<ExpenseResponseDTO>> listResponse2 = controller.listAllExpense();
		assertThat(listResponse2.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		verify(service, times(1)).listAllExpenses();
		
	}
}
