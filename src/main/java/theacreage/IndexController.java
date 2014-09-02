package theacreage;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import theacreage.User.User;

/**
 * Created by wptrs on 8/26/2014.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping(method = RequestMethod.GET)
    public String showHomePage(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object myUser = (auth != null) ? auth.getPrincipal() :  null;

        if (myUser instanceof User) {
            User user = (User) myUser;
            model.addAttribute("CurrentUser", user);
        }
        return "index";
    }

    @RequestMapping("/aboutus")
    public String showAboutUsPage(){

        return "aboutus";
    }
}
