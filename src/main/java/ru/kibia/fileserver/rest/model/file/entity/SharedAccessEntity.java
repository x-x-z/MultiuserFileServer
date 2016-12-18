package ru.kibia.fileserver.rest.model.file.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by kostin on 22.11.2016.
 */
@Entity
@Table(name = "SHARED_ACCESS")
public class SharedAccessEntity {

    @EmbeddedId
    private SharedAccessPkEntity pk;

    private int rightId;

    public SharedAccessEntity() {
    }

    public SharedAccessEntity(SharedAccessPkEntity pk, int rightId) {
        this.pk = pk;
        this.rightId = rightId;
    }

    public SharedAccessPkEntity getPk() {
        return pk;
    }

    public int getRightId() {
        return rightId;
    }

    @Override
    public String toString() {
        return "SharedAccessEntity{" +
                "pk=" + pk +
                '}';
    }
}
