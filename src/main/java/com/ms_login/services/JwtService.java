package com.ms_login.services;

import com.ms_login.dto.UserCreateRequest;
import com.ms_login.entity.User;

public interface JwtService {
     String generateToken(final User user);
     String generateRefreshToken(final User user);
     String BuildToken(final User user,long time);
}
