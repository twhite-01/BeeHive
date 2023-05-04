package com.techelevator.model;

public class MemberBookDto {

    private Integer memberId;
    private Integer bookId;
    private boolean isCompleted;

    public MemberBookDto(){};

    public MemberBookDto(Integer memberId, Integer bookId, boolean isCompleted) {
        this.memberId = memberId;
        this.bookId = bookId;
        this.isCompleted = isCompleted;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
