package com.techelevator.dao;

import com.techelevator.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JdbcPrizeDao implements PrizeDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public JdbcPrizeDao(JdbcTemplate jdbcTemplate) {this. jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Prize getPrizeByPrizeId(int prizeId) {

        Prize prize = new Prize();

        //String sql = "SELECT * FROM prize WHERE id = ?";

        SqlRowSet rowset = jdbcTemplate.queryForRowSet(BeeHiveSql.GET_PRIZE_BY_ID.getSqlString(), prizeId);

        if(rowset.next()){
            prize = mapRowToPrize(rowset);
        }

        return prize;
    }

    @Override
    public List<Prize> getPrizesByMemberId(int memberId) {

            List<Prize> prizes = new ArrayList<>();

            String sql = "SELECT * FROM prize p\n" +
                    "INNER JOIN member_prize mp\n" +
                    "ON p.id = mp.prize_id\n" +
                    "WHERE mp.member_id = ? ;";

            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(BeeHiveSql.GET_PRIZE_BY_MEMBER_ID.getSqlString(), memberId);

        while(rowSet.next()){
            Prize prize = mapRowToPrize(rowSet);
            prizes.add(prize);
        }
        return prizes;

    }

    @Override
    public List<Prize> getPrizesById(int userId) {

        List<Prize> prizes = new ArrayList<>();

        String sql = "SELECT * FROM prize WHERE user_id = ?";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(BeeHiveSql.GET_PRIZES_BY_USER.getSqlString(),userId);

        while(rowSet.next()){
            Prize prize = mapRowToPrize(rowSet);
            prizes.add(prize);
        }
        return prizes;
    }

    public boolean checkForRowExists(MemberPrizeDto memberPrizeDto){
        boolean doesExist = false;

        String sql = "SELECT member_id\n" +
                "FROM member_prize\n" +
                "WHERE member_id = ? AND prize_id = ?";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(BeeHiveSql.CHECK_PRIZE_EXISTS.getSqlString(), memberPrizeDto.getMemberId(), memberPrizeDto.getPrizeId());

        if(rowSet.next()){
            doesExist = true;
        }
        return doesExist;
    }

 /*   public boolean addMemberPrizeTable(MemberPrizeDto memberPrizeDto){

        String sql = "INSERT INTO member_prize\n" +
                "\t (member_id, prize_id, prize_status_id)\n" +
                "\tVALUES ( ?, ?, ?);";

        return jdbcTemplate.update(sql,memberPrizeDto.getMemberId(), memberPrizeDto.getPrizeId(), memberPrizeDto.getPrizeStatusId()) == 1;

    }*/

    public boolean editMemberPrizeCompleted(MemberPrizeDto memberPrizeDto){
        String sql = "INSERT INTO member_prize(\n" +
                "\tmember_id, prize_id, prize_status_id)\n" +
                "\tVALUES (?, ?, ?);";
        return jdbcTemplate.update(BeeHiveSql.EDIT_MEMBER_PRIZE_COMPLETED.getSqlString(), memberPrizeDto.getMemberId(),
                memberPrizeDto.getPrizeId(), memberPrizeDto.getPrizeStatusId()) == 1;
    }

    //Todo does this exist?
    @Override
    public boolean parentEditPrize(int prizeId) {
    /*    String sql ="SELECT * FROM members m\n" +
                "INNER JOIN member_prize mp\n" +
                "ON m.id = mp.member_id\n" +
                "WHERE mp.prize_id = ?";*/
        BeeHiveSql.PARENT_EDIT_PRIZE.getSqlString();

        return false;
    }

    @Override
    public Integer addPrize(Prize prize) {
        String sql = "INSERT INTO prize (prize_name, description, goal_minutes, user_id, max_winners, current_winners, start_date, end_date, is_active) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id;";

        Integer newPrizeId = jdbcTemplate.queryForObject(BeeHiveSql.ADD_PRIZE.getSqlString(), Integer.class,
                prize.getPrizeName(), prize.getDescription(), prize.getGoalInMinutes(),
                prize.getUserId(), prize.getMaxWinners(), prize.getCurrentWinners(), prize.getStartDate(),
                prize.getEndDate(), prize.isActive());

        return newPrizeId;

    }

    @Override
    public boolean deletePrize(int prizeId) {
        String sql = "DELETE FROM prize\n" +
                "\tWHERE id = ? ;";
        jdbcTemplate.update(sql, prizeId);
        return true;
    }

    @Override
    public Prize editPrize(Prize prize, int prizeId) {

        String sql = "UPDATE prize\n" +
                "\tSET id=?, prize_name=?, description=?, goal_minutes=?, user_id=?, max_winners=?, current_winners=?, start_date=?, end_date=?, is_active=?\n" +
                "\tWHERE id = ?;";

         jdbcTemplate.update(BeeHiveSql.DELETE_PRIZE.getSqlString(), prize.getId(), prize.getPrizeName(), prize.getDescription(), prize.getGoalInMinutes(), prize.getUserId(), prize.getMaxWinners(), prize.getCurrentWinners(), prize.getStartDate(), prize.getEndDate(), prize.isActive(), prizeId );

         return prize;
    }

    @Override
    public MinutesReadDto getMinutesTowardPrize (int memberId, int prizeId){

        MinutesReadDto result = new MinutesReadDto();

        String sql = "SELECT SUM(minutes_read) AS total_mins\n" +
                "FROM reading_activity ra\n" +
                "INNER JOIN member_prize mp ON mp.member_id = ra.member_id\n" +
                "INNER JOIN prize p ON p.id = mp.prize_id\n" +
                "WHERE ra.date_read >= p.start_date AND ra.date_read <= p.end_date AND p.is_active = true AND mp.prize_status_id = 2 AND ra.member_id = ? AND p.id = ?";

            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(BeeHiveSql.GET_MINUTES_TOWARD_PRIZE.getSqlString(), memberId, prizeId);

            if(rowSet.next()){
                result.setMinutesRead(rowSet.getInt("total_mins"));
            }

            return result;
    }

    @Override
    public boolean addToMemberPrizeTable(int memberId, int prizeId){

        String sql = "INSERT INTO member_prize (member_id, prize_id, prize_status_id) VALUES (?, ?, ?)";
        return jdbcTemplate.update(BeeHiveSql.ADD_TO_MEMBER_PRIZE_TABLE.getSqlString(), memberId, prizeId, 2) == 1;


    }

    @Override
    public boolean checkForPrize(Prize prize){
        boolean doesExist = false;

        String sql = "Select id from prize where id = ?";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(BeeHiveSql.CHECK_FOR_PRIZE.getSqlString(),prize.getId());

        if(rowSet.next()){
            doesExist = true;
        }
        return doesExist;
    }


    //public boolean editMemberPrizeCompleted()



    private Prize mapRowToPrize (SqlRowSet rowSet){
        Prize result = new Prize();

        result.setId(rowSet.getInt("id"));
        result.setPrizeName(rowSet.getString("prize_name"));
        result.setDescription(rowSet.getString("description"));
        result.setGoalInMinutes(rowSet.getInt("goal_minutes"));
        result.setUserId(rowSet.getInt("user_id"));
        result.setMaxWinners(rowSet.getInt("max_winners"));
        result.setCurrentWinners(rowSet.getInt("current_winners"));
        if (rowSet.getDate("start_date") != null) {
            result.setStartDate(rowSet.getDate("start_date").toLocalDate());
        if (rowSet.getDate("end_date") != null) {
            result.setEndDate(rowSet.getDate("end_date").toLocalDate());
        result.setActive(rowSet.getBoolean("is_active"));
        }

    }
        return result;
    }

    private MemberPrizeDto mapRowToMemberPrize(SqlRowSet rowSet)
    {
        MemberPrizeDto result = new MemberPrizeDto();

        result.setMemberId(rowSet.getInt("member_id"));
        result.setPrizeId(rowSet.getInt("prize_id"));
        result.setPrizeStatusId(rowSet.getInt("prize_status_id"));

        return result;

    }





}

