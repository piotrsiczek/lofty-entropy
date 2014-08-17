package com.spiczek.chat.shared.dto;


import java.io.Serializable;

/**
 * @author Piotr Siczek
 */
public class UserDTO implements Serializable {

    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
    private Long chatKey;
    private Long friendKey;

    public UserDTO() {}

    public UserDTO(Long id, String name, String surname, String login, String password, String email, Long chatKey, Long friendKey) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.email = email;
        this.chatKey = chatKey;
        this.friendKey = friendKey;
    }

    public UserDTO(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Long getChatKey() {
        return chatKey;
    }

    public Long getFriendKey() {
        return friendKey;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + surname + " " + login;
    }
}
