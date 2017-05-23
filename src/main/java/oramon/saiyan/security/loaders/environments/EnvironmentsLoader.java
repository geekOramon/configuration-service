package oramon.saiyan.security.loaders.environments;

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
public class EnvironmentsLoader {

    public static final String FILE_TO_FILTER = "environments.yml";
    public static final String ROOT_CONFIGS = "configs";
    
    private List<ConfigEnvironment> environments;

    public EnvironmentsLoader(){
        environments = loadUserInformation();
    }

    private List<ConfigEnvironment> loadUserInformation() {
        Collection<File> files = extractAllUserFiles();
        List<ConfigEnvironment> users = extractUsersOfFiles(files);
        return users;
    }

    private List<ConfigEnvironment> extractUsersOfFiles(Collection<File> files) {
        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return files.stream().map(userFile -> {
            try {
                return mapper.readValue(userFile, Environments.class);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }).flatMap(entry -> entry.getEnvironments().stream())
                .collect(Collectors.toList());
    }

    private Collection<File> extractAllUserFiles() {
        File file = new File(getClass().getClassLoader().getResource(ROOT_CONFIGS).getFile());
        List<File> files = Arrays.stream(file.listFiles())
                .filter(element -> element.isDirectory())
                .flatMap(entry -> Arrays.asList(entry.listFiles()).stream())
                .filter(entry -> entry.getName().equals(FILE_TO_FILTER))
                .collect(Collectors.toList());
        return files;
    }

    public Collection<ConfigEnvironment> environments(){return new ArrayList<ConfigEnvironment>(environments); }
}
