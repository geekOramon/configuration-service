package oramon.saiyan.security;


import oramon.saiyan.security.loaders.user.ConfigUser;
import oramon.saiyan.security.loaders.user.UsersLoader;
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
    private UsersLoader usersLoader;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        for (ConfigUser user : usersLoader.users()) {
            String[] roles = user.roles().stream().toArray(String[]::new);
            auth.inMemoryAuthentication().withUser(user.name()).password(user.password()).roles(roles);
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().and()
                .authorizeRequests()
                .antMatchers("/micro-config-template/dev").access("hasRole('ROLE_micro-config-template-DEVELOPER')")
                .antMatchers("/micro-config-template/qa").access("hasRole('ROLE_micro-config-template-LEAD')");
    }
}
