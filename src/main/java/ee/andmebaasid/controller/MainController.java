package ee.andmebaasid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: iljad Date: 13.01.2016 Time: 22:44
 */
@Controller
public class MainController {

    @RequestMapping("/")
    public String index(){
        return "redirect:/static/index.html";
    }
}
