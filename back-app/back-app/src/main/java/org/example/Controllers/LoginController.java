package org.example.Controllers;

import org.example.Entities.ApplicationUser;
import org.example.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;

@Controller
@CrossOrigin
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value= {"login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value="registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        ApplicationUser applicationUser = new ApplicationUser();
        modelAndView.addObject("applicationUser", applicationUser);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String createNewUser(@Valid ApplicationUser applicationUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "registration";

        if (userService.saveUser(applicationUser) != null)
            return "index";
        else
            return "registration";
    }

}
