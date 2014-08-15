package com.spiczek.chat.backend.authentication;

import com.spiczek.chat.datastore.UserDAO;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Piotr Siczek
 */
public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken token) throws AuthenticationException {
        UserDAO data = new UserDAO();
        String password = token.getCredentials().toString();

        if (data.validateUser(username, password)) {
            return new User(username, password, true, true, true, true, null);
        }
        else {
            token.setAuthenticated(false);
            throw new BadCredentialsException("Nazwa użytkownika, hasło nie zgadzają sie sprobój ponownie." + token.getPrincipal());
        }
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken token) throws AuthenticationException {}
}
