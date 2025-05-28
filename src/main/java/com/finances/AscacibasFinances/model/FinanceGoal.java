package com.finances.AscacibasFinances.model;

import com.finances.AscacibasFinances.enumerator.GoalTypeEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class FinanceGoal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	private GoalTypeEnum type;
	private Double targetAmount;
	private Double progress;
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GoalTypeEnum getType() {
		return type;
	}

	public void setType(GoalTypeEnum type) {
		this.type = type;
	}

	public Double getTargetAmount() {
		return targetAmount;
	}

	public void setTargetAmount(Double targetAmount) {
		this.targetAmount = targetAmount;
	}

	public Double getProgress() {
		return progress;
	}

	public void setProgress(Double progress) {
		this.progress = progress;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
