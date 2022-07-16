package com.example.library.controllers;

import com.example.library.controllers.requests.AuthorRequest;
import com.example.library.controllers.requests.MemberRequest;
import com.example.library.controllers.responses.AuthorView;
import com.example.library.controllers.responses.MemberView;
import com.example.library.models.AuthorModel;
import com.example.library.models.MemberModel;
import com.example.library.services.AuthorService;
import com.example.library.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(@Autowired MemberService memberService) {
        this.memberService = memberService;
    }


    @Transactional
    @PostMapping
    public MemberView createMember(@RequestBody MemberRequest memberRequest){
        MemberModel memberModel= memberService.createMember(memberRequest);
        return new MemberView(memberModel.getMemberUsername(), memberModel.getNumberOfBooksLoaned());
    }



    @Transactional
    @DeleteMapping
    public long deleteMember(@RequestBody MemberRequest memberRequest){ //what to return?
        return memberService.deleteMember(memberRequest);
    }


    @Transactional
    @GetMapping
    public List<MemberView> readAll(){
        return memberService.readAll().stream().map(x->new MemberView(x.getMemberUsername(),
                x.getNumberOfBooksLoaned())).collect(Collectors.toList());
    }


}
