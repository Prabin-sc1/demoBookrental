package com.bookrental.bookrental.service.member;

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
        try {
            memberRepository.save(member);
        } catch (Exception e) {
            throw new CategoryAlreadyExistsException("A member with the email " + member.getEmail() + " already exists.");
        }
    }

    @Override
    public MemberResponsePojo getMemberById(Integer id) {
//        Member m = memberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Member", "Id", id));
//        return modelMapper.map(m, MemberResponsePojo.class);
        return memberMapper.getSingleMember(id);
    }

    @Override
    public List<MemberResponsePojo> getAllMember() {
//        List<Member> l = memberRepository.findAll();
//        return l.stream().map(e -> modelMapper.map(e, MemberResponsePojo.class)).collect(Collectors.toList());
        return memberMapper.getAllMember();
    }

    @Override
    public void deleteMember(Integer id) {
        memberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Member", "Id", id));
        memberRepository.deleteById(id);
    }
}
