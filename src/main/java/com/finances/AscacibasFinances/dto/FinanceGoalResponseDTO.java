package com.finances.AscacibasFinances.dto;

import com.finances.AscacibasFinances.enumerator.GoalTypeEnum;

public record FinanceGoalResponseDTO(Long id, GoalTypeEnum type, Double targetAmount, Double progress) {

}
