package com.spiczek.chat.datastore.Integration;

import com.spiczek.chat.datastore.UserDAO;
import com.spiczek.chat.datastore.entities.User;

/**
 * @author Piotr Siczek
 */
public class DataIntegration {

    private static final String FRIEND_USER_NAME = "friend_user_name";
    private static final String FRIEND_USER_SURNAME = "friend_user_surname";

    private static User user;
    private static UserDAO userDAO = new UserDAO();
    private DataIntegration() {}

    public static void generate() {

        User u1 = userDAO.createUser("a", "a");

        generateFriend(u1, 100);
    }

    public static void generateFriend(User u1, int qty) {
        user = u1;
        for(int i=0; i < qty; i++) {
            userDAO.createFriend(u1, userDAO.createUser(FRIEND_USER_NAME+i, FRIEND_USER_SURNAME+i));
        }
    }

    public static User getUser() {
        return user;
    }
}
