package ru.kibia.fileserver.rest.model.file.bean;

/**
 * Created by D.Kostin on 05.12.2016.
 */
public class SharedAccess {

    private long userId;
    private SharedAccessRight right;

    public SharedAccess(long userId, SharedAccessRight right) {
        this.userId = userId;
        this.right = right;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public SharedAccessRight getRight() {
        return right;
    }

    public void setRight(SharedAccessRight right) {
        this.right = right;
    }

}
