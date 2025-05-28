package com.finances.AscacibasFinances.mapper;

import com.finances.AscacibasFinances.dto.RevenueRequestDTO;
import com.finances.AscacibasFinances.dto.RevenueResponseDTO;
import com.finances.AscacibasFinances.model.Revenue;
import com.finances.AscacibasFinances.model.User;

public class RevenueMapper {

	public static Revenue toEntity(RevenueRequestDTO dto, User user) {
		Revenue revenue = new Revenue();
		revenue.setUser(user);
		revenue.setDescription(dto.description());
		revenue.setType(dto.type());
		revenue.setAmount(dto.amount());
		revenue.setDate(dto.date());
		return revenue;
	}
	
	public static RevenueResponseDTO toDTO(Revenue revenue) {
		return new RevenueResponseDTO(revenue.getId(), revenue.getAmount(), revenue.getDate(), revenue.getDescription(), revenue.getType(), revenue.getUser().getId());
	}
}
