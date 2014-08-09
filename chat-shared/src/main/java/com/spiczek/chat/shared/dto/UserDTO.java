package com.spiczek.chat.shared.dto;

import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Piotr Siczek
 */
public class UserDTO implements Serializable {
    Long id;
    String name;
    String surname;

    public UserDTO() {}

    public UserDTO(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
