package com.finances.AscacibasFinances.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import com.finances.AscacibasFinances.dto.FinanceGoalRequestDTO;
import com.finances.AscacibasFinances.dto.FinanceGoalResponseDTO;
import com.finances.AscacibasFinances.enumerator.GoalTypeEnum;
import com.finances.AscacibasFinances.model.FinanceGoal;
import com.finances.AscacibasFinances.model.User;
import com.finances.AscacibasFinances.repository.FinanceGoalRepository;
import com.finances.AscacibasFinances.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class FinanceGoalServiceTest {
	
	@Mock
	private FinanceGoalRepository financeGoalRepository;
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private FinanceGoalService financeGoalService;
	
	private FinanceGoalRequestDTO request;
	
	@BeforeEach
	void setup() {
		request = new FinanceGoalRequestDTO(GoalTypeEnum.TRAVEL, 10.4, 35.0, 1L);
	}
	
	@Test
	void createFinanceGoal_ReturnSuccess() {
		User user = new User();
		user.setId(request.userId());
		when(userRepository.findById(request.userId())).thenReturn(Optional.of(user));
		
		ResponseMessage response = financeGoalService.createFinanceGoal(request);
		
		assertThat(response.isSuccess()).isTrue();
		assertThat(response.getMessage()).isEqualTo("Finance goal created with success.");
		
		verify(userRepository, times(1)).findById(user.getId());
	}
	
	@Test
	void createFinanceGoal_ReturnError() {
		User user = new User();
		user.setId(request.userId());
		when(userRepository.findById(request.userId())).thenReturn(Optional.of(user));
		doThrow(new RuntimeException("Error creating finance goal")).when(financeGoalRepository).save(any(FinanceGoal.class));
		
		ResponseMessage response = financeGoalService.createFinanceGoal(request);
		
		assertThat(response.isSuccess()).isFalse();
		assertThat(response.getMessage()).contains("Error creating finance goal");
		
		verify(userRepository, times(1)).findById(user.getId());
	}
	
	@Test
	void deleteFinanceGoal_ReturnSuccess() {
		FinanceGoal fg = new FinanceGoal();
		when(financeGoalRepository.findById(1L)).thenReturn(Optional.of(fg));
		ResponseMessage response = financeGoalService.deleteFinanceGoal(1L);
		
		assertThat(response.isSuccess()).isTrue();
		assertThat(response.getMessage()).isEqualTo("Finance goal deleted with success.");
		
		verify(financeGoalRepository, times(1)).findById(1L);
		
	}
	
	@Test
	void deleteFinanceGoal_ReturnError() {
		FinanceGoal fg = new FinanceGoal();
		when(financeGoalRepository.findById(request.userId())).thenReturn(Optional.of(fg));
		doThrow(new RuntimeException("Error deleting finance goal")).when(financeGoalRepository).delete(any(FinanceGoal.class));
		ResponseMessage response = financeGoalService.deleteFinanceGoal(1L);
		
		assertThat(response.isSuccess()).isFalse();
		assertThat(response.getMessage()).contains("Error deleting finance goal");
		
		verify(financeGoalRepository, times(1)).findById(request.userId());
		verify(financeGoalRepository, times(1)).delete(fg);
	}
	
	@Test
	void listAllFinanceGoals() {
		FinanceGoal fg = new FinanceGoal();
		fg.setTargetAmount(request.targetAmount());
		fg.setType(request.type());
		fg.setProgress(request.progress());
		List<FinanceGoal> list = new ArrayList<FinanceGoal>();
		list.add(fg);
		when(financeGoalRepository.findAll()).thenReturn(list);
		List<FinanceGoalResponseDTO> response = financeGoalService.listAllFinanceGoals();
		
		assertThat(response.get(0).progress()).isEqualTo(request.progress());
		verify(financeGoalRepository, times(1)).findAll();
	}
	
}
