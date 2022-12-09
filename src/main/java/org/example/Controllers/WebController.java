package org.example.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
public class WebController {

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/page")
    public String page() {
        return "page";
    }

    @RequestMapping(value = "/upload-video")
    public String uploadVideo() {
        return "upload-video";
    }

    @RequestMapping(value = "components/{component-name}")
    public String components(@PathVariable("component-name") String componentName) {
        return "components/" + componentName;
    }

}