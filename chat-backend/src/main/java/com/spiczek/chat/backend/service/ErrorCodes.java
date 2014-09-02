package com.spiczek.chat.backend.service;

/**
 * @author Piotr Siczek
 */
public class ErrorCodes {
    public static final String NO_FRIENDS_ERROR = "Nie znaleziono znajomych.";
    public static final String NO_USER_LOGIN = "Brak użytkownika o podanym loginie.";
    public static final String USER_IS_FRIEND_ALREADY = "Użytkownik jest już dodany do znajomych.";

    private ErrorCodes() {}
}
