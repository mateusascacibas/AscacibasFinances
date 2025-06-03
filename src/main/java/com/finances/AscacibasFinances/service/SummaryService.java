package com.finances.AscacibasFinances.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finances.AscacibasFinances.dto.MonthlySummaryResponseDTO;
import com.finances.AscacibasFinances.model.Expense;
import com.finances.AscacibasFinances.model.Revenue;
import com.finances.AscacibasFinances.repository.ExpenseRepository;
import com.finances.AscacibasFinances.repository.RevenueRepository;

@Service
public class SummaryService {

	@Autowired
	private RevenueRepository revenueRepository;
	
	@Autowired
	private ExpenseRepository expenseRepository;
	
	public MonthlySummaryResponseDTO listSummary(Integer year, Integer month, Long userId) {
		if (year == null || month == null || userId == null) {
			throw new IllegalArgumentException("Year, month and User Id are required.");
		}
		
		Optional<List<Revenue>> listRevenue = revenueRepository.findByUserId(userId);
		Optional<List<Expense>> listExpense = expenseRepository.findByUserId(userId);
		Double sumRevenue = listRevenue.orElseThrow(() -> new RuntimeException("Revenue not found")).stream().filter(m-> m.getDate().getYear() == year).filter(m -> m.getDate().getMonthValue() == month).mapToDouble(Revenue::getAmount).sum();
		Double sumExpense = listExpense.orElseThrow(() -> new RuntimeException("Expense not found")).stream().filter(m-> m.getDate().getYear() == year).filter(m -> m.getDate().getMonthValue() == month).mapToDouble(Expense::getAmount).sum();
		
		return new MonthlySummaryResponseDTO(year, month, sumRevenue, sumExpense, sumRevenue-sumExpense);
	}

}
