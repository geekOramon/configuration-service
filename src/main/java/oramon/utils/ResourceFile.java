package oramon.utils;

import java.io.IOException;
import java.util.Collection;

public interface ResourceFile {


    public Collection<ResourceFile> listFiles();

    public boolean isDirectory();

    public <T> T readValue(Class<T> type) throws IOException;

    public String getName();
}
