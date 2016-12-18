package ru.kibia.fileserver.rest.model.file.entity;

import ru.kibia.fileserver.rest.model.file.bean.SharedAccessRight;

import javax.persistence.*;

/**
 * Created by D.Kostin on 05.12.2016.
 */
@Entity
@Table(name = "SHARED_ACCESS_RIGHTS")
public class SharedAccessRightEntity {

    @Id
    @Column(updatable = false)
    private long id;

    @Column(name = "file_right", updatable = false, unique = true)
    private String right;

    public SharedAccessRightEntity() {
    }

    public SharedAccessRightEntity(SharedAccessRight right) {
        this.right = right.toString();
    }

    public long getId() {
        return id;
    }

    public String getRight() {
        return right;
    }
}
