package oramon.saiyan.security.loaders.user;

import java.util.Collection;

public class Users {

    public Users(){

    }

    public void setUsers(Collection<ConfigUser> users) {
        this.users = users;
    }

    private Collection<ConfigUser> users;

    public Users(Collection<ConfigUser> users) {
        this.users = users;
    }

    public Collection<ConfigUser> getUsers() {
        return users;
    }
}
