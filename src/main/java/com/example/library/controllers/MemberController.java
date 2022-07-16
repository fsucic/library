package com.example.library.controllers;

import com.example.library.controllers.requests.MemberRequest;
import com.example.library.controllers.responses.MemberView;
import com.example.library.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Set;
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
    public MemberView createMember(@RequestBody MemberRequest memberRequest) {
        return new MemberView(memberService.createMember(memberRequest));
    }


    @Transactional
    @DeleteMapping("/{memberId}")
    public long deleteMember(@PathVariable String memberId) { //what to return?
        return memberService.deleteMember(Long.parseLong(memberId));
    }

    @Transactional
    @GetMapping("/{memberId}")
    public MemberView readOne(@PathVariable String memberId) {
        return new MemberView(memberService.readOne(Long.parseLong(memberId)));
    }


    @Transactional
    @GetMapping
    public Set<MemberView> readAll() {
        return memberService.readAll().stream().map(x->new MemberView(x)).collect(Collectors.toSet());
    }


}
