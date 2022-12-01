package com.DragonTiger.MyFirstWeb.Controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.DragonTiger.MyFirstWeb.VO.MemberVO;

@Controller
public class MyPageTabController {


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
    public String tab3() {
        return "Ajax/MemberList";
    }

    @GetMapping("/tab5")
    public String MyPageTab() {
        return "Ajax/hello";
    }
    @RequestMapping("/Memberjoin")
    public String join(@RequestParam("id") String id,       @RequestParam("name") String name,
                       @RequestParam("email") String email, @RequestParam("password") String password){
        MemberVO mem = new MemberVO(id, name, email, password);
        MemberDAO dao = new MemberDAO();
        try{
            dao.insertMemo(mem);
        }catch(SQLException e){
            System.out.println("정상적으로 추가되지 않았습니다");
            return "index";
        }
        System.out.println("정상적으로 추가되었습니다");
        return "index";
    }



    @GetMapping("/memberlist")
    public String list(Model model) {

        MemberDAO dao = new MemberDAO();
        // selectallmemo() 메소드를 호출하여 모든 레코드를 조회한 후
        // 그 결과를 model에 저장
        try {
            model.addAttribute("members", dao.selectAllMemo());
            System.out.println("정상적으로 선택되었습니다");
        } catch (SQLException e) {
            System.out.println("정상적으로 선택되지 않음");
        }

        return "Ajax/MemberList";
    }
}
