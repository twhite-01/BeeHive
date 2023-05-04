package com.techelevator.dao;

import com.techelevator.model.Book;
import com.techelevator.model.MemberBookDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.RowSet;
import java.util.ArrayList;
import java.util.List;


@Component
public class JdbcBookDao implements BookDao{



    private JdbcTemplate jdbcTemplate = new JdbcTemplate();
    public JdbcBookDao(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Book getBookByID(int id)
    {
        Book book = null;

        String sql = "SELECT * FROM book WHERE id = ?";

        SqlRowSet rowset = jdbcTemplate.queryForRowSet(sql,id);

        if(rowset.next()){
            book = mapRowToBook(rowset);
        }
        return book;
    }

    @Override
    public List<Book> getAllBooksByUserId(int memberId)
    {
        List<Book> books = new ArrayList<>();

        String sql ="SELECT * FROM book WHERE id = ? ";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql,memberId);
        while(rowSet.next())
        {
            Book book = mapRowToBook(rowSet);
            books.add(book);
        }
        return books;
    }

    @Override
    public boolean deleteBook(int id)
    {
        String sql = "DELETE FROM book WHERE id = ?";
        return true;
    }

    @Override
    public Integer addBook(Book book)
    {
        String sql = "INSERT INTO book(isbn,cover_url,title,author) " +
        "VALUES(?,?,?,?) RETURNING id;";

        Integer newBookId = jdbcTemplate.queryForObject(sql,Integer.class,book.getISBN(),book.getCoverUrl(),
                book.getTitle(),book.getAuthor());

        return newBookId;
    }

    @Override
    public List<Integer> getRecentBooks(int memberId){

        List<Integer> books = new ArrayList<>();

        String sql = "SELECT DISTINCT book_id, MAX(date_read) AS most_recent\n" +
                "FROM reading_activity\n" +
                "WHERE member_id = ?\n" +
                "GROUP BY book_id\n" +
                "ORDER BY most_recent DESC\n" +
                "LIMIT 3";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, memberId);

        while(rowSet.next()){
            Integer bookId = rowSet.getInt("book_id");
            books.add(bookId);
        }
        return books;
    }

    public List<Book> userBookList (int memberId){
        List<Book> books = new ArrayList<>();

        String sql = "SELECT b.id, b.isbn, b.cover_url,  b.title, b.author, mb.is_completed\n" +
                "FROM book b\n" +
                "INNER JOIN member_book mb\n" +
                "\tON b.id=mb.book_id\n" +
                "WHERE mb.member_id = ?" ;
        SqlRowSet rowSet =  jdbcTemplate.queryForRowSet(sql, memberId);

        while(rowSet.next()){
            Book book = mapRowToBookComp(rowSet);
            books.add(book);
        }
        return books;
    }

    public boolean addMemberBookTable(MemberBookDto memberBook){

        String sql = "INSERT INTO member_book(\n" +
                "\t member_id, book_id, is_completed)\n" +
                "\tVALUES ( ?, ?, ?);";

       return jdbcTemplate.update(sql,memberBook.getMemberId(), memberBook.getBookId(), memberBook.isCompleted()) == 1;

    }

    public boolean checkForRowExists(MemberBookDto memberBookDto){
        boolean doesExist = false;

        String sql = "SELECT member_id\n" +
                "FROM member_book\n" +
                "WHERE member_id = ? AND book_id = ?";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, memberBookDto.getMemberId(), memberBookDto.getBookId());

        if(rowSet.next()){
            doesExist = true;
        }
        return doesExist;
    }



    public boolean editMemberBookCompleted(MemberBookDto memberBook){
        String sql = "UPDATE member_book\n" +
                "\tSET member_id=?, book_id=?, is_completed= ?\n" +
                "\tWHERE member_id =? AND book_id = ? ;";
        return jdbcTemplate.update(sql, memberBook.getMemberId(), memberBook.getBookId(), memberBook.isCompleted(), memberBook.getMemberId(),memberBook.getBookId()) == 1;
    }

    private Book mapRowToBook(SqlRowSet rowSet){
        Book result = new Book();

        result.setId(rowSet.getInt("id"));
        result.setTitle(rowSet.getString("title"));
        result.setAuthor(rowSet.getString("author"));
        result.setISBN(rowSet.getString("isbn"));
        result.setCoverUrl(rowSet.getString("cover_url"));
        return result;

    }

    private Book mapRowToBookComp(SqlRowSet rowSet){
        Book result = new Book();

        result.setId(rowSet.getInt("id"));
        result.setTitle(rowSet.getString("title"));
        result.setAuthor(rowSet.getString("author"));
        result.setISBN(rowSet.getString("isbn"));
        result.setCoverUrl(rowSet.getString("cover_url"));
        result.setCompleted(rowSet.getBoolean("is_completed"));


        return result;

    }
}
