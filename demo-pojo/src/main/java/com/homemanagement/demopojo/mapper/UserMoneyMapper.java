package com.homemanagement.demopojo.mapper;

import com.homemanagement.demoentity.entity.SeasUser;
import com.homemanagement.demoentity.entity.UserMoney;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component(value="userMoneyMapper")
public interface UserMoneyMapper {

    @Select("select * from user_money where user_id = #{userId}")
    public UserMoney getUserMoneyByUserId(String userId);

}
