package oramon.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class ManageResourceFile implements ResourceFile {
    private final File file;

    public ManageResourceFile(File file) {
        this.file = file;
    }

    @Override
    public Collection<ResourceFile> listFiles() {
        return Arrays.asList(file.listFiles()).stream().map(entry -> new ManageResourceFile(entry)).collect(Collectors.toList());
    }

    @Override
    public boolean isDirectory() {
        return file.isDirectory();
    }

    @Override
    public <T> T readValue(Class<T> type) throws IOException {
        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(file, type);
    }

    @Override
    public String getName() {
        return file.getName();
    }
}
