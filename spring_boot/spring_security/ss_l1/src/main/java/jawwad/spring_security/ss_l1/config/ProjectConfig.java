package jawwad.spring_security.ss_l1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectConfig {
    @Bean
    public UserDetailsService UserDetailsService() {
        var usd = new InMemoryUserDetailsManager();
        var u1 = User.withUsername("bill")
                .password("12345")
                .authorities("read")
                .build();
        usd.createUser(u1);
        return usd;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
