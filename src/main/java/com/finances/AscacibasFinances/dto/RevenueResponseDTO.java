package com.finances.AscacibasFinances.dto;

import java.time.LocalDateTime;

import com.finances.AscacibasFinances.enumerator.TypeEnum;

public record RevenueResponseDTO(Long id, Double amount, LocalDateTime date, String description, TypeEnum type, Long userId) {

}
