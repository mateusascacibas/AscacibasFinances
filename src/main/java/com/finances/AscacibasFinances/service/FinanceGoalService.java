package com.finances.AscacibasFinances.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finances.AscacibasFinances.dto.FinanceGoalRequestDTO;
import com.finances.AscacibasFinances.dto.FinanceGoalResponseDTO;
import com.finances.AscacibasFinances.mapper.FinanceGoalMapper;
import com.finances.AscacibasFinances.model.FinanceGoal;
import com.finances.AscacibasFinances.model.User;
import com.finances.AscacibasFinances.repository.FinanceGoalRepository;
import com.finances.AscacibasFinances.repository.UserRepository;

@Service
public class FinanceGoalService {
	
	@Autowired
	private FinanceGoalRepository financeGoalRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public FinanceGoalResponseDTO createFinanceGoal(FinanceGoalRequestDTO request) {
		try {
			User user = userRepository.findById(request.userId()).orElseThrow(() -> new RuntimeException("User not found"));
			FinanceGoal finance = FinanceGoalMapper.toEntity(request, user);
			financeGoalRepository.save(finance);
			return FinanceGoalMapper.toDTO(finance);
		} catch(Exception e) {
			System.out.println("Error creating finance goal");
			throw e;
		}
	}
	
	public List<FinanceGoalResponseDTO> listAllFinanceGoals(){
		List<FinanceGoal> list = financeGoalRepository.findAll();
		List<FinanceGoalResponseDTO> response = list.stream().map(FinanceGoalMapper::toDTO).toList();
		return response;
	}

}
