package com.finances.AscacibasFinances.dto;


import java.time.LocalDateTime;

public class FilterDateDTO {
    private LocalDateTime startDate;
    private LocalDateTime finalDate;

    // Getters e setters
    public LocalDateTime getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getFinalDate() {
        return finalDate;
    }
    public void setFinalDate(LocalDateTime finalDate) {
        this.finalDate = finalDate;
    }
}
