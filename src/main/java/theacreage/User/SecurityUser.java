package theacreage.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Siva
 *
 */
public class SecurityUser extends User implements UserDetails
{
    public SecurityUser(User user) {
        if(user != null)
        {
            this.setId(user.getId());
            this.setEmail(user.getEmail());
            this.setPassword(user.getPassword());
            this.setFirstName(user.getFirstName());
            this.setLastName(user.getLastName());
            this.setDateJoined(user.getDateJoined());
            this.setLastLogin(user.getLastLogin());
            this.setRoles(user.getRoles());
        }
    }

    @Override
    public Collection<? extends SimpleGrantedAuthority> getAuthorities() {

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Collection<Role> userRoles = this.getRoles();

        if(userRoles != null)
        {
            for (Role role : userRoles) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRole());
                authorities.add(authority);
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getEmail();
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