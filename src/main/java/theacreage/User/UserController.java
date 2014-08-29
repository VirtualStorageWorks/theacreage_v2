package theacreage.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import theacreage.UserRepositoryUserDetailsService;
import javax.validation.Valid;
import java.util.*;

/**
 * Created by wpmwm on 8/27/2014.
 */
@Controller
public class UserController {

    @Autowired
    protected AuthenticationManager authenticationManager;
    @Autowired
    private UserRepositoryUserDetailsService userRepositoryUserDetailsService;

    PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/directory")
    public String userDirectory(Model model) {
        List<User> userList = userRepository.findAll();
        model.addAttribute("userList", userList);
        return "directory";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signupForm(@ModelAttribute User user) {
        return "signup";
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String newUserSignup(@Valid User user, BindingResult result, RedirectAttributes redirect) {
        if(result.hasErrors()){
            return "signup";
        }
        String password = user.getPassword();
        password = encoder.encode(password);
        user.setPassword(password);
        user.setDateJoined(new Date());
        user.setLastLogin(new Date());
        user.setEnabled(true);

        userRepository.save(user);
        redirect.addFlashAttribute("globalMessage", "Successfully signed up!");

        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, password, authorities);

        // Place the new Authentication object in the security context.
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/";

    }
}