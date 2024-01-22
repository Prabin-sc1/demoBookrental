package com.bookrental.bookrental.service.member;

import com.bookrental.bookrental.config.CustomMessageSource;
import com.bookrental.bookrental.constants.ModuleNameConstants;
import com.bookrental.bookrental.enums.Message;
import com.bookrental.bookrental.exception.AppException;
import com.bookrental.bookrental.exception.CategoryAlreadyExistsException;
import com.bookrental.bookrental.exception.ResourceNotFoundException;
import com.bookrental.bookrental.mapper.MemberMapper;
import com.bookrental.bookrental.model.Member;
import com.bookrental.bookrental.pojo.member.MemberRequestPojo;
import com.bookrental.bookrental.pojo.member.MemberResponsePojo;
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
    private final MemberMapper memberMapper;
    private final CustomMessageSource customMessageSource;

    @Override
    public void createMember(MemberRequestPojo memberRequestPojo) {
        Member member = new Member();
        if (memberRequestPojo.getId() != null) {
            member = memberRepository.findById(memberRequestPojo.getId()).orElse(member);
        }
        try {
            beanUtils.copyProperties(member, memberRequestPojo);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new AppException(e.getMessage());
        }
        member.setEmail(memberRequestPojo.getEmail().toLowerCase());
        try {
            memberRepository.save(member);
        } catch (Exception e) {
            throw new AppException(customMessageSource.get(Message.ALREADY_EXISTS.getCode(), ModuleNameConstants.MEMBER));
        }
    }

    @Override
    public MemberResponsePojo getMemberById(Integer id) {
        return memberMapper.getSingleMember(id).orElseThrow(() ->
                new AppException(customMessageSource.get(Message.ID_NOT_FOUND.getCode(), ModuleNameConstants.MEMBER)));
    }

    @Override
    public List<MemberResponsePojo> getAllMember() {
        return memberMapper.getAllMember();
    }

    @Override
    public void deleteMember(Integer id) {
        memberRepository.deleteById(id);
    }

    @Override
    public Member findMemberById(Integer id) {
        return memberRepository.findById(id).orElseThrow(() ->
                new AppException(customMessageSource.get(Message.ID_NOT_FOUND.getCode(), ModuleNameConstants.MEMBER)));
    }
}
