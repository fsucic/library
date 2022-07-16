package com.example.library.services;

import com.example.library.controllers.requests.MemberRequest;
import com.example.library.models.AuthorModel;
import com.example.library.models.BookModel;
import com.example.library.models.MemberModel;
import com.example.library.repositories.AuthorRepository;
import com.example.library.repositories.BookRepository;
import com.example.library.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(@Autowired MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public MemberModel createMember(MemberRequest memberRequest) {
        MemberModel memberModel = new MemberModel();
        memberModel.setMemberUsername(memberRequest.getMemberUsername());
        memberModel.setMemberPassword(memberRequest.getMemberPassword());
        memberModel.setNumberOfBooksLoaned(0);
        try {
            return memberRepository.save(memberModel);
        } catch (Exception e) {
            return null;
        }
    }

    public long deleteMember(MemberRequest memberRequest) { //
        return memberRepository.deleteByMemberUsername(memberRequest.getMemberUsername());
    }


    public List<MemberModel> readAll() {
        try {
            return (ArrayList<MemberModel>) memberRepository.findAll();
        } catch (Exception e) {
            return null;
        }
    }
}