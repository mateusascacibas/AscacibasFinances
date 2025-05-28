package com.finances.AscacibasFinances.dto;

import java.time.LocalDateTime;

public record UserResponseDTO(Long id, String name, String email, LocalDateTime createdDate) {
}
