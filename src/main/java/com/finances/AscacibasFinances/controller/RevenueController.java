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
import com.finances.AscacibasFinances.dto.RevenueRequestDTO;
import com.finances.AscacibasFinances.dto.RevenueResponseDTO;
import com.finances.AscacibasFinances.service.RevenueService;

@RestController
@RequestMapping("/revenue")
public class RevenueController {

	@Autowired
	private RevenueService revenueService;

	@PostMapping("/createRevenue")
	public ResponseEntity<ResponseMessage> createRevenue(@RequestBody RevenueRequestDTO revenue) {
		ResponseMessage response = revenueService.createRevenue(revenue);
		if (response.isSuccess()) {
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.badRequest().body(response);
	}

	@DeleteMapping("/deleteRevenue/{id}")
	public ResponseEntity<ResponseMessage> deleteRevenue(@PathVariable Long id) {
		ResponseMessage response = revenueService.deleteRevenue(id);
		if (response.isSuccess()) {
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.badRequest().body(response);
	}

	@PutMapping("/editRevenue/{id}")
	public ResponseEntity<ResponseMessage> editRevenue(@PathVariable Long id, @RequestBody RevenueRequestDTO request) {
		ResponseMessage response = revenueService.editRevenue(id, request);
		if(response.isSuccess()) {			
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.badRequest().body(response);
	}

	@GetMapping()
	public ResponseEntity<List<RevenueResponseDTO>> listAllRevenues() {
		return ResponseEntity.ok(revenueService.listAllRevenues());
	}
}
