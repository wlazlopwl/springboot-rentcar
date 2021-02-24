package com.appdevpwl.rentCar.controller;

import com.appdevpwl.rentCar.exception.EmailExistException;
import com.appdevpwl.rentCar.model.ConfirmationToken;
import com.appdevpwl.rentCar.model.User;
import com.appdevpwl.rentCar.service.impl.ConfirmationTokenServiceImpl;
import com.appdevpwl.rentCar.service.impl.MailSenderServiceImpl;
import com.appdevpwl.rentCar.service.impl.RoleServiceImpl;
import com.appdevpwl.rentCar.service.impl.UserServiceImpl;
import com.appdevpwl.rentCar.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

@Controller
public class RegistrationController {

    UserServiceImpl userServiceImpl;
    UserValidator userValidator;
    RoleServiceImpl roleServiceImpl;
    MailSenderServiceImpl mailSenderServiceImpl;
    ConfirmationTokenServiceImpl confirmationTokenServiceImpl;

    @Autowired
    public RegistrationController(UserServiceImpl userServiceImpl,
                                  UserValidator userValidator,
                                  RoleServiceImpl roleServiceImpl,
                                  MailSenderServiceImpl mailSenderServiceImpl,
                                  ConfirmationTokenServiceImpl confirmationTokenServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.userValidator = userValidator;
        this.roleServiceImpl = roleServiceImpl;
        this.mailSenderServiceImpl = mailSenderServiceImpl;
        this.confirmationTokenServiceImpl = confirmationTokenServiceImpl;
    }

    @GetMapping("/singup")
    public String setRegistrationPage(Model model) {

        model.addAttribute("user", new User());

        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid User user, BindingResult bindingResult) throws MessagingException {


        if (bindingResult.hasErrors()) {
            return "register";
        } else {
            try {
                user.setRoles(roleServiceImpl.userRoleByName("ROLE_USER"));
                userServiceImpl.save(user);
                ConfirmationToken confirmationToken = new ConfirmationToken(user);
                confirmationTokenServiceImpl.save(confirmationToken);
                mailSenderServiceImpl.sendMail(user.getEmail(),
                        "Confirm your email in RentCar",
                        "To confirm your account, please click here : "
                        + "http://localhost:8080/confirm-account?token=" + confirmationToken.getToken());
            } catch (EmailExistException exception) {
                bindingResult.rejectValue("tt", "Email is already registered");
                return "register";
            }
        }
        return "register";
    }

    @RequestMapping(value = "/confirm-account", method = RequestMethod.GET)
    public String confirmEmail(@RequestParam("token") String token) throws EmailExistException {

        ConfirmationToken confirmationToken = confirmationTokenServiceImpl.getByToken(token);

        if (confirmationToken != null) {
            User user = userServiceImpl.findUserByEmail(confirmationToken.getUser().getEmail());
            if (!user.isEnabled()) {
                user.setEnabled(true);
                userServiceImpl.save(user);
                return "positive-confirm-email";
            } else return "negative-confirm-email";


        } else return "bad-confirm-token";


    }

}
