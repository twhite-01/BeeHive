package com.techelevator.services;

import com.techelevator.dao.*;
import com.techelevator.model.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.List;

@Component
public class HiveService {

    private UserDao userDao;
    private MemberDao memberDao;
    private PrizeDao prizeDao;
    private BookDao bookDao;
    private ReadingActivityDao readDao;

    public HiveService(UserDao userDao, MemberDao memberDao, PrizeDao prizeDao, BookDao bookDao,
                       ReadingActivityDao readDao) {
        this.userDao = userDao;
        this.memberDao = memberDao;
        this.prizeDao = prizeDao;
        this.bookDao = bookDao;
        this.readDao = readDao;
    }

    public int insertUserIdToNewMember(Member newMember, Principal principal) {

        String username = principal.getName();
        int userId = userDao.findIdByUsername(username);

        newMember.setUserId(userId);

        return memberDao.addMember(newMember);

    }

    public Integer addPrize(Prize newPrize) {

        Integer newId = prizeDao.addPrize(newPrize);

        Integer[] membersEligible = newPrize.getMemberIds();

        for (Integer member : membersEligible) {
            prizeDao.addToMemberPrizeTable(member, newId);
        }
        return newId;
    }

    public boolean postOrPutMemberBook(MemberBookDto memberBookDto) {
        boolean exist = bookDao.checkForRowExists(memberBookDto);

        if (exist == false) {
            return bookDao.addMemberBookTable(memberBookDto);
        } else {
            return bookDao.editMemberBookCompleted(memberBookDto);
        }
    }

    public Member getMemberById(int id) {
        return memberDao.getMemberById(id);
    }

    public List<Member> getAllMembers(Principal principal) {
        return memberDao.getMembersByUserId(userDao.findIdByUsername(principal.getName()));
    }

    public Member editMember(Member member, int memberId) {
        return memberDao.editMember(member, memberId);
    }

    public Boolean deleteMember(int memberId) {
        return memberDao.deleteMember(memberId);
    }

    public MinutesReadDto getTotalMinutesByMember(int memberId) {
        return memberDao.getTotalMinutesByMember(memberId);
    }

    public ReadingActivity editActivity(ReadingActivity read) {
        return readDao.editActivity(read);
    }

    public Integer addReadingActivity(ReadingActivity read) {
        return readDao.addReadingActivity(read);
    }

    public List<ReadingActivity> getAllReadsByMemId(int memberId) {
        return readDao.allActivityByMemId(memberId);
    }

    public Boolean deleteActivity(int activityId) {
        return readDao.deleteActivity(activityId);
    }

    public List<ReadingActivity> pendingRequest(Principal principal) {
        return readDao.pendingReading(userDao.findIdByUsername(principal.getName()));
    }

    public Prize getPrizeByPrizeId(int prizeId) {
        return prizeDao.getPrizeByPrizeId(prizeId);
    }

    public List<Prize> getPrizesByMemberId(int memberId) {
        return prizeDao.getPrizesByMemberId(memberId);
    }

    public List<Prize> getPrizesById(Principal principal) {
        return prizeDao.getPrizesById(userDao.findIdByUsername(principal.getName()));
    }

    public boolean deletePrize(int prizeId) {
        return prizeDao.deletePrize(prizeId);
    }

    public Prize editPrize(Prize prize, int prizeId) {
        return prizeDao.editPrize(prize, prizeId);
    }

    public MinutesReadDto getMinutesTowardPrize(int memberId, int prizeId) {
        return prizeDao.getMinutesTowardPrize(memberId, prizeId);
    }

    public boolean addFamilyName(FamilyNameDto famName) {
        return userDao.updateFamilyName(famName);
    }


    public boolean postOrPutMemberPrize(MemberPrizeDto memberPrizeDto) {
        return prizeDao.editMemberPrizeCompleted(memberPrizeDto);
    }


    public Integer postOrPutPrize(Prize prize) {
        boolean exist = prizeDao.checkForPrize(prize);

        if (exist == false) {
            prizeDao.addPrize(prize);
            return prize.getId();
        } else {
            prizeDao.editPrize(prize,prize.getId());
            return prize.getId();
        }
    }
}




