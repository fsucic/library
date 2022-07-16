package com.example.library.services;


import com.example.library.models.MemberModel;
import com.example.library.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MemberUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        MemberModel member = memberRepository.findByMemberUsername(username);
        if (member == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(member);
    }

}
