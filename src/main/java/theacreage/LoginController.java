package theacreage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mm247_000 on 8/26/2014.
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String showLoginPage(){

        return "login";
    }
}
