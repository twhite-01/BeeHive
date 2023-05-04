package com.techelevator.model;

import java.time.LocalDate;

public class Prize {

        private int id;
        private String prizeName;
        private String description;
        private int goalInMinutes;
        private int userId;
        private int maxWinners;
        private int currentWinners;
        private LocalDate startDate;
        private LocalDate endDate;
        private boolean isActive;
        private Integer[] memberIds;


    public Prize(){};

    public Prize(int id, String prizeName, String description, int goalInMinutes, int userId, int maxWinners, LocalDate startDate, LocalDate endDate, boolean isActive) {
        this.id = id;
        this.prizeName = prizeName;
        this.description = description;
        this.goalInMinutes = goalInMinutes;
        this.userId = userId;
        this.maxWinners = maxWinners;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
    }

    public Integer[] getMemberIds() {
        return memberIds;
    }

    public void setMemberIds(Integer[] memberIds) {
        this.memberIds = memberIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGoalInMinutes() {
        return goalInMinutes;
    }

    public void setGoalInMinutes(int goalInMinutes) {
        this.goalInMinutes = goalInMinutes;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMaxWinners() {
        return maxWinners;
    }

    public void setMaxWinners(int maxWinners) {
        this.maxWinners = maxWinners;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getCurrentWinners() {
        return currentWinners;
    }

    public void setCurrentWinners(int currentWinners) {
        this.currentWinners = currentWinners;
    }
}
