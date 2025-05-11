package com.nobleson.dashboardmanagement.serviceInterface;

import com.nobleson.dashboardmanagement.dto.LoginRequest;
import com.nobleson.dashboardmanagement.dto.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);
}
