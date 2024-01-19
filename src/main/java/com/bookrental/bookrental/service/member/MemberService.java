package com.bookrental.bookrental.service.member;

import com.bookrental.bookrental.model.Member;
import com.bookrental.bookrental.pojo.member.MemberRequestPojo;
import com.bookrental.bookrental.pojo.member.MemberResponsePojo;

import java.util.List;

public interface MemberService {
    void createMember(MemberRequestPojo member);

    MemberResponsePojo getMemberById(Integer id);

    List<MemberResponsePojo> getAllMember();

    void deleteMember(Integer id);

    Member findMemberById(Integer id);
}
