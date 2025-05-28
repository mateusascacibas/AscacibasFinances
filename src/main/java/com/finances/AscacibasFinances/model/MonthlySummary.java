package com.finances.AscacibasFinances.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class MonthlySummary {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer year;
	private Integer month;
	private Double revenueTotal;
	private Double expenseTotal;
	private Double balance;
	@ManyToOne
	@JoinColumn(name="user_id", nullable = false)
	private User user;
	
	@PrePersist
	@PreUpdate
	public void balanceCalculate() {
		if(this.revenueTotal != null && this.expenseTotal != null) {
			this.balance = this.revenueTotal - this.expenseTotal;
		}
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Double getRevenueTotal() {
		return revenueTotal;
	}
	public void setRevenueTotal(Double revenueTotal) {
		this.revenueTotal = revenueTotal;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Double getExpenseTotal() {
		return expenseTotal;
	}
	public void setExpenseTotal(Double expenseTotal) {
		this.expenseTotal = expenseTotal;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	
}
