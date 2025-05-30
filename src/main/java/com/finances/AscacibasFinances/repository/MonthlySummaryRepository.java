package com.finances.AscacibasFinances.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finances.AscacibasFinances.model.MonthlySummary;

@Repository
public interface MonthlySummaryRepository extends JpaRepository<MonthlySummary, Long> {
	
	Optional<MonthlySummary> findByYearAndMonthAndUserId(Integer year, Integer month, Long userId);


}
