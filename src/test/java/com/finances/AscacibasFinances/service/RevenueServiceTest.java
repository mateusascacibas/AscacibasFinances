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
import com.finances.AscacibasFinances.dto.RevenueRequestDTO;
import com.finances.AscacibasFinances.dto.RevenueResponseDTO;
import com.finances.AscacibasFinances.enumerator.CategoryEnum;
import com.finances.AscacibasFinances.enumerator.RecurrenceEnum;
import com.finances.AscacibasFinances.enumerator.TypeEnum;
import com.finances.AscacibasFinances.model.Revenue;
import com.finances.AscacibasFinances.model.User;
import com.finances.AscacibasFinances.repository.RevenueRepository;
import com.finances.AscacibasFinances.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class RevenueServiceTest {
	
	@Mock
	private RevenueRepository RevenueRepository;
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private RevenueService RevenueService;
	
	private RevenueRequestDTO requestRevenue;

	@BeforeEach
	void setup() {
		requestRevenue = new RevenueRequestDTO(10.0, LocalDateTime.now(), "Test", TypeEnum.BONUS, 1L);
	}
	
	@Test
	void createRevenue_ReturnSuccess() {
		User user = new User();
		user.setId(requestRevenue.userId());
		when(userRepository.findById(requestRevenue.userId())).thenReturn(Optional.of(user));
		
		ResponseMessage response = RevenueService.createRevenue(requestRevenue);
		
		assertThat(response.isSuccess()).isTrue();
		assertThat(response.getMessage()).isEqualTo("Revenue created with success.");
		
		verify(userRepository, times(1)).findById(requestRevenue.userId());
	}
	
	@Test
	void createRevenue_ReturnError() {
		User user = new User();
		user.setId(requestRevenue.userId());
		when(userRepository.findById(requestRevenue.userId())).thenReturn(Optional.of(user));
		when(RevenueRepository.save(any(Revenue.class))).thenThrow(new RuntimeException("Error creating Revenue"));
		ResponseMessage response = RevenueService.createRevenue(requestRevenue);
		
		assertThat(response.isSuccess()).isFalse();
		assertThat(response.getMessage()).contains("Error creating Revenue");
		
		verify(userRepository, times(1)).findById(requestRevenue.userId());
		verify(RevenueRepository, times(1)).save(any(Revenue.class));
	}
	
	@Test
	void deleteRevenue_ReturnSuccess() {
		Revenue Revenue = new Revenue();
		Revenue.setId(1L);
		when(RevenueRepository.findById(Revenue.getId())).thenReturn(Optional.of(Revenue));
		ResponseMessage response = RevenueService.deleteRevenue(Revenue.getId());
		
		assertThat(response.isSuccess()).isTrue();
		assertThat(response.getMessage()).isEqualTo("Revenue deleted with success.");
		
		verify(RevenueRepository, times(1)).findById(Revenue.getId());
	}
	
	@Test
	void deleteRevenue_ReturnError() {
		Revenue Revenue = new Revenue();
		Revenue.setId(1L);
		when(RevenueRepository.findById(Revenue.getId())).thenReturn(Optional.of(Revenue));
		doThrow(new RuntimeException("Error deleting Revenue")).when(RevenueRepository).delete(any(Revenue.class));
		ResponseMessage response = RevenueService.deleteRevenue(Revenue.getId());
		
		assertThat(response.isSuccess()).isFalse();
		assertThat(response.getMessage()).contains("Error deleting Revenue");
		
		verify(RevenueRepository, times(1)).findById(Revenue.getId());
		verify(RevenueRepository, times(1)).delete(any(Revenue.class));
	}
	
	@Test
	void editRevenue_ReturnSuccess() {
		Revenue Revenue = new Revenue();
		User user = new User();
		Revenue.setId(1L);
		user.setId(requestRevenue.userId());
		Revenue.setUser(user);
		when(RevenueRepository.findById(Revenue.getId())).thenReturn(Optional.of(Revenue));
		ResponseMessage response = RevenueService.editRevenue(Revenue.getId(), requestRevenue);
		
		assertThat(response.isSuccess()).isTrue();
		assertThat(response.getMessage()).isEqualTo("Revenue edited with success.");
		
		verify(RevenueRepository, times(1)).findById(Revenue.getId());
	}
	
	@Test
	void editRevenue_ReturnError() {
		Revenue Revenue = new Revenue();
		Revenue.setId(1L);
		when(RevenueRepository.findById(Revenue.getId())).thenReturn(Optional.of(Revenue));
		ResponseMessage response = RevenueService.editRevenue(Revenue.getId(), requestRevenue);
		
		assertThat(response.isSuccess()).isFalse();
		assertThat(response.getMessage()).contains("Error editing revenue");
		
		verify(RevenueRepository, times(1)).findById(Revenue.getId());
	}
	
	@Test
	void listAllRevenues_ReturnSuccess() {
		Revenue Revenue = new Revenue();
		User user = new User();
		Revenue.setId(1L);
		user.setId(2L);
		Revenue.setUser(user);
		List<Revenue> list = new ArrayList<Revenue>();
		list.add(Revenue);
		when(RevenueRepository.findAll()).thenReturn(list);
		List<RevenueResponseDTO> response = RevenueService.listAllRevenues();
		
		assertThat(response.get(0).id()).isEqualTo(Revenue.getId());
		
		verify(RevenueRepository, times(1)).findAll();
		
	}
	
}
