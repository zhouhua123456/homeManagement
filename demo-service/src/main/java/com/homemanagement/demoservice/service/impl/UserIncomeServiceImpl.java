package com.homemanagement.demoservice.service.impl;

import com.homemanagement.demoentity.entity.UserIncome;
import com.homemanagement.demoentity.entity.UserMoney;
import com.homemanagement.demopojo.mapper.UserIncomeMapper;
import com.homemanagement.demopojo.mapper.UserMoneyMapper;
import com.homemanagement.demoservice.service.IUserIncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserIncomeServiceImpl implements IUserIncomeService {

    @Autowired
    UserIncomeMapper userIncomeMapper;

    @Autowired
    UserMoneyMapper userMoneyMapper;

    @Transactional( rollbackFor = {RuntimeException.class,Error.class})
    @CacheEvict(cacheNames = "userMoney",key = "#userIncome.userId")
    public UserIncome addIncomeRecord(UserIncome userIncome){
        UserMoney userMoney = userMoneyMapper.getUserMoneyByUserId(userIncome.getUserId());
        String totalMoney = userMoney.getTotalMoney();//将获取的总金额进行数据转换
        float totalMoney_f = Float.parseFloat(totalMoney);
        float incomeAmount_f = Float.parseFloat(userIncome.getIncomeAmount());
        float money_sum = totalMoney_f+ incomeAmount_f;//获取更新后的总金额
        userMoney.setTotalMoney(String.valueOf(money_sum));
        userMoneyMapper.addUserMoneyByObj(userMoney);
        Integer integer = userIncomeMapper.addIncomeRecord(userIncome);
        return userIncome;
    }
}
