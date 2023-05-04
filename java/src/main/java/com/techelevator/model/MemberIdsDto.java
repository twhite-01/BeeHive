package com.techelevator.model;

public class MemberIdsDto {

    private Integer[] memberIds;

    public MemberIdsDto(Integer[] memberIds) {
        this.memberIds = memberIds;
    }

    public Integer[] getMemberIds() {
        return memberIds;
    }

    public void setMemberIds(Integer[] memberIds) {
        this.memberIds = memberIds;
    }
}
