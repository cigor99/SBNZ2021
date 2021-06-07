package rs.ac.uns.ftn.sbnz.rentcarservice.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import rs.ac.uns.ftn.sbnz.rentcarservice.jwt.JwtAuthenticationFilter;
import rs.ac.uns.ftn.sbnz.rentcarservice.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import rs.ac.uns.ftn.sbnz.rentcarservice.jwt.TokenUtils;
import rs.ac.uns.ftn.sbnz.rentcarservice.service.SecureUserDetailsService;


@Configuration
@EnableWebSecurity
// Ukljucivanje podrske za anotacije "@Pre*" i "@Post*" koje ce aktivirati autorizacione provere za svaki pristup metodi
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private SecureUserDetailsService secureUserDetailsService;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(secureUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/login").permitAll()
//                .antMatchers("/rentcarservice/**").permitAll()
//                .antMatchers("/successful-logout.html").permitAll()
                .antMatchers("/api/register").permitAll()
                .anyRequest().authenticated()
                .and()
//                .formLogin().permitAll().and()
//                .logout()
//                .logoutUrl("/logout-user")
//                .logoutSuccessUrl("/successful-logout.html")
//                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), tokenUtils))
                .addFilterAfter(new JwtAuthenticationFilter(tokenUtils, secureUserDetailsService),
                        JwtUsernameAndPasswordAuthenticationFilter.class);
    }

}