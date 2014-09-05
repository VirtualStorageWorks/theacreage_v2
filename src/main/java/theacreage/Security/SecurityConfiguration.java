package theacreage.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import theacreage.User.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource datasource;

    @Autowired
    private UserRepositoryUserDetailsService userRepositoryUserDetailsService;

    //@Autowired
    //private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/register", "/login")
                    .not()
                    .access("isAuthenticated()")
                    .and().authorizeRequests()
                .antMatchers("/", "/signup","/aboutus", "/business**/**", "/classified**/**")
                    .permitAll()
                    .and()//.authorizeRequests()
                //.antMatchers("/userdirectory").access("hasRole('ROLE_USER')")
                    //.and()
                .authorizeRequests().anyRequest().authenticated();
        http
                // not using this but could use it to set global session variables or do other logs
                //.formLogin().successHandler(myAuthenticationSuccessHandler)
                //.and()
                .formLogin().failureUrl("/login?error")
                    .defaultSuccessUrl("/account")
                    .loginPage("/login")
                    .permitAll()
                .and()
                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
                    .permitAll();
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        auth.userDetailsService(userRepositoryUserDetailsService).passwordEncoder(encoder);
    }

/*    @Component
    public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
        @Autowired
        UserRepository userRepository;

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            request.getSession().setAttribute("attribute1",userRepository.findAll() );
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }*/
}