package com.techelevator.dao;

import com.techelevator.model.Book;
import com.techelevator.model.MemberBookDto;

import java.util.List;

public interface BookDao {
    Book getBookByID(int id);
    List<Book> getAllBooksByUserId(int memberId);
    boolean deleteBook(int id);
    Integer addBook(Book book);
    List<Integer> getRecentBooks(int memberId);
    List<Book> userBookList (int memberId);
    boolean addMemberBookTable(MemberBookDto memberBook);
    boolean editMemberBookCompleted(MemberBookDto memberBook);
    public boolean checkForRowExists(MemberBookDto memberBookDto);
}
