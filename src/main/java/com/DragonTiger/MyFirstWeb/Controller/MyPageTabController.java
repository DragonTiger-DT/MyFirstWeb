package com.DragonTiger.MyFirstWeb.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.DragonTiger.MyFirstWeb.VO.MemberVO;
import com.DragonTiger.MyFirstWeb.mapper.MemberMapper;

@Controller
public class MyPageTabController {

    @Autowired
    MemberMapper mapper;


    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/tab1")
    public String tab1() {
        return "Ajax/Introduction";
    }

    @GetMapping("/tab2")
    public String tab2() {
        return "Ajax/Member";
    }
    
    @GetMapping("/tab3")
    public String list(Model model) {
        try {
            model.addAttribute("members", mapper.getidname());
        } catch (Exception e) {
            System.out.println("DB데이터 가져오기에 실패했습니다.");
        }
        return "Ajax/MemberList";
    }

    @GetMapping("/tab5")
    public String MyPageTab() {
        return "Ajax/hello";
    }
    @RequestMapping("/Memberjoin")
    public String join(@RequestParam("id") String id,       @RequestParam("name") String name,
                       @RequestParam("email") String email, @RequestParam("password") String password) {
        MemberVO mem = new MemberVO(id, name, email, password);

        try {
            mapper.insertMember(mem);
        } catch (Exception e) {
            System.out.println("DB데이터 저장에 실패했습니다.");
        }
        return "index";
    }






    @GetMapping("/Memberdelete")
    public String delete(@RequestParam("id") String id, @RequestParam("password") String password) {
        try {
            if (mapper.getpassword(id).equals(password)) {
                mapper.deleteMember(id);
            }
            else
                System.out.println("비밀번호가 틀렸습니다.");
                
        } catch (Exception e) {
            System.out.println("DB데이터 삭제에 실패했습니다.");
            return "MemberList";
        }
        return "index";
    }

    @RequestMapping("/MemberListtextbox")
    public String listtextbox(Model model , @RequestParam("id") String id) {
        model.addAttribute("id", id);
        return "Ajax/MemberListtextbox";
    }
}
