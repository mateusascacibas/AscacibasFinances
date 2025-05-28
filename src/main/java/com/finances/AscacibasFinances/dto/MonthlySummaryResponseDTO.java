package com.finances.AscacibasFinances.dto;

public record MonthlySummaryResponseDTO(Integer year, Integer month, Double revenueTotal, Double expenseTotal, Double balance) {

}
