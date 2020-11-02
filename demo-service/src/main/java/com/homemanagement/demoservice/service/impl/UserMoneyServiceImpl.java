package com.homemanagement.demoservice.service.impl;

import com.homemanagement.demoentity.entity.SeasUser;
import com.homemanagement.demoentity.entity.UserMoney;
import com.homemanagement.demopojo.mapper.UserMoneyMapper;
import com.homemanagement.demoservice.service.IUserMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserMoneyServiceImpl implements IUserMoneyService {

    @Autowired
    UserMoneyMapper userMoneyMapper;

    @Override
    @Cacheable(cacheNames = "userMoney",key = "#userId")
    public UserMoney getUserMoneyByUserId(String userId) {
        UserMoney userMoney  = userMoneyMapper.getUserMoneyByUserId(userId);
        return userMoney;
    }


    @CachePut(cacheNames = "userMoney",key = "#userMoney.userId")
    public UserMoney addUserMoneyByObj(UserMoney userMoney) {
        userMoneyMapper.addUserMoneyByObj(userMoney);
        return userMoney;
    }
}
