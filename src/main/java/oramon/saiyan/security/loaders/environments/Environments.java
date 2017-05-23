package oramon.saiyan.security.loaders.environments;

import java.util.Collection;

public class Environments {
    private Collection<ConfigEnvironment> environments;

    public Collection<ConfigEnvironment> getEnvironments() {
        return environments;
    }

    public void setEnvironments(Collection<ConfigEnvironment> environments) {
        this.environments = environments;
    }
}
