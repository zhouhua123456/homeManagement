package com.homemanagement.demoservice.service;

import com.homemanagement.demoentity.entity.SeasUser;

public interface ISeasUserService {
    SeasUser getSeasUserByUserId(String userId);
}
