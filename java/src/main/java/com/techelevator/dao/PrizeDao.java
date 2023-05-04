package com.techelevator.dao;

import com.techelevator.model.Member;
import com.techelevator.model.MemberPrizeDto;
import com.techelevator.model.MinutesReadDto;
import com.techelevator.model.Prize;

import java.util.List;
import java.util.Map;

public interface PrizeDao {

    Prize getPrizeByPrizeId(int prizeId);

    List<Prize> getPrizesByMemberId(int memberId);

    List<Prize> getPrizesById (int userId);

    Integer addPrize (Prize prize);

    boolean addToMemberPrizeTable (int memberId, int prizeId);

    boolean deletePrize (int prizeId);

    Prize editPrize (Prize prize, int prizeId);

    MinutesReadDto getMinutesTowardPrize (int memberId, int prizeId);

    boolean checkForRowExists(MemberPrizeDto memberPrizeDto);

    //boolean addMemberPrizeTable(MemberPrizeDto memberPrizeDto);

    boolean editMemberPrizeCompleted(MemberPrizeDto memberPrizeDto);

    boolean parentEditPrize(int prizeId);

//    List<Member> getAllMembersForAPrizeId(int prizeId);

    boolean checkForPrize(Prize prize);



}
