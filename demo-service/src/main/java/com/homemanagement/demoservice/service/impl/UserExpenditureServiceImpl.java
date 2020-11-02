package com.homemanagement.demoservice.service.impl;

import com.homemanagement.demoentity.entity.UserExpenditure;
import com.homemanagement.demoentity.entity.UserMoney;
import com.homemanagement.demopojo.mapper.UserExpenditureMapper;
import com.homemanagement.demopojo.mapper.UserMoneyMapper;
import com.homemanagement.demoservice.service.IUserExpenditureService;
import com.homemanagement.demoservice.service.myException.MyAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class UserExpenditureServiceImpl implements IUserExpenditureService {

    @Autowired
    UserMoneyMapper userMoneyMapper;

    @Autowired
    UserExpenditureMapper userExpenditureMapper;


    @Override
    @Transactional( rollbackFor = {RuntimeException.class,Error.class})
    @CacheEvict(cacheNames = "userMoney",key = "#userExpenditure.userId")
    public UserExpenditure addExpenditureRecords(UserExpenditure userExpenditure) {
        UserMoney userMoney = userMoneyMapper.getUserMoneyByUserId(userExpenditure.getUserId());
        String totalMoney = userMoney.getTotalMoney();//将获取的总金额进行数据转换
        float totalMoney_f = Float.parseFloat(totalMoney);
        float expenditureAmount_f = Float.parseFloat(userExpenditure.getExpenditureAmount());
        float money_sum = totalMoney_f - expenditureAmount_f;//获取更新后的总金额
        if(money_sum < 0){
            throw new MyAccountException("余额不足！");
        }
        userMoney.setTotalMoney(String.valueOf(money_sum));
        userMoneyMapper.addUserMoneyByObj(userMoney);
        Integer flag = userExpenditureMapper.addExpenditureRecords(userExpenditure);
        return userExpenditure;
    }
}
