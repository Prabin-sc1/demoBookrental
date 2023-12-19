package com.bookrental.bookrental.service.member;

import com.bookrental.bookrental.pojo.member.MemberRequestPojo;

import java.util.List;

public interface MemberService {
    void createMember(MemberRequestPojo member);
    MemberRequestPojo getMemberById(Integer id);
    List<MemberRequestPojo> getALlMember();
    void deleteMember(Integer id);
}
