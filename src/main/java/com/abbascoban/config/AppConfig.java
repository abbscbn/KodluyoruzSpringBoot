package com.abbascoban.config;

import com.abbascoban.data.entity.User;
import com.abbascoban.data.repository.UserRepository;
import com.abbascoban.exception.BaseExcepiton;
import com.abbascoban.exception.ErrorMessage;
import com.abbascoban.exception.ErrorMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@Configuration
public class AppConfig {

    @Autowired
    private UserRepository userRepository;



    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

                Optional<User> optional = userRepository.findByUsername(username);

               if(optional.isPresent()){
                   return optional.get();
               }
                throw new BaseExcepiton(new ErrorMessage(ErrorMessageType.USERNAMEORPASSWORDNOTFOUND,"girilen username: "+username));
            }
        };

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider= new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService());

        return authenticationProvider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
