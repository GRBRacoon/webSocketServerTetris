package com.example.webSocketServer.service;


import com.example.webSocketServer.domain.Member;
import com.example.webSocketServer.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.*;

@Configuration
@Service
public class MemberService {
    @Autowired
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //생성
    public boolean createMember(String id,String password){
        if(memberRepository.findById(id).isEmpty()){
            Member member=new Member();
            member.setId(id);
            member.setPassword(password);
            memberRepository.save(member);
            return true;
        }
        else return false;

    }
    //로그인
    public boolean logIn(String id,String password){
        if(findMemberById(id).getPassword().equals(password)) {
            return true;
        }
        else return false;
    }
    //멤버 찾기.
    public Member findMemberById(String id){
        return memberRepository.findById(id).get();
    }

    public List<Map.Entry<String,Float>> getRanking() {
        HashMap<String, Float> ranking=new HashMap<>();
        List<Member> list= memberRepository.findAll();
        for(int i=0;i<list.size();i++){
            ranking.put(list.get(i).getId(),list.get(i).getRate());
        }
        List<Map.Entry<String, Float>> entryList = new LinkedList<>(ranking.entrySet());
        entryList.sort(Map.Entry.comparingByValue());
        List<Map.Entry<String ,Float>> returnList=new ArrayList<>();

        for(int i=0;i<10;i++){
            returnList.add(entryList.get(i));
        }
        return returnList;

    }
}
