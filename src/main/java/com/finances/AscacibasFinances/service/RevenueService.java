package com.finances.AscacibasFinances.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finances.AscacibasFinances.comuns.ResponseMessage;
import com.finances.AscacibasFinances.dto.RevenueRequestDTO;
import com.finances.AscacibasFinances.dto.RevenueResponseDTO;
import com.finances.AscacibasFinances.mapper.RevenueMapper;
import com.finances.AscacibasFinances.model.Revenue;
import com.finances.AscacibasFinances.model.User;
import com.finances.AscacibasFinances.repository.RevenueRepository;
import com.finances.AscacibasFinances.repository.UserRepository;

@Service
public class RevenueService {
	
	@Autowired
	private RevenueRepository revenueRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public ResponseMessage createRevenue(RevenueRequestDTO request) {
		User user = userRepository.findById(request.userId()).orElseThrow(() -> new RuntimeException("User not found"));
		try {			
			Revenue revenue = RevenueMapper.toEntity(request, user);
			revenueRepository.save(revenue);
			return new ResponseMessage(true, "Revenue created with success.");
		} catch(Exception e) {
			return new ResponseMessage(false, "Error creating revenue -> " + e.getMessage());
		}
	}
	
	public ResponseMessage deleteRevenue(Long id) {
		try {
			Revenue revenue = revenueRepository.findById(id).orElseThrow(() -> new RuntimeException("Revenue not found"));
			revenueRepository.delete(revenue);
			return new ResponseMessage(true, "Revenue deleted with success.");
		} catch(Exception e) {
			return new ResponseMessage(false, "Error deleting revenue -> " + e.getMessage());
		}
	}
	
	public List<RevenueResponseDTO> listAllRevenues(){
		List<Revenue> list = revenueRepository.findAll();
		List<RevenueResponseDTO> response = list.stream().map(RevenueMapper::toDTO).toList();
		return response;
	}

}
