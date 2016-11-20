package ru.kibia.fileserver.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by kostin on 09.11.2016.
 */
@Component
@PropertySource(value = "classpath:server.properties")
public class ConfigLoaderService {

    @Value("${file.visibleTypes}")
    private String[] fileVisibleTypes;

    @Value("${file.baseDirectory}")
    private String fileBaseDirectory;

    public String[] getFileVisibleTypes(){
        return fileVisibleTypes;
    }

    public String getFileBaseDirectory() {
        return fileBaseDirectory;
    }
}
