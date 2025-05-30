package com.finances.AscacibasFinances.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finances.AscacibasFinances.dto.MonthlySummaryResponseDTO;
import com.finances.AscacibasFinances.repository.MonthlySummaryRepository;

@Service
public class SummaryService {

	@Autowired
	private MonthlySummaryRepository summaryRepository;
	
	public MonthlySummaryResponseDTO listSummary(Integer year, Integer month, Long userId) {
		if (year == null || month == null || userId == null) {
			throw new IllegalArgumentException("Year, month and userId are required.");
		}
		
		return summaryRepository.findByYearAndMonthAndUserId(year, month, userId)
				.map(summary -> new MonthlySummaryResponseDTO(
						summary.getYear(),
						summary.getMonth(),
						summary.getRevenueTotal(),
						summary.getExpenseTotal(),
						summary.getBalance()
				))
				.orElseThrow(() -> new RuntimeException("Monthly summary not found."));
	}

}
