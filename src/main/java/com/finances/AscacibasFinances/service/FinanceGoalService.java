package com.finances.AscacibasFinances.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finances.AscacibasFinances.comuns.ResponseMessage;
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
	
	public ResponseMessage createFinanceGoal(FinanceGoalRequestDTO request) {
		try {
			User user = userRepository.findById(request.userId()).orElseThrow(() -> new RuntimeException("User not found"));
			FinanceGoal finance = FinanceGoalMapper.toEntity(request, user);
			financeGoalRepository.save(finance);
			return new ResponseMessage(true, "Finance goal created with success.");
		} catch(Exception e) {
			return new ResponseMessage(false, "Error creating finance goal" + e.getMessage());
		}
	}
	
	public ResponseMessage deleteFinanceGoal(Long id) {
		try {
			FinanceGoal financeGoal = financeGoalRepository.findById(id).orElseThrow(() -> new RuntimeException("Finance goal not found"));
			financeGoalRepository.delete(financeGoal);
			return new ResponseMessage(true, "Finance goal deleted with success.");
		} catch(Exception e) {
			return new ResponseMessage(false, "Error deleting finance goal -> " + e.getMessage());
		}
	}
	
	public List<FinanceGoalResponseDTO> listAllFinanceGoals(){
		List<FinanceGoal> list = financeGoalRepository.findAll();
		List<FinanceGoalResponseDTO> response = list.stream().map(FinanceGoalMapper::toDTO).toList();
		return response;
	}

}
