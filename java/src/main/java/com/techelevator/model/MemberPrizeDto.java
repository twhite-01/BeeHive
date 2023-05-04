package com.techelevator.model;

public class MemberPrizeDto {
 private Integer memberId;
 private Integer prizeId;
 private Integer prizeStatusId;

    public MemberPrizeDto() {
    }

    public MemberPrizeDto(Integer memberId, Integer prizeId, Integer prizeStatusId) {
        this.memberId = memberId;
        this.prizeId = prizeId;
        this.prizeStatusId = prizeStatusId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(Integer prizeId) {
        this.prizeId = prizeId;
    }

    public Integer getPrizeStatusId() {
        return prizeStatusId;
    }

    public void setPrizeStatusId(Integer prizeStatusId) {
        this.prizeStatusId = prizeStatusId;
    }
}
