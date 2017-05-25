package oramon.saiyan.security.loaders.environments;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import oramon.utils.ResourceFile;
import oramon.utils.ResourceFileFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EnvironmentsLoader {

    public static final String FILE_TO_FILTER = "environments.yml";
    public static final String ROOT_CONFIGS = "configs";

    private List<ConfigEnvironment> environments;

    public EnvironmentsLoader(){
        environments = loadUserInformation();
    }

    private List<ConfigEnvironment> loadUserInformation() {
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
        List<ResourceFile> files = file.listFiles().stream()
                .filter(element -> element.isDirectory())
                .flatMap(entry -> entry.listFiles().stream())
                .filter(entry -> entry.getName().equals(FILE_TO_FILTER))
                .collect(Collectors.toList());
        return files;
    }

    public Collection<ConfigEnvironment> environments(){return new ArrayList<ConfigEnvironment>(environments); }
}
