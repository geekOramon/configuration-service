package oramon.saiyan.security.loaders.environments;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ConfigEnvironment {

    private String uri;
    private Collection<String> roles_allowed;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Collection<String> getRoles_allowed() {
        List<String> newRoles = new ArrayList<>();
        roles_allowed.stream().forEach(role -> newRoles.add("ROLE_"+ role));
        return newRoles;
    }

    public void setRoles_allowed(Collection<String> roles_allowed) {
        this.roles_allowed = roles_allowed;
    }
}
