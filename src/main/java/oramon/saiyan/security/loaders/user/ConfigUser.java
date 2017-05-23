package oramon.saiyan.security.loaders.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ConfigUser {

    private String name;
    private String password;
    private Collection<String> roles;

    public ConfigUser(){

    }

    public ConfigUser(String name, String password, Collection<String> roles){

        this.name = name;
        this.password = password;
        this.roles = roles;
    }

    public String name(){ return name; }
    public String password(){ return password; }
    public Collection<String> roles(){
        List<String> newRoles = new ArrayList<>();
        roles.stream().forEach(role -> newRoles.add(role));
        return newRoles;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Collection<String> roles) {
        this.roles = roles;
    }
}
