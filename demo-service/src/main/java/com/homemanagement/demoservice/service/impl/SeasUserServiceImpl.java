package com.homemanagement.demoservice.service.impl;

import com.homemanagement.demoentity.entity.SeasUser;
import com.homemanagement.demopojo.mapper.SeasUserMapper;
import com.homemanagement.demoservice.service.ISeasUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class SeasUserServiceImpl implements ISeasUserService {

    @Autowired
    SeasUserMapper seasUserMapper;

    @Override
    @Cacheable(cacheNames = "seasUser",key = "#userId")
    public SeasUser getSeasUserByUserId(String userId) {
        SeasUser seasUser = seasUserMapper.getSeasUserByUserId(userId);
        return seasUser;
    }
}
