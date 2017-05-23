package oramon.saiyan.security.loaders.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsersLoader {

    private List<ConfigUser> users;

    public UsersLoader(){
        users = loadUserInformation();
    }

    private List<ConfigUser> loadUserInformation() {
        Collection<File> files = extractAllUserFiles();
        List<ConfigUser> users = extractUsersOfFiles(files);
        return users;
    }

    private List<ConfigUser> extractUsersOfFiles(Collection<File> files) {
        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return files.stream().map(userFile -> {
                try {
                    return mapper.readValue(userFile, Users.class);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }).flatMap(entry -> entry.getUsers().stream())
                    .collect(Collectors.toList());
    }

    private Collection<File> extractAllUserFiles() {
        File file = new File("src/main/resources/configs");
        List<File> files = Arrays.stream(file.listFiles())
                .filter(element -> element.isDirectory())
                .flatMap(entry -> Arrays.asList(entry.listFiles()).stream())
                .filter(entry -> entry.getName().equals("users.yml"))
                .collect(Collectors.toList());
        return files;
    }

    public Collection<ConfigUser> users(){return new ArrayList<ConfigUser>(users); }
}
