package theacreage.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import theacreage.CustomUserDetailsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

/**
 * Created by wpmwm on 8/27/2014.
 */
@Controller
public class UserController {

    @Autowired
    protected AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    private String password;
    PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(value = "/signup")
    public String newUserSignupPage(){
        return "signup";
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String newUserSignup(@ModelAttribute("user") User user, BindingResult result,  HttpServletRequest request, HttpServletResponse response){
        password = user.getPassword();
        user.setPassword(encoder.encode(password));
        user.setDateJoined(new Date());
        user.setLastLogin(new Date());
        user.setEnabled(true);
        userRepository.save(user);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getEmail());

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken (userDetails, user.getPassword(), userDetails.getAuthorities());
        authenticationManager.authenticate(auth);
        if(auth.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(auth);
            System.out.println("here");
            return "index";
        }
        return "index";

}
}
