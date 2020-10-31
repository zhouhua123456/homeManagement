package com.homemanagement.demoweb;

import com.homemanagement.demoentity.entity.SeasUser;
import com.homemanagement.demoentity.entity.UserMoney;
import com.homemanagement.demoservice.service.ISeasUserService;
import com.homemanagement.demoservice.service.IUserMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController()
@RequestMapping("/userMoney")
public class FinancialManagementController {

    @Autowired
    ISeasUserService seasUserService;

    @Autowired
    IUserMoneyService userMoneyService;
    //
    @CrossOrigin(origins = "*")
    @RequestMapping("/financialManagementTab")
    public UserMoney queryMoney(@RequestParam(value = "tab",required = false) String tab, HttpSession session){
        System.out.println("进来了");
        SeasUser seasUser = (SeasUser)session.getAttribute("seasUser");
        System.out.println(seasUser.getUserId());
        UserMoney userMoney = userMoneyService.getUserMoneyByUserId(seasUser.getUserId());

        session.setAttribute("userMoney",userMoney);
        return userMoney;
    }
}
