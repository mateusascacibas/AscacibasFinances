package com.finances.AscacibasFinances.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finances.AscacibasFinances.model.Revenue;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue, Long> {

	Optional<List<Revenue>> findByUserId(Long userId);

}
