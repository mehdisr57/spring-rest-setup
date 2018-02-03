package com.msrazavi.tamim.controller;

import com.msrazavi.tamim.model.UserEntity;
import com.msrazavi.tamim.model.UserProfileEntity;
import com.msrazavi.tamim.repository.UserProfileRepository;
import com.msrazavi.tamim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by Mehdi on 2/3/2018.
 */
@Controller
public class UserProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserProfileRepository userProfileRepository;


    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        UserProfileEntity userProfileEntity = new UserProfileEntity();
        userProfileEntity.setUser(getUser());
        modelAndView.addObject("userProfileEntity", userProfileEntity);
        modelAndView.setViewName("profile");
        return modelAndView;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public ModelAndView editProfile(@Valid UserProfileEntity profileEntity, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("profile");
        } else {
            profileEntity.setUser(getUser());
            userProfileRepository.save(profileEntity);
            modelAndView.addObject("successMessage", "User's Profile has been registered successfully");
            modelAndView.addObject("userProfileEntity", new UserProfileEntity());
            modelAndView.setViewName("profile");
        }
        return modelAndView;
    }

    private UserEntity getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        return userService.findByUsername(name);
    }
}
