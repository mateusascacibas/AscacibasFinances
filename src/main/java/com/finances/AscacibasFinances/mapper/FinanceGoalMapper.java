package com.finances.AscacibasFinances.mapper;

import com.finances.AscacibasFinances.dto.FinanceGoalRequestDTO;
import com.finances.AscacibasFinances.dto.FinanceGoalResponseDTO;
import com.finances.AscacibasFinances.model.FinanceGoal;
import com.finances.AscacibasFinances.model.User;

public class FinanceGoalMapper {

	public static FinanceGoal toEntity(FinanceGoalRequestDTO dto, User user) {
		FinanceGoal financeGoal = new FinanceGoal();
		financeGoal.setProgress(dto.progress());
		financeGoal.setTargetAmount(dto.targetAmount());
		financeGoal.setType(dto.type());
		financeGoal.setUser(user);
		return financeGoal;
	}
	
	public static FinanceGoalResponseDTO toDTO(FinanceGoal financeGoal) {
		return new FinanceGoalResponseDTO(financeGoal.getId(), financeGoal.getType(), financeGoal.getTargetAmount(), financeGoal.getProgress());
	}
}
