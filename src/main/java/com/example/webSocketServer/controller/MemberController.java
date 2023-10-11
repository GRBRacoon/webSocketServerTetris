package com.example.webSocketServer.controller;

import com.example.webSocketServer.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MemberController {
    @Autowired
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/memberJoin")
    public String join(@RequestParam("id")String id, @RequestParam("password")String password){
        memberService.createMember(id, password);
        return "memberJoin";
    }
    @PostMapping("/memberLogIn")
    public String logIn(@RequestParam("id")String id, @RequestParam("password")String password){
        if(memberService.logIn(id, password)){
            return "log in success";
        }
        else return "log in fail";
    }
    @GetMapping("/ranking")
    public List<Map.Entry<String, Float>> getRanking(Model model){
        List<Map.Entry<String, Float>> list=memberService.getRanking();
        model.addAttribute(list);

        return list;
    }

}
