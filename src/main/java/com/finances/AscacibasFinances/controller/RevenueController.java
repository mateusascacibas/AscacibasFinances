package com.finances.AscacibasFinances.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finances.AscacibasFinances.dto.RevenueRequestDTO;
import com.finances.AscacibasFinances.dto.RevenueResponseDTO;
import com.finances.AscacibasFinances.service.RevenueService;

@RestController
@RequestMapping("/revenue")
public class RevenueController {

	@Autowired
	private RevenueService revenueService;
	
	@PostMapping("/createRevenue")
	public ResponseEntity<RevenueResponseDTO> createRevenue(@RequestBody RevenueRequestDTO revenue){
		RevenueResponseDTO response = revenueService.createRevenue(revenue);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping()
	public ResponseEntity<List<RevenueResponseDTO>> listAllRevenues(){
		return ResponseEntity.ok(revenueService.listAllRevenues());
	}
}
