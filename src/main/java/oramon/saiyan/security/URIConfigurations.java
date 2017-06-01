package oramon.saiyan.security;


import oramon.saiyan.security.loaders.environments.ConfigEnvironment;
import oramon.saiyan.security.loaders.environments.EnvironmentsLoader;
import oramon.saiyan.security.loaders.user.ConfigUser;
import oramon.saiyan.security.loaders.user.UsersLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

import java.util.Collection;
import java.util.List;

@Configuration
@EnableWebSecurity
public class URIConfigurations
        extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsersLoader usersLoader;

    @Autowired
    private EnvironmentsLoader environmentsLoader;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        for (ConfigUser user : usersLoader.loadUserInformation()) {
            String[] roles = user.roles().stream().toArray(String[]::new);
            auth.inMemoryAuthentication().withUser(user.name()).password(user.password()).roles(roles);
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final List<ConfigEnvironment> urlsToFilter = environmentsLoader.loadUserInformation();
        if(urlsToFilter.isEmpty()) return;

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry expressionInterceptUrlRegistry = http
                .httpBasic().and()
                .authorizeRequests();

        for (ConfigEnvironment environment : urlsToFilter) {
            Collection<String> roles = environment.getRoles_allowed();
            for (String role : roles) {
                String access = "hasRole('" + role + "')";
                expressionInterceptUrlRegistry = expressionInterceptUrlRegistry
                        .antMatchers(environment.getUri()).access(access);
            }
        }

        expressionInterceptUrlRegistry = expressionInterceptUrlRegistry.anyRequest().denyAll();
    }
}
