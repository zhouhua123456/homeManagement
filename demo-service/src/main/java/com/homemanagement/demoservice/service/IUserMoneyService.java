package com.homemanagement.demoservice.service;

import com.homemanagement.demoentity.entity.UserMoney;

public interface IUserMoneyService {
    UserMoney getUserMoneyByUserId(String userId);

    UserMoney addUserMoneyByObj(UserMoney userMoney);
}
