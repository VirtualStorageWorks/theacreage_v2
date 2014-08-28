package theacreage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import theacreage.User.SecurityUser;
import theacreage.User.User;
import theacreage.User.UserRepository;

import java.util.Date;

/**
 * @author Siva
 *
 */
@Component
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userService;

    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {
        User user = userService.findByUsername(userName);
        user.setLastLogin(new Date());
        userService.save(user);
        if(!user.isEnabled()){
            throw new DisabledException("User is disabled");
        }
        if(user == null){
            throw new UsernameNotFoundException("UserName "+userName+" not found");
        }
        return new SecurityUser(user);
    }

}