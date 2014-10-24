package com.spiczek.chat.backend.controllers;

import com.spiczek.chat.datastore.daos.UserDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.regex.Pattern;

/**
 * @author Piotr Siczek
 */
@Controller
public class RegisterController {

    private ModelAndView model;

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public ModelAndView registerFormSubmit() {
        return new ModelAndView("login");
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ModelAndView registerFormSubmit(@RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "login", required = false) String login, @RequestParam(value = "password", required = false) String pass) {

        model = new ModelAndView("login");
        UserDAO data = new UserDAO();

        if (isValidUserData(name, login, pass)) {
            if (data.findUser(login) == null) {
                String[] parts = name.split(" ");
                if (data.createUser(parts[0], parts[1], login, "", pass) != null)
                    model.addObject("registerSuccess", "Użytkownik został dodany pomyślnie, możesz się zalogować.");
            }
            else {
                model.addObject("registerFail", "Użytkownik o podanej nazwie już istnieje.");
            }
        }

        return model;
    }

    private boolean isValidUserData(String name, String login, String pass) {

        boolean error = false;
        if (name.equals("")) {
            model.addObject("nameError", "Wpisz swoje imię i nazwisko.");
            error = true;
        }
        else {
            String[] parts = name.split(" ");
            if (parts.length != 2) {
                model.addObject("nameError", "Wpisz poprawnie imię i nazwisko.");
                error = true;
            }
//            else {
//                String pattern = "[a-zA-Z]";
//                if (!Pattern.matches(pattern, parts[0])) {
//                    model.addObject("nameError", "Wpisz poprawnie imię i nazwisko.");
//                    error = true;
//                }
//                if (!Pattern.matches(pattern, parts[1])) {
//                    model.addObject("nameError", "Wpisz poprawnie imię i nazwisko.");
//                    error = true;
//                }
//            }
        }

        if (login.equals("")) {
            model.addObject("loginError", "Podaj nazwę użytkownika.");
            error = true;
        }
        if (pass.equals("")) {
            model.addObject("passError", "Podaj hasło.");
            error = true;
        }

        return !error;
    }
}
