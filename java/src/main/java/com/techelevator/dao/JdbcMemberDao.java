package com.techelevator.dao;

import com.techelevator.model.FamilyNameDto;
import com.techelevator.model.Member;
import com.techelevator.model.MinutesReadDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMemberDao implements MemberDao{

    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public JdbcMemberDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Member getMemberById(int memberId) {

       Member member = null;

        String sql = "SELECT * FROM members WHERE id = ?";

        SqlRowSet rowset = jdbcTemplate.queryForRowSet(sql, memberId);

        if(rowset.next()){
            member = mapRowToMember(rowset);
        }

        return member;

    }

    @Override
    public List<Member> getMembersByUserId(int userId) {
        List<Member> members = new ArrayList<>();

        String sql = "SELECT *\n" +
                "FROM members\n" +
                "WHERE user_id = ?";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql,userId);

        while(rowSet.next()){
            Member member = mapRowToMember(rowSet);
            members.add(member);
        }
        return members;
    }

    @Override
    public Integer addMember(Member member) {

        String sql = "INSERT INTO members(is_child, user_id, first_name, last_initial, avatar_id, pin) " +
                "VALUES (?, ?, ?, ?, ?, ?) RETURNING id;";

        Integer newMemberId = jdbcTemplate.queryForObject(sql, Integer.class, member.isChild(), member.getUserId(), member.getFirstName(),
                member.getLastInitial(), member.getAvatarId(), member.getPin());

        return newMemberId;

    }

    @Override
    public boolean deleteMember(int memberId) {
        String sql0 =" DELETE FROM reading_activity\n" +
                "\tWHERE member_id = ?;";
        jdbcTemplate.update(sql0, memberId);
        String sql1 =" DELETE FROM member_book\n" +
                "\tWHERE member_id = ?;";
        jdbcTemplate.update(sql1, memberId);
        String sql2 =" DELETE FROM member_prize\n" +
                "\tWHERE member_id = ?;";
        jdbcTemplate.update(sql2, memberId);
        String sql3 = "DELETE FROM members\n" +
                "\tWHERE id = ? ;";
        jdbcTemplate.update(sql3,memberId);
        return true;
    }

    @Override
    public Member editMember(Member member, int memberId){

        String sql = "UPDATE members\n" +
                "\tSET id=?, is_child=?, user_id=?, first_name=?, last_initial=?, avatar_id=?, pin=?\n" +
                "\tWHERE id = ? ; ";

        jdbcTemplate.update(sql, member.getId(), member.isChild(), member.getUserId(), member.getFirstName(), member.getLastInitial(), member.getAvatarId(), member.getPin(), memberId);

        return member;
    }

    @Override
    public MinutesReadDto getTotalMinutesByMember (int memberId) {

        MinutesReadDto result = new MinutesReadDto();

        String sql = "SELECT SUM(minutes_read) as total_mins\n" +
                            "FROM reading_activity\n" +
                            "WHERE member_id = ? AND request_status_id = 1 ";

         SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, memberId);

         if (rowSet.next()){
              result = mapRowToMinutes(rowSet);
         }

        return result;
    }

    @Override
    public List<Member> getAllMembersForAPrizeId(int prizeId){

        List<Member> memberNames = new ArrayList<>();

        String sql = "SELECT m.id, m.first_name, m.last_initial, m.is_child, m.user_id, m.avatar_id, m.pin FROM members m\n" +
                "INNER JOIN member_prize mp\n" +
                "ON m.id = mp.member_id\n" +
                "WHERE mp.prize_id = ?";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, prizeId);

        while(rowSet.next()) {
            Member member = mapRowToMember(rowSet);
            memberNames.add(member);

        }
        return memberNames;
    }




    private Member mapRowToMember(SqlRowSet rowSet){

        Member result = new Member();

        result.setId(rowSet.getInt("id"));
        result.setChild(rowSet.getBoolean("is_child"));
        result.setUserId(rowSet.getInt("user_id"));
        result.setFirstName(rowSet.getString("first_name"));
        result.setLastInitial(rowSet.getString("last_initial"));
        result.setAvatarId(rowSet.getString("avatar_id"));
        result.setPin(rowSet.getString("pin"));

        return result;

    }

    private MinutesReadDto mapRowToMinutes(SqlRowSet rowSet){

        MinutesReadDto result = new MinutesReadDto();

        result.setMinutesRead(rowSet.getInt("total_mins"));

        return result;


    }


}