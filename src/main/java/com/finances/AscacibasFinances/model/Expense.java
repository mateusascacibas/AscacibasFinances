package com.finances.AscacibasFinances.model;

import java.time.LocalDateTime;

import com.finances.AscacibasFinances.enumerator.CategoryEnum;
import com.finances.AscacibasFinances.enumerator.RecurrenceEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Expense {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double amount;
	@Enumerated(EnumType.STRING)
	private CategoryEnum category;
	private LocalDateTime date;
	private String description;
	@Enumerated(EnumType.STRING)
	private RecurrenceEnum recurrence;
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public CategoryEnum getCategory() {
		return category;
	}
	public void setCategory(CategoryEnum category) {
		this.category = category;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public RecurrenceEnum getRecurrence() {
		return recurrence;
	}
	public void setRecurrence(RecurrenceEnum recurrence) {
		this.recurrence = recurrence;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	

}
