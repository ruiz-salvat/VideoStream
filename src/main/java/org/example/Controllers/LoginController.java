package org.example.Controllers;

import org.example.Entities.User;
import org.example.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

//    @RequestMapping(value = "/registration", method = RequestMethod.POST)
//    public String createNewUser(@Valid User user, BindingResult bindingResult) {
////        ModelAndView modelAndView = new ModelAndView();
//
//        if (bindingResult.hasErrors()) {
//            return "login";
////            modelAndView.setViewName("registration");
//        } else {
//            userService.saveUser(user);
////            modelAndView.addObject("successMessage", "User has been registered successfully");
////            modelAndView.addObject("user", new User());
////            modelAndView.setViewName("registration");
//        }
////        return modelAndView;
//        return "index";
//    }

}
