package com.spiczek.chat.datastore.Integration;

import com.spiczek.chat.datastore.UserDAO;
import com.spiczek.chat.datastore.entities.User;

/**
 * @author Piotr Siczek
 */
public class DataIntegration {

    private static final String FRIEND_USER_NAME = "friend_user_name";
    private static final String FRIEND_USER_SURNAME = "friend_user_surname";

    private static UserDAO userDAO = new UserDAO();
    private DataIntegration() {}

    public static void generate() {

        User u1 = userDAO.createUser("a", "a");

        generateFriend(u1, 10);
    }

    private static void generateFriend(User u1, int qty) {
        for(int i=0; i < qty; i++) {
            userDAO.createFriend(u1, userDAO.createUser(FRIEND_USER_NAME+i, FRIEND_USER_SURNAME+i));
        }
    }


}
