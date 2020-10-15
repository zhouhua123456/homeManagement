package com.homemanagement.demopojo.mapper;

import com.homemanagement.demoentity.entity.SeasUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component(value="seasUserMapper")
public interface SeasUserMapper {

    @Select("select * from seas_user where user_id = #{userId}")
    public SeasUser getSeasUserByUserId(String userId);

}
