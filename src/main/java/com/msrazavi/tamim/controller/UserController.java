package com.msrazavi.tamim.controller;

import com.msrazavi.tamim.model.UserEntity;
import com.msrazavi.tamim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        UserEntity user = new UserEntity();
        modelAndView.addObject("userEntity", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid UserEntity user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        UserEntity userExists = userService.findByUsername(user.getUsername());
        if (userExists != null) {
            bindingResult
                    .rejectValue("username", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.save(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("userEntity", new UserEntity());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }


    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }
}
