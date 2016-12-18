package ru.kibia.fileserver.rest.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kibia.fileserver.facade.user.UserFacade;
import ru.kibia.fileserver.rest.model.user.bean.Role;
import ru.kibia.fileserver.rest.model.user.bean.User;
import ru.kibia.fileserver.service.ConfigLoaderService;

import javax.validation.Valid;
import java.io.File;

/**
 * Created by D.Kostin on 18.12.2016.
 */

@RestController
public class UserController {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private ConfigLoaderService configLoader;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<String> signUp(@RequestBody @Valid User user, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()) {
            user.setRole(Role.USER);
            userFacade.save(user);
            if(new File(configLoader.getFileBaseDirectory() + user.getLogin()).mkdir()) {
                return ResponseEntity.ok("User has been created");
            } else {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Can not create directory for user");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.toString());
        }
    }
}
