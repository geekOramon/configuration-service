package oramon.saiyan.security.loaders.environments;

import oramon.utils.ResourceFile;
import oramon.utils.ResourceFileFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EnvironmentsLoader {

    public static final String FILE_TO_FILTER = "environments.yml";

    @Value("${root_configs}")
    private String ROOT_CONFIGS;

    public EnvironmentsLoader(){
    }

    public List<ConfigEnvironment> loadUserInformation() {
        Collection<ResourceFile> files = extractAllUserFiles();
        List<ConfigEnvironment> users = extractUsersOfFiles(files);
        return users;
    }

    private List<ConfigEnvironment> extractUsersOfFiles(Collection<ResourceFile> files) {
        return files.stream().map(userFile -> {
            try {
                return userFile.readValue(Environments.class);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }).flatMap(entry -> entry.getEnvironments().stream())
                .collect(Collectors.toList());
    }

    private Collection<ResourceFile> extractAllUserFiles() {
        ResourceFile file = ResourceFileFactory.build(ROOT_CONFIGS);
        if(file == null) return Collections.emptyList();

        List<ResourceFile> files = file.listFiles().stream()
                .filter(element -> element.isDirectory())
                .flatMap(entry -> entry.listFiles().stream())
                .filter(entry -> entry.getName().equals(FILE_TO_FILTER))
                .collect(Collectors.toList());
        return files;
    }

}
