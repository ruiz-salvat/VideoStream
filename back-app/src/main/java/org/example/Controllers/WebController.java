package org.example.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@CrossOrigin
public class WebController {

    @RequestMapping(value = "")
    public String index(@RequestParam(required = false) String video) {
        if (video == null) return "index";
        else return "page";
    }

    @RequestMapping(value = "admin")
    public String uploadVideo() {
        return "admin";
    }

    @RequestMapping(value = "components/{component-name}")
    public String components(@PathVariable("component-name") String componentName) {
        return "components/" + componentName;
    }

}