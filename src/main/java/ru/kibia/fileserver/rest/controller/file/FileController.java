package ru.kibia.fileserver.rest.controller.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kibia.fileserver.facade.file.SharedFileFacade;
import ru.kibia.fileserver.facade.user.UserFacade;
import ru.kibia.fileserver.rest.model.file.bean.SharedFile;
import ru.kibia.fileserver.rest.model.user.bean.User;
import ru.kibia.fileserver.service.ConfigLoaderService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kostin on 08.11.2016.
 */
@RestController
public class FileController {

    @Autowired
    private ConfigLoaderService configLoader;

    @Autowired
    private FileWalker fileWalker;

    @Autowired
    private SharedFileFacade fileFacade;

    @Autowired
    private UserFacade userFacade;

    @RequestMapping(value = "/files", method = RequestMethod.GET)
    public File[] list(@RequestParam(value = "path", required = false, defaultValue = "") String path, Principal principal) throws IOException {
        FileWalkerFilter filter = new FileWalkerFilter(configLoader.getFileVisibleTypes());
        String fullPath = configLoader.getFileBaseDirectory() + principal.getName() + "/" + path;

        if(isValidPath(fullPath, principal)) {
            return fileWalker.getFiles(fullPath, filter);
        }

        return null;
    }

    @RequestMapping("/share")
    public File[] share(@RequestParam(value = "path", required = false, defaultValue = "") String path, Principal principal) throws IOException {
        User user = userFacade.get(principal.getName());
        if(user != null) {
            List<SharedFile> sharedFileList = fileFacade.getAllAvailableFiles(user.getId());
            List<String> paths = new ArrayList<>();

            for(SharedFile file : sharedFileList) {
                paths.add(file.getFile().getAbsolutePath());
            }

            FileWalkerShareFilter shareFilter = new FileWalkerShareFilter(configLoader.getFileVisibleTypes(), paths);
            String fullPath = configLoader.getFileBaseDirectory() + path;

            if(isValidPath(fullPath, principal)) {
                return fileWalker.getFiles(fullPath, shareFilter);
            }
        }

        return null;
    }

    private boolean isValidPath(String path, Principal principal) throws IOException {
        Path p1 = Paths.get(new File(path).getCanonicalPath());
        return p1.startsWith(configLoader.getFileBaseDirectory() + principal.getName());
    }
}
