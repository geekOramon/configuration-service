package oramon.saiyan.application;


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
        auth.inMemoryAuthentication().withUser("developer").password("chibiGoku").roles("DEVELOPER");
        auth.inMemoryAuthentication().withUser("leader").password("kaioken").roles("LEAD", "DEVELOPER");
        auth.inMemoryAuthentication().withUser("god").password("gokuSSJ3").roles("ADMIN", "LEAD", "DEVELOPER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().and()
                .authorizeRequests()
                .antMatchers("/micro-config-template/dev").access("hasRole('ROLE_DEVELOPER')")
                .antMatchers("/micro-config-template/qa").access("hasRole('ROLE_LEAD')");
    }

}
