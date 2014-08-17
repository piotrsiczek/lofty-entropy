package com.spiczek.chat.backend.service;

import com.spiczek.chat.backend.authentication.UserSession;
import com.spiczek.chat.shared.UserService;
import com.spiczek.chat.shared.dto.UserDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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


    @Override
    public UserDTO getUserDetails() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserSession u = (UserSession)auth.getPrincipal();

        //UserDetails userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return new UserDTO(u.getId(), u.getName(), u.getSurname(), u.getUsername(), u.getPassword(),
                u.getEmail(), u.getChat().getId(), u.getFriend().getId());
    }
}
