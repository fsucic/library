package com.example.library.services;

import com.example.library.controllers.requests.MemberRequest;
import com.example.library.models.MemberModel;
import com.example.library.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(@Autowired MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberModel readOne(long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() ->
                new IllegalArgumentException("No member with that ID!"));
    }

    public MemberModel createMember(MemberRequest memberRequest) {
        MemberModel memberModel = new MemberModel();
        memberModel.setMemberUsername(memberRequest.getMemberUsername());
        memberModel.setMemberPassword(memberRequest.getMemberPassword());
        memberModel.setNumberOfBooksLoaned(0);
        try {
            return memberRepository.save(memberModel);
        } catch (Exception e) {
            throw new RuntimeException("Interaction with DB unsuccessful!");
        }
    }


    public long deleteMember(long memberId) {
        try {
            memberRepository.deleteById(memberId);
            return memberId;
        } catch (Exception e) {
            throw new RuntimeException("Interaction with DB unsuccessful!");
        }
    }

    public List<MemberModel> readAll() {
        try {
            return (List<MemberModel>) memberRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Interaction with DB unsuccessful!");
        }
    }

}