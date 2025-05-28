package com.finances.AscacibasFinances.dto;

import java.time.LocalDateTime;

import com.finances.AscacibasFinances.enumerator.CategoryEnum;
import com.finances.AscacibasFinances.enumerator.RecurrenceEnum;

public record ExpenseRequestDTO(Double amount, CategoryEnum category, LocalDateTime date, String description, RecurrenceEnum recurrence, Long userId) {

}
