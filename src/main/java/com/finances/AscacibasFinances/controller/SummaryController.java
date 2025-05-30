package com.finances.AscacibasFinances.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finances.AscacibasFinances.dto.MonthlySummaryResponseDTO;
import com.finances.AscacibasFinances.service.SummaryService;

@RestController
@RequestMapping("/summary")
public class SummaryController {
	
	@Autowired
	private SummaryService summaryService;
	
	@GetMapping()
	public ResponseEntity<MonthlySummaryResponseDTO> listSummary(@RequestBody Integer year, @RequestBody Integer month, @RequestBody Long userId){
		return ResponseEntity.ok(summaryService.listSummary(year, month, userId));
	}

}
