package com.homemanagement.demopojo.mapper;

import com.homemanagement.demoentity.entity.UserExpenditure;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "userExpenditureMapper")
public interface UserExpenditureMapper {

    @Insert("insert into user_expenditure(user_id,user_name,expenditure_amount,expenditure_type,expenditure_date,expenditure_explain) values (#{userId},#{userName},#{expenditureAmount},#{expenditureType},#{expenditureDate},#{expenditureExplain})")
    public Integer addExpenditureRecords(UserExpenditure userExpenditure);
}
