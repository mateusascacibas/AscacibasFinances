package com.finances.AscacibasFinances.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public RevenueResponseDTO createRevenue(RevenueRequestDTO request) {
		User user = userRepository.findById(request.userId()).orElseThrow(() -> new RuntimeException("User not found"));
		try {			
			Revenue revenue = RevenueMapper.toEntity(request, user);
			revenueRepository.save(revenue);
			return RevenueMapper.toDTO(revenue);
		} catch(Exception e) {
			System.out.println("Error creating revenue");
			throw e;
		}
	}
	
	public List<RevenueResponseDTO> listAllRevenues(){
		List<Revenue> list = revenueRepository.findAll();
		List<RevenueResponseDTO> response = list.stream().map(RevenueMapper::toDTO).toList();
		return response;
	}

}
