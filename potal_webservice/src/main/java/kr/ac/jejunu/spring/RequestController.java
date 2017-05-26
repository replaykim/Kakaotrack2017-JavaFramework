package kr.ac.jejunu.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by replay on 2017. 5. 19..
 */

@Controller
public class RequestController {
    @RequestMapping("/request")
    public String contentnego() {
        return "request";
    }
}