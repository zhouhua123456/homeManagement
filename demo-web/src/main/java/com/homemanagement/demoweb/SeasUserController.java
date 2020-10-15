package com.homemanagement.demoweb;

import com.homemanagement.demoentity.entity.SeasUser;
import com.homemanagement.demoservice.service.ISeasUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeasUserController {

    @Autowired
    ISeasUserService seasUserService;

    @CrossOrigin(origins = "*")
    @RequestMapping("/login")
    public SeasUser querySeasUser(@RequestParam(value = "username",required = false) String username, @RequestParam(value = "password",required = false) String password, Model model){
        System.out.println("用户名为："+username);
        System.out.println("密码为："+password);
        SeasUser seasUser = seasUserService.getSeasUserByUserId(username);
        if(seasUser != null){
            if(password.equals(seasUser.getPassword())){
                return seasUser;
            }else{
                seasUser = null;
            }
        }
        //SeasUser seasUser = seasUserService.getSeasUser(id);
        return seasUser;
    }
}
