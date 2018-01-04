package com.membermanager.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public void addNewMember(Member member) {
        memberRepository.save(member);
    }

    public void removeMember(Member member){
        List<Member> memberToBeRemoved = memberRepository.findByFirstName(member.getFirstName());
        memberRepository.delete(memberToBeRemoved);
    }

    public List<Member> getMember(String firstName) {
        return memberRepository.findByFirstName(firstName);
    }

    public int updateMember(Member member){
        return memberRepository.updateMember(member.getFirstName(), member.getLastName(), member.getDateOfBirth(), member.getZipCode());

    }

    public void removeAllMembers(){
        memberRepository.deleteAll();
    }
}
