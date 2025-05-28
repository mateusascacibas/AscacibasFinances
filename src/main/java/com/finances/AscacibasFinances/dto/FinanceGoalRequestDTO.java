package com.finances.AscacibasFinances.dto;

import com.finances.AscacibasFinances.enumerator.GoalTypeEnum;

public record FinanceGoalRequestDTO(GoalTypeEnum type, Double targetAmount, Double progress, Long userId) {

}
