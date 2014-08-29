package theacreage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import theacreage.User.User;
import theacreage.User.UserRepository;

import java.util.Collection;
import java.util.Date;

/**
 * Created by mm247_000 on 8/28/2014.
 */
@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserRepositoryUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName);
        user.setLastLogin(new Date());
        userRepository.save(user);
        if(!user.isEnabled()){
            throw new DisabledException("User is disabled");
        }
        if(user == null){
            throw new UsernameNotFoundException("UserName "+userName+" not found");
        }
        return new UserRepositoryUserDetails(user);
    }

    private final static class UserRepositoryUserDetails extends User implements UserDetails {

        private UserRepositoryUserDetails(User user){
            this.setId(user.getId());
            this.setEmail(user.getEmail());
            this.setPassword(user.getPassword());
            this.setFirstName(user.getFirstName());
            this.setLastName(user.getLastName());
            this.setDateJoined(user.getDateJoined());
            this.setLastLogin(user.getLastLogin());
        }


        @Override
        public String getPassword() {
            return super.getPassword();
        }

        @Override
        public String getUsername() {
            return super.getUsername();
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return AuthorityUtils.createAuthorityList("ROLE_USER");
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}