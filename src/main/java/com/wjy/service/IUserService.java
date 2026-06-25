package com.wjy.service;

import com.wjy.domain.User;
import com.wjy.dto.UserDTO;

public interface IUserService {
    User login(User user);

    void register(UserDTO userDTO);
}
