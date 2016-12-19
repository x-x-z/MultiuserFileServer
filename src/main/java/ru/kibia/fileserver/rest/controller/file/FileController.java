package ru.kibia.fileserver.rest.controller.file;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kibia.fileserver.facade.file.SharedFileFacade;
import ru.kibia.fileserver.facade.user.UserFacade;
import ru.kibia.fileserver.rest.model.file.bean.SharedFile;
import ru.kibia.fileserver.rest.model.user.bean.User;
import ru.kibia.fileserver.service.ConfigLoaderService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
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

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public String upload(@RequestParam(value = "path", required = false, defaultValue = "") String path,
                         @RequestParam("file") MultipartFile file, Principal principal) {

        String fullPath = getFullPath(principal.getName(), path);

        if (!file.isEmpty() && isValidPath(fullPath, principal)) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(new File(path))
                );

                stream.write(bytes);
                stream.close();
                return "You successfully uploaded file!";
            } catch (Exception e) {
                return "You failed to upload file => " + e.getMessage();
            }
        } else {
            return "You failed to upload because the file was empty or path was wrong";
        }
    }

    @RequestMapping(value = "/file", method = RequestMethod.GET)
    public File[] file(@RequestParam(value = "path", required = false, defaultValue = "") String path,
                       Principal principal, HttpServletResponse response) throws IOException {

        FileWalkerFilter filter = new FileWalkerFilter(configLoader.getFileVisibleTypes());
        String fullPath = getFullPath(principal.getName(), path);

        if(isValidPath(fullPath, principal)) {
            File file = new File(fullPath);

            if(file.isFile()) {
                response.setContentLength((int)file.length());
                response.setContentType("application/force-download");
                response.setHeader("Content-Transfer-Encoding", "binary");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

                InputStream is = new FileInputStream(file);
                IOUtils.copy(is, response.getOutputStream());
                response.flushBuffer();

                is.close();
            } else {
                return fileWalker.getFiles(fullPath, filter);
            }
        }

        return null;
    }

    @RequestMapping("/share")
    public File[] share(@RequestParam(value = "path", required = false, defaultValue = "") String path, Principal principal) throws FileNotFoundException {
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

    private String getFullPath(String username, String path) {
        return configLoader.getFileBaseDirectory() + username + "/" + path;
    }

    private boolean isValidPath(String path, Principal principal) {
        try {
            Path p1 = Paths.get(new File(path).getCanonicalPath());
            return p1.startsWith(configLoader.getFileBaseDirectory() + principal.getName());
        } catch (IOException e) {
            return false;
        }
    }
}
