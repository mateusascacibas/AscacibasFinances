package com.finances.AscacibasFinances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finances.AscacibasFinances.model.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
