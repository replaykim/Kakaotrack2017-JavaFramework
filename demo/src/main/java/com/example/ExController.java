package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by replay on 2017. 3. 20..
 */

@Controller
public class ExController {

    @RequestMapping("/")
    public String home() {
        return "greeting";
    }


}
