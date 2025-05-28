package com.finances.AscacibasFinances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finances.AscacibasFinances.model.FinanceGoal;

@Repository
public interface FinanceGoalRepository extends JpaRepository<FinanceGoal, Long> {

}
