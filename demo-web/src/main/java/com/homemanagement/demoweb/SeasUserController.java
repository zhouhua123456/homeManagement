package com.homemanagement.demoweb;

import com.homemanagement.demoentity.entity.SeasUser;
import com.homemanagement.demoentity.entity.UserMoney;
import com.homemanagement.demoservice.service.ISeasUserService;
import com.homemanagement.demoservice.service.IUserMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Map;

@RestController
public class SeasUserController {

    @Autowired
    ISeasUserService seasUserService;

    @Autowired
    IUserMoneyService userMoneyService;

    @CrossOrigin(origins = "*")
    @RequestMapping("/login")
    public SeasUser querySeasUser(@RequestParam(value = "username",required = false) String username, @RequestParam(value = "password",required = false) String password, Model model, HttpSession session, HttpServletRequest request){
        System.out.println("用户名为："+username);
        System.out.println("密码为："+password);

        SeasUser seasUser = seasUserService.getSeasUserByUserId(username);//这里需要将个人信息缓存一下
        userMoneyService.getUserMoneyByUserId(username);//这边需要将个人的总财产缓存一下
        if(seasUser != null){
            if(password.equals(seasUser.getPassword())){

                session.setAttribute("seasUser",seasUser);
                String id = session.getId();
                System.out.println(id);
                return seasUser;
            }else{
                seasUser = null;
            }
        }
        //SeasUser seasUser = seasUserService.getSeasUser(id);
        return seasUser;
    }


}
