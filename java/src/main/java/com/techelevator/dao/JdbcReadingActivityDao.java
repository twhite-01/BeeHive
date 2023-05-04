package com.techelevator.dao;

import com.techelevator.model.ReadingActivity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcReadingActivityDao implements ReadingActivityDao{

    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public JdbcReadingActivityDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer addReadingActivity(ReadingActivity read){
        String sql = "INSERT INTO reading_activity( member_id, book_id, minutes_read, notes, format_id, request_status_id, date_read)\n " +
                "\tVALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id; ";

        Integer newReadingId = jdbcTemplate.queryForObject(sql, Integer.class, read.getMemberId(), read.getBookId(), read.getMinutesRead(),
                read.getNotes(), read.getFormatId(), read.getRequestId(), read.getDateRead());

        return newReadingId;
    }

    @Override
    public List<ReadingActivity> allActivityByMemId(int memberId) {
        List<ReadingActivity> reads = new ArrayList<>();

        String sql = "SELECT * FROM reading_activity \n" +
                "WHERE member_id = ? ";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql,memberId);

        while(rowSet.next()){
            ReadingActivity read = mapRowToRead(rowSet);
            reads.add(read);
        }
        return reads;
    }

    @Override
    public ReadingActivity editActivity( ReadingActivity read) {
        String sql = "UPDATE reading_activity\n" +
                "\tSET member_id=?, book_id=?, minutes_read=?, notes=?, format_id=?, request_status_id=?, date_read=?\n" +
                "\tWHERE id = ?;";

         jdbcTemplate.update(sql, read.getMemberId(), read.getBookId(),
                read.getMinutesRead(), read.getNotes(), read.getFormatId(), read.getRequestId(), read.getDateRead(), read.getId());


        return read;

    }

    @Override
    public Boolean deleteActivity(int readingId) {
        String sql = "DELETE FROM reading_activity\n" +
                "\tWHERE id = ? ";
        jdbcTemplate.update(sql,readingId);
        return true;
    }


    @Override
    public List<ReadingActivity> pendingReading(int userId){

        List<ReadingActivity> requests = new ArrayList<>();

        String sql = "SELECT ra.id, ra.member_id, ra.book_id, ra.minutes_read, ra.notes, ra.format_id, ra.request_status_id, ra.date_read FROM reading_activity ra\n" +
                "INNER JOIN members me ON ra.member_id = me.id\n" +
                "WHERE user_id = ? AND request_status_id = 2";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);

        while(rowSet.next()){
            ReadingActivity read = mapRowToRead(rowSet);
            requests.add(read);
        }
    return requests;
    }

    private ReadingActivity mapRowToRead(SqlRowSet rowSet){
        ReadingActivity result = new ReadingActivity();

        result.setId(rowSet.getInt("id"));
        result.setMemberId(rowSet.getInt("member_id"));
        result.setBookId(rowSet.getInt("book_id"));
        result.setMinutesRead(rowSet.getInt("minutes_read"));
        result.setNotes(rowSet.getString("notes"));
        result.setFormatId(rowSet.getInt("format_id"));
        result.setRequestId(rowSet.getInt("request_status_id"));
        if (rowSet.getDate("date_read") != null) {
            result.setDateRead(rowSet.getDate("date_read").toLocalDate());
        }

        return result;
    }
}



