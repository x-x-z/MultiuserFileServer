package ru.kibia.fileserver.mvc.model.file.bean;

import java.io.File;
import java.util.Set;

/**
 * Created by kostin on 22.11.2016.
 */
public class SharedFile {

    private final long id;
    private final File file;
    private final long ownerUserId;
    private Set<Long> foreignUsersId;

    public SharedFile(long id, File file, long ownerUserId, Set<Long> foreignUsersId) {
        this.id = id;
        this.file = file;
        this.ownerUserId = ownerUserId;
        this.foreignUsersId = foreignUsersId;
    }

    public SharedFile(File file, long ownerUserId, Set<Long> foreignUsersId) {
        this(0, file, ownerUserId, foreignUsersId);
    }

    public long getId() {
        return id;
    }

    public File getFile() {
        return file;
    }

    public long getOwnerUserId() {
        return ownerUserId;
    }

    public Set<Long> getForeignUsersId() {
        return foreignUsersId;
    }

    public void addForeignUserId(long userId) {
        foreignUsersId.add(userId);
    }
}
