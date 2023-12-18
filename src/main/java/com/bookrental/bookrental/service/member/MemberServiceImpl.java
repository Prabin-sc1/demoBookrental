package com.bookrental.bookrental.service.member;

import com.bookrental.bookrental.model.Member;
import com.bookrental.bookrental.pojo.MemberRequestPojo;
import com.bookrental.bookrental.repository.MemberRepository;
import com.bookrental.bookrental.utils.NullAwareBeanUtilsBean;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();
    @Override
    public void createMember(MemberRequestPojo memberRequestPojo) {
        Member member = new Member();
        if (memberRequestPojo.getId() != null) {
            member = memberRepository.findById(memberRequestPojo.getId()).orElse(member);
        }
        try {
            beanUtils.copyProperties(member, memberRequestPojo);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        memberRepository.save(member);
    }

    @Override
    public MemberRequestPojo getMemberById(Integer id) {
        return null;
    }

    @Override
    public List<MemberRequestPojo> getALlMember() {
        return null;
    }

    @Override
    public void deleteMember(Integer id) {

    }
}
