package com.spiczek.chat.backend.authentication;

import com.spiczek.chat.datastore.UserDAO;
import com.spiczek.chat.datastore.entities.User;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Piotr Siczek
 */
public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private static String ERROR_MESSAGE = "Nazwa użytkownika i hasło nie zgadzają się. Sprawdź jeszcze raz i spróbuj ponownie.";

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken token) throws AuthenticationException {
        UserDAO data = new UserDAO();
        String password = token.getCredentials().toString();

        User user = data.loginUser(username, password);
        if (user != null) {

            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new GrantedAuthorityImpl("ROLE_USER"));
            return new UserSession(user, username, password, true, true, true, true, authorities);
        }
        else {
            token.setAuthenticated(false);
            throw new BadCredentialsException(ERROR_MESSAGE);
        }
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken token) throws AuthenticationException {}
}
