package ru.kibia.fileserver.mvc.controller.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kibia.fileserver.mvc.model.file.FileWalker;
import ru.kibia.fileserver.mvc.model.file.FileWalkerFilter;
import ru.kibia.fileserver.service.ConfigLoaderService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;

/**
 * Created by kostin on 08.11.2016.
 */
@RestController
public class FileController {

    @Autowired
    private ConfigLoaderService configLoader;

    @Autowired
    private FileWalker fileWalker;

    @RequestMapping(value = "/files", method = RequestMethod.GET)
    public File[] list(@RequestParam(value = "path", required = false, defaultValue = "") String path /*, Principal principal*/) throws IOException {
        FileWalkerFilter filter = new FileWalkerFilter(configLoader.getFileVisibleTypes());
        String fullPath = configLoader.getFileBaseDirectory() + "kostin" + "/" + path;
        System.out.println(fullPath + ", isValid = " + isValidPath(fullPath));
        return fileWalker.getFiles(fullPath, filter);
    }

    @RequestMapping("/share")
    public File[] share() throws FileNotFoundException {
        return null;
    }

    private boolean isValidPath(String path) throws IOException {
        Path p1 = Paths.get(new File(path).getCanonicalPath());
        return p1.startsWith(configLoader.getFileBaseDirectory() + "kostin");
    }
}
