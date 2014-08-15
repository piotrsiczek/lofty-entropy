package com.spiczek.chat.backend.service;

import com.spiczek.chat.shared.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by piotr on 2014-08-14.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Override
    public String test(String data) {
        return "elo elo " + data;
    }
}
