package com.techelevator.model;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public class ReadingActivity {

    private int id;
    private int memberId;
    private int bookId;
    private int minutesRead;
    private String notes;
    private int formatId;
    private int requestId;
    private LocalDate dateRead;

    public ReadingActivity(){};

    public ReadingActivity(int id, int memberId, int bookId, int minutesRead, String notes, int formatId, int requestId, LocalDate dateRead) {
        this.id = id;
        this.memberId = memberId;
        this.bookId = bookId;
        this.minutesRead = minutesRead;
        this.notes = notes;
        this.formatId = formatId;
        this.requestId = requestId;
        this.dateRead = dateRead;
    }

    public LocalDate getDateRead() {
        return dateRead;
    }

    public void setDateRead(LocalDate dateRead) {
        this.dateRead = dateRead;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getMinutesRead() {
        return minutesRead;
    }

    public void setMinutesRead(int minutesRead) {
        this.minutesRead = minutesRead;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getFormatId() {
        return formatId;
    }

    public void setFormatId(int formatId) {
        this.formatId = formatId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }
}
