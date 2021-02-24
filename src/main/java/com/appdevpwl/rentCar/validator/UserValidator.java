package com.appdevpwl.rentCar.validator;

import com.appdevpwl.rentCar.model.User;
import com.appdevpwl.rentCar.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    UserServiceImpl userServiceImpl;

    @Autowired
    public UserValidator(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        //FIRST NAME
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        if (user.getName().length() < 3 || user.getName().length() > 14) {
            errors.rejectValue("name", "Size.userForm.first-name");
        }

        //LAST NAME
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "NotEmpty");
        if (user.getSurname().length() < 3 || user.getSurname().length() > 14) {
            errors.rejectValue("surname", "Size.userForm.last-name");
        }

        //EMAIL
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (userServiceImpl.findUserByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "Duplicate.userForm.email");
        }
        if (!user.getEmail().contains("@")) {
            errors.rejectValue("email", "NotAt.userForm.email");
        }

        //PASSWORD
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 7 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }


    }
}
