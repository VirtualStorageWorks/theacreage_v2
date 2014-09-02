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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import theacreage.Security.UserRepositoryUserDetailsService;
import javax.validation.Valid;
import java.util.*;

/**
 * Created by VirtualStorageWorks on 8/27/2014.
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

    @RequestMapping("/account")
    public String userAccount(@ModelAttribute User user, Model model){
        //user = userRepository.findByUsername(username);
        user = userRepository.findByUsername(((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        model.addAttribute("user", user);
        return "userAccount";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm(@ModelAttribute User user) {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String newUserRegistration(@Valid User user, BindingResult result, RedirectAttributes redirect) {
        if(result.hasErrors()){
            return "register";
        }
        String password = user.getPassword();
        password = encoder.encode(password);
        user.setPassword(password);
        user.setDateJoined(Calendar.getInstance());
        user.setLastLogin(Calendar.getInstance());
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