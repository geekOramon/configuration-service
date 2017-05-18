package oramon.rocketpower.javi.application;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class URIConfigurations
        extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("antonio").password("lolo1234").roles("MANIACO", "COJO");
        auth.inMemoryAuthentication().withUser("antonio1").password("lolo1234").roles("COJO");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().and()
                .authorizeRequests()
                .antMatchers("/prototype-config-micro-one/dev").access("hasRole('ROLE_MANIACO')")
                .antMatchers("/prototype-config-micro-one/qa").access("hasRole('ROLE_COJO')");
    }

}
