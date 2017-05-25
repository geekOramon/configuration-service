package oramon.utils;

import java.io.File;

public class ResourceFileFactory {

    public static ResourceFile build(String path){
        File file = new File(ResourceFileFactory.class.getClassLoader().getResource(path).getFile());
        if(file.exists()){
            return new ManageResourceFile(file);
        }

        return null;
    }
}
