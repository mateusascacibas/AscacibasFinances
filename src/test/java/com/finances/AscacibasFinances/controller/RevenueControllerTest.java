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
import com.finances.AscacibasFinances.dto.RevenueRequestDTO;
import com.finances.AscacibasFinances.dto.RevenueResponseDTO;
import com.finances.AscacibasFinances.enumerator.TypeEnum;
import com.finances.AscacibasFinances.service.RevenueService;

@ExtendWith(MockitoExtension.class)
public class RevenueControllerTest {

	@InjectMocks
	private RevenueController revenueController;
	
	@Mock
	private RevenueService revenueService;
	
	private RevenueRequestDTO request;
	
	@BeforeEach
	void setup() {
		request = new RevenueRequestDTO(10.0, LocalDateTime.now(), "Test", TypeEnum.BONUS, 1L);
	}
	
	@Test
	void createRevenue_ReturnSuccess() {
		when(revenueService.createRevenue(request)).thenReturn(new ResponseMessage(true, "Revenue created with success."));
		ResponseEntity<ResponseMessage> response = revenueController.createRevenue(request);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		verify(revenueService, times(1)).createRevenue(request);
	}
	
	@Test
	void createRevenue_ReturnError() {
		when(revenueService.createRevenue(request)).thenReturn(new ResponseMessage(false, "Error creating revenue"));
		ResponseEntity<ResponseMessage> response = revenueController.createRevenue(request);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		verify(revenueService, times(1)).createRevenue(request);
	}
	
	@Test
	void deleteRevenue_ReturnSuccess() {
		when(revenueService.deleteRevenue(1L)).thenReturn(new ResponseMessage(true, "Revenue deleted with success."));
		ResponseEntity<ResponseMessage> response = revenueController.deleteRevenue(1L);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		verify(revenueService, times(1)).deleteRevenue(1L);
	}
	
	@Test
	void deleteRevenue_ReturnError() {
		when(revenueService.deleteRevenue(1L)).thenReturn(new ResponseMessage(false, "Error deleting revenue"));
		ResponseEntity<ResponseMessage> response = revenueController.deleteRevenue(1L);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		verify(revenueService, times(1)).deleteRevenue(1L);
	}
	
	@Test
	void editRevenue_ReturnSuccess() {
		when(revenueService.editRevenue(1L, request)).thenReturn(new ResponseMessage(true, "Revenue edited with success."));
		ResponseEntity<ResponseMessage> response = revenueController.editRevenue(1L, request);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		verify(revenueService, times(1)).editRevenue(1L, request);
	}
	
	@Test
	void editRevenue_ReturnError() {
		when(revenueService.editRevenue(1L, request)).thenReturn(new ResponseMessage(false, "Error editing revenue"));
		ResponseEntity<ResponseMessage> response = revenueController.editRevenue(1L, request);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		verify(revenueService, times(1)).editRevenue(1L, request);
	}
	
	@Test
	void listAllRevenue_ReturnSuccess() {
		when(revenueService.listAllRevenues()).thenReturn(new ArrayList<RevenueResponseDTO>());
		ResponseEntity<List<RevenueResponseDTO>> response = revenueController.listAllRevenues();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		verify(revenueService, times(1)).listAllRevenues();
	}
	
}
