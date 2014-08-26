package theacreage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wptrs on 8/26/2014.
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String showHomePage(){
        return "index";
    }
}
