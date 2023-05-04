package com.techelevator.dao;

import com.techelevator.model.Member;
import com.techelevator.model.MinutesReadDto;

import java.util.List;

public interface MemberDao {

    Member getMemberById(int userId);

    List<Member> getMembersByUserId(int userId);

    Integer addMember(Member member);

    boolean deleteMember(int memberId);

    Member editMember(Member member, int memberId);

    MinutesReadDto getTotalMinutesByMember (int memberId);

    List<Member> getAllMembersForAPrizeId(int prizeId);

}
