package com.finances.AscacibasFinances.dto;

import java.time.LocalDateTime;

import com.finances.AscacibasFinances.enumerator.CategoryEnum;
import com.finances.AscacibasFinances.enumerator.RecurrenceEnum;

public record ExpenseResponseDTO(Long id, Double amount, CategoryEnum category, LocalDateTime date, String description, RecurrenceEnum recurrence) {

}
