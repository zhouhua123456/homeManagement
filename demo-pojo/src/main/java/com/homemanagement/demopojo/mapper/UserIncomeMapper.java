package com.homemanagement.demopojo.mapper;

import com.homemanagement.demoentity.entity.SeasUser;
import com.homemanagement.demoentity.entity.UserIncome;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "userIncomeMapper")
public interface UserIncomeMapper {

    @Insert("insert into user_income(user_id,user_name,income_amount,income_type,income_date,remarks) values(#{userId},#{userName},#{incomeAmount},#{incomeType},#{incomeDate},#{remarks})")
    public Integer addIncomeRecord(UserIncome userIncome);

}
